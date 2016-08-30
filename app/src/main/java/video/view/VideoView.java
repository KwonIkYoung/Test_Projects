package video.view;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.WindowManager;
import android.widget.MediaController;

import java.io.IOException;

import util.LogUtils;

public class VideoView extends TextureView {

    private static final String LOG_TAG = VideoView.class.getSimpleName();

    // all possible internal states
    private static final int STATE_ERROR              = -1;
    private static final int STATE_IDLE               = 0;
    private static final int STATE_PREPARING          = 1;
    private static final int STATE_PREPARED           = 2;
    private static final int STATE_PLAYING            = 3;
    private static final int STATE_PAUSED             = 4;
    private static final int STATE_PLAYBACK_COMPLETED = 5;

    // SWIPE 상수
    private static final int SWIPE_BRIGHT_FACTOR = 2;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private static int SWIPE_VOLUME_LANGE = 0;

    // SWIPE 액션 동작
    private static float initilizeBrightness;
    private static float currentBrightness;
    private float pivotBrightness;
    private float pivotY;
    private boolean isSwipeMinDistance;

    private int currentVolume = -1;
    private int pivotVolume;

    public int number;

    // currentState is a VideoView object's current state.
    // targetState is the state that a method caller intends to reach.
    // For instance, regardless the VideoView object's current state,
    // calling pause() intends to bring the object to a target state
    // of STATE_PAUSED.
    private int currentState = STATE_IDLE;
    private int targetState = STATE_IDLE;

    // Stuff we need for playing and showing a video
    private MediaPlayer mediaPlayer;
    private int videoWidth;
    private int videoHeight;
    private int surfaceWidth;
    private int surfaceHeight;
    private SurfaceTexture surfaceTexture;
    private Surface surface;
    private MediaController mediaController;
    private MediaPlayer.OnCompletionListener onCompletionListener;

    private MediaPlayer.OnPreparedListener onPreparedListener;
    private int currentBufferPercentage;
    private MediaPlayer.OnErrorListener onErrorListener;
//    private MediaPlayer.OnInfoListener onInfoListener;
    private OnSwipeListener onSwipeListener;
    private OnClickListener onClickListener;

    private View progress;

    /** GESTURE LISTENER */
    GestureDetector gestureDetector = new GestureDetector(new GestureDetector.OnGestureListener() {
        public boolean onDown(MotionEvent e) {
            pivotY = e.getY();
            isSwipeMinDistance = false;

            if (currentBrightness != 0) {
                pivotBrightness = currentBrightness;
            }

            if (currentVolume != -1) {
                pivotVolume = currentVolume;
            }
            return false;
        }
        public void onShowPress(MotionEvent e) { }
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            if (e1 == null || e2 == null) return false;

            if (!isSwipeMinDistance && Math.abs(e1.getY() - e2.getY()) > SWIPE_MIN_DISTANCE) {
                isSwipeMinDistance = true;
            }

            else if (isSwipeMinDistance) {
//                DisplayMetrics metrics = getResources().getDisplayMetrics();
//
//                // 초기 터치 섹션 구분
//                boolean isLeft = e1.getX() < metrics.widthPixels / 2;
//                float offsetY = pivotY - e2.getY();
//
//                // 밝기 조절
//                if (isLeft) {
////                    float currBright = pivotBrightness + (offsetY / SWIPE_BRIGHT_FACTOR);
////                    if (currBright < 25) {
////                        currBright = 25;
////                        pivotY -= (SWIPE_BRIGHT_FACTOR * (25 - currBright));
////                    } else if (currBright > 255) {
////                        currBright = 255;
////                        pivotY += (SWIPE_BRIGHT_FACTOR * (currBright - 255));
////                    }
////                    setBrightness(currBright);
//
//                } else {
//                    float volumeFactor = metrics.heightPixels / SWIPE_VOLUME_LANGE;
//                    int volume = (int) (pivotVolume + (offsetY / volumeFactor));
//                    if (volume < 0) {
//                        volume = 0;
//                        pivotY -= volumeFactor;
//                    } else if (volume > SWIPE_VOLUME_LANGE) {
//                        volume = SWIPE_VOLUME_LANGE;
//                        pivotY += volumeFactor;
//                    }
//                    setVolume(volume);
//                }
            }
            return true;
        }
        public void onLongPress(MotionEvent e) { }
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                int offset = 0;
                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) {

                    // down to up swipe
                    if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE
                            && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                        offset = 1;

                    // up to down swipe
                    } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE
                            && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                        offset = -1;
                    }

                    if (offset != 0 && onSwipeListener != null) {
                        onSwipeListener.onFlingVertical(offset);
                    }

                } else {

                    // right to left swipe
                    if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                            && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                        offset = 1;

                    // left to right swipe
                    } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                            && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                        offset = -1;
                    }

                    if (offset != 0 && onSwipeListener != null) {
                        onSwipeListener.onFlingHorizontal(offset);
                    }
                }
            } catch (Exception e) { }
            return true;
        }
    });

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
//        if (isOrientationPortrait()) {
//            float brightness = currentBrightness;
//            setBrightness(initilizeBrightness);
//            initilizeBrightness = 0;
//            currentBrightness = brightness;
//
//        } else {
//            if (initilizeBrightness == 0) {
//                try {
//                    initilizeBrightness = Settings.System.getInt(
//                            getContext().getContentResolver(),
//                            Settings.System.SCREEN_BRIGHTNESS);
//                } catch (Settings.SettingNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//            setBrightness(currentBrightness);
//        }
    }

    private void setBrightness(float brightness) {

        // 밝기 모드를 수동으로 설정
        Settings.System.putInt(getContext().getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);

        if (brightness != 0) {

            // 밝기 값에 value 값을 적용한다. ( value : 0 ~ 255 값 )
            Settings.System.putInt(getContext().getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS, (int) brightness);

//            if (getContext() instanceof Activity) {
//                Window window= ((Activity) getContext()).getWindow();
//                WindowManager.LayoutParams lp = window.getAttributes();
//                lp.screenBrightness = brightness / 255f;
//                window.setAttributes(lp);
//            }

            currentBrightness = (int) brightness;
        }
    }

    private void setVolume(int volume) {
        AudioManager audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, AudioManager.FLAG_SHOW_UI);
        currentVolume = volume;
    }

    public void mute(boolean isMute) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(isMute ? 0 : 1, isMute ? 0 : 1);
        }
    }

    private Uri uri;

    private Context mContext;

    public VideoView(final Context context) {
        super(context);
        mContext = context;
        initVideoView();
    }

    public VideoView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initVideoView();
    }

    public VideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        initVideoView();
    }

    public void initVideoView() {
        logd("Initializing video view.");
        setOpaque(false);
        setAlpha(0);
        try {
            if (initilizeBrightness == 0) {
                initilizeBrightness = Settings.System.getInt(
                        getContext().getContentResolver(),
                        Settings.System.SCREEN_BRIGHTNESS);
            }

            pivotBrightness = initilizeBrightness;

            // 설정된 동영상의 밝기를 로딩
            // currentBrightness = getPrefs();

        } catch (Settings.SettingNotFoundException e) { LogUtils.e(e); }

        AudioManager audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        SWIPE_VOLUME_LANGE = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        if (currentVolume == -1) {
            currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        }

        videoHeight = 0;
        videoWidth = 0;
        setFocusable(false);
        setSurfaceTextureListener(surfaceTextureListener);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                boolean isAction = false;
                if (!isOrientationPortrait()) {
                    isAction = gestureDetector.onTouchEvent(event);
                }

                if (!isAction) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_UP:
                            if (getOnClickListener() != null)
                                getOnClickListener().onClick(VideoView.this);
                            break;
                    }
                }
                return true;
            }
        });
    }

    private boolean isOrientationPortrait() {
        if (getContext() instanceof Activity) {
            return ((Activity) getContext()).getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        }
        return true;
    }

    public int resolveAdjustedSize(int desiredSize, int measureSpec) {
        logd("Resolve called.");
        int result = desiredSize;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize =  MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                /* Parent says we can be as big as we want. Just don't be larger
                 * than max size imposed on ourselves.
                 */
                result = desiredSize;
                break;

            case MeasureSpec.AT_MOST:
                /* Parent says we can be as big as we want, up to specSize.
                 * Don't be larger than specSize, and don't be larger than
                 * the max size imposed on ourselves.
                 */
                result = Math.min(desiredSize, specSize);
                break;

            case MeasureSpec.EXACTLY:
                // No choice. Do what we are told.
                result = specSize;
                break;
        }
        return result;
    }

    public void setOnCompletionListener(MediaPlayer.OnCompletionListener listener) {
        onCompletionListener = listener;
    }

    public void setVideoPath(String path) {
        logd("Setting video path to: " + path);
        setVideoURI(Uri.parse(path));
    }

    public void setVideoURI(Uri _videoURI) {
        uri = _videoURI;
        requestLayout();
        invalidate();
        openVideo();
    }

    public void setSurfaceTexture(SurfaceTexture _surfaceTexture) {
        surfaceTexture = _surfaceTexture;
    }

    public void openVideo() {
        if ((uri == null) || (surfaceTexture == null)) {
            logd("Cannot open video, uri or surface is null number " + number);
            return;
        }
        // Tell the music playback service to pause
        // TODO: these constants need to be published somewhere in the framework.
//        Intent i = new Intent("com.android.music.musicservicecommand");
//        i.putExtra("command", "pause");
//        mContext.sendBroadcast(i);

        AudioManager audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        audioManager.requestAudioFocus(null, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        logd("Opening video.");
        release(false);
        try {
            surface = new Surface(surfaceTexture);
            logd("Creating media player number " + number);
            mediaPlayer = new MediaPlayer();
            logd("Setting surface.");
            mediaPlayer.setSurface(surface);
            logd("Setting data source.");
            mediaPlayer.setDataSource(mContext, uri);
            logd("Setting media player listeners.");
            mediaPlayer.setOnBufferingUpdateListener(bufferingUpdateListener);
            mediaPlayer.setOnCompletionListener(completeListener);
            mediaPlayer.setOnPreparedListener(preparedListener);
            mediaPlayer.setOnErrorListener(errorListener);
            mediaPlayer.setOnVideoSizeChangedListener(videoSizeChangedListener);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            logd("Preparing media player.");
            mediaPlayer.prepareAsync();
            currentState = STATE_PREPARING;
        } catch (IllegalStateException e) {
            currentState = STATE_ERROR;
            targetState = STATE_ERROR;
            Log.d(LOG_TAG, e.getMessage()); //TODO auto-generated catch block
        } catch (IOException e) {
            currentState = STATE_ERROR;
            targetState = STATE_ERROR;
            Log.d(LOG_TAG, e.getMessage()); //TODO auto-generated catch block
        }

        // 추가 - 비디오 음소거 제어
//        LogUtils.i("PrefUtils.isMuteVideoSound(getActivity()) : " + PrefUtils.isMuteVideoSound(mContext);
        mute(true);
    }

    public void stopPlayback() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }

        if (getProgress() != null) getProgress().setVisibility(View.GONE);
    }

    private void release(boolean cleartargetstate) {
        logd("Releasing media player.");
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
            currentState = STATE_IDLE;
            if (cleartargetstate) {
                targetState  = STATE_IDLE;
            }
            logd("Released media player.");
        } else {
            logd("Media player was null, did not release.");
        }
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {

        int i = getDefaultSize(this.videoWidth, widthMeasureSpec);
        int j = getDefaultSize(this.videoHeight, heightMeasureSpec);
        logd("Setting size: " + i + '/' + j + " for number ");
        setMeasuredDimension(i, j);

//        // Will resize the view if the video dimensions have been found.
//        // video dimensions are found after onPrepared has been called by MediaPlayer
//        logd("onMeasure number " + number);
//        int width = getDefaultSize(videoWidth, widthMeasureSpec);
//        int height = getDefaultSize(videoHeight, heightMeasureSpec);
//        if ((videoWidth > 0) && (videoHeight > 0)) {
//            if ((videoWidth * height) > (width * videoHeight)) {
//                logd("Image too tall, correcting.");
//                height = (width * videoHeight) / videoWidth;
//            } else if ((videoWidth * height) < (width * videoHeight)) {
//                logd("Image too wide, correcting.");
//                width = (height * videoWidth) / videoHeight;
//            } else {
//                logd("Aspect ratio is correct.");
//            }
//        }
//        logd("Setting size: " + width + '/' + height + " for number " + number);
//        setMeasuredDimension(width, height);
    }

    public boolean isPlaying() {
        return isInPlaybackState() && mediaPlayer.isPlaying();
    }

    public void start() {
        // This can potentially be called at several points, it will go through when all conditions are ready
        // 1. When setting the video URI
        // 2. When the surface becomes available
        // 3. From the activity
        if (isInPlaybackState()) {
            logd("Starting media player for number " + number);
            mediaPlayer.start();
            currentState = STATE_PLAYING;
        } else {
            logd("Could not start. Current state " + currentState);
        }
        targetState = STATE_PLAYING;
        if (getProgress() != null) getProgress().setVisibility(View.VISIBLE);
    }

    private boolean isInPlaybackState() {
        return ((mediaPlayer != null) &&
                (currentState != STATE_ERROR) &&
                (currentState != STATE_IDLE) &&
                (currentState != STATE_PREPARING));
    }

    // Listeners
    private MediaPlayer.OnBufferingUpdateListener bufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(final MediaPlayer mp, final int percent) {
            if (getProgress() != null) {
                getProgress().setVisibility(percent != 100 ? View.VISIBLE : View.GONE);
                setAlpha(percent != 100 ? 0 : 1);
            }
            currentBufferPercentage = percent;
        }
    };

    private MediaPlayer.OnCompletionListener completeListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(final MediaPlayer mp) {
            currentState = STATE_PLAYBACK_COMPLETED;
            targetState = STATE_PLAYBACK_COMPLETED;
            logd("Video completed number " + number);
            surface.release();
            if (onCompletionListener != null) {
                onCompletionListener.onCompletion(mp);
            }
            if (getOnCompletionListener() != null) getOnCompletionListener().onCompletion(mp);
        }
    };

    private MediaPlayer.OnPreparedListener preparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(final MediaPlayer mp) {
            currentState = STATE_PREPARED;
            logd("Video prepared for " + number);
            videoWidth = mp.getVideoWidth();
            videoHeight = mp.getVideoHeight();
            requestLayout();
            invalidate();
            if ((videoWidth != 0) && (videoHeight != 0)) {
                logd("Video size for number " + number + ": " + videoWidth + '/' + videoHeight);
                if (targetState == STATE_PLAYING) {
                    mediaPlayer.start();
                }
            } else {
                if (targetState == STATE_PLAYING) {
                    mediaPlayer.start();
                }
            }

            if (mContext != null && mContext instanceof Activity) {
                ((Activity) mContext).getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }

            if (getOnPreparedListener() != null) getOnPreparedListener().onPrepared(mp);
            if (getProgress() != null) getProgress().setVisibility(View.GONE);
            setAlpha(1f);
        }
    };

    private MediaPlayer.OnVideoSizeChangedListener videoSizeChangedListener = new MediaPlayer.OnVideoSizeChangedListener() {
        @Override
        public void onVideoSizeChanged(final MediaPlayer mp, final int width, final int height) {
            logd("Video size changed " + width + '/' + height + " number " + number);
        }
    };

    private MediaPlayer.OnErrorListener errorListener = new MediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(final MediaPlayer mp, final int what, final int extra) {
            currentState = STATE_ERROR;
            targetState = STATE_ERROR;
            Log.e(LOG_TAG, "There was an error during video playback.");

            if (getOnErrorListener() != null) getOnErrorListener().onError(mp, what, extra);
            return true;
        }
    };

    SurfaceTextureListener surfaceTextureListener = new SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(final SurfaceTexture surface, final int width, final int height) {
            logd("Surface texture now avaialble.");
            surfaceTexture = surface;
            openVideo();
        }

        @Override
        public void onSurfaceTextureSizeChanged(final SurfaceTexture surface, final int width, final int height) {
            logd("Resized surface texture: " + width + '/' + height);
            surfaceWidth = width;
            surfaceHeight = height;
            boolean isValidState =  (targetState == STATE_PLAYING);
            boolean hasValidSize = (videoWidth == width && videoHeight == height);
            if (mediaPlayer != null && isValidState && hasValidSize) {
                start();
            }
        }

        @Override
        public boolean onSurfaceTextureDestroyed(final SurfaceTexture surface) {
            logi("Destroyed surface number " + number);
            stopPlayback();
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(final SurfaceTexture surface) {
            logi("onSurfaceTextureUpdated " + currentState);
        }
    };

    public void destroy() {
//        if (isOrientationPortrait()) {
//            float brightness = currentBrightness;
//            setBrightness(initilizeBrightness);
//            initilizeBrightness = 0;
//            currentBrightness = brightness;
//        }

        // 현재 비디오 밝기 설정 저장
        // savePrefs -> currentBrightness;

        stopPlayback();
        release(true);

        if (mContext != null && mContext instanceof Activity) {
            ((Activity) mContext).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    /** GETTER, SETTER */

    public View getProgress() {
        return progress;
    }

    public void setProgress(View progress) {
        this.progress = progress;
    }

    public MediaPlayer.OnPreparedListener getOnPreparedListener() {
        return onPreparedListener;
    }

    public void setOnPreparedListener(MediaPlayer.OnPreparedListener onPreparedListener) {
        this.onPreparedListener = onPreparedListener;
    }

    public MediaPlayer.OnCompletionListener getOnCompletionListener() {
        return onCompletionListener;
    }

    public MediaPlayer.OnErrorListener getOnErrorListener() {
        return onErrorListener;
    }

    public void setOnErrorListener(MediaPlayer.OnErrorListener onErrorListener) {
        this.onErrorListener = onErrorListener;
    }

    public OnSwipeListener getOnSwipeListener() {
        return onSwipeListener;
    }

    public void setOnSwipeListener(OnSwipeListener onSwipeListener) {
        this.onSwipeListener = onSwipeListener;
    }

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    @Override
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnSwipeListener {

        /**
         * 가로 스와이프
         * @param offset left -1, right +1
         */
        void onFlingHorizontal(int offset);

        /**
         * 세로 스와이프
         * @param offset down +1, up -1
         */
        void onFlingVertical(int offset);
    }

    public static abstract class SwipeListenerAdapter implements OnSwipeListener {
        public void onFlingHorizontal(int offset) { }
        public void onFlingVertical(int offset) { }
    }

    private void logi(String msg) {
//        Log.i("uno", LOG_TAG + msg);
    }

    private void logd(String msg) {
//        Log.d("uno", LOG_TAG + msg);
    }
}