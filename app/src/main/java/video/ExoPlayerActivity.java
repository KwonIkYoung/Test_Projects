package video;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaCodec;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.exoplayer.ExoPlaybackException;
import com.google.android.exoplayer.ExoPlayer;
import com.google.android.exoplayer.MediaCodecAudioTrackRenderer;
import com.google.android.exoplayer.MediaCodecSelector;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer;
import com.google.android.exoplayer.extractor.ExtractorSampleSource;
import com.google.android.exoplayer.upstream.Allocator;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.DefaultAllocator;
import com.google.android.exoplayer.upstream.DefaultUriDataSource;
import com.google.android.exoplayer.util.PlayerControl;
import com.google.android.exoplayer.util.Util;
import com.kiy.app.test_project.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import util.LogUtils;
import video.lib.DepthPageTransformer;
import view.VerticalViewPager;

public class ExoPlayerActivity extends AppCompatActivity {
	@BindView(R.id.viewpager)
	VerticalViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video_view);
		ButterKnife.bind(this);

		Toast.makeText(ExoPlayerActivity.this, "Class : "+ ExoPlayerActivity.class.getSimpleName() , Toast.LENGTH_SHORT).show();
		mViewPager.setAdapter(new VerticalAdapter(this));
		mViewPager.setPageTransformer(true, new DepthPageTransformer());
	}



	private class VerticalAdapter extends PagerAdapter {
		private Context mContext;
		private LayoutInflater mInflater;
		public VerticalAdapter(Context context) {
			mContext = context;
			mInflater = LayoutInflater.from(mContext);
		}

		@Override
		public int getCount() {
			return 5;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View view = mInflater.inflate(R.layout.layout_verti, null);

			ViewPager pager = (ViewPager)view.findViewById(R.id.pager);
			pager.setAdapter(new ExoAdapter(mContext));

			container.addView(view);
			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			if(object instanceof View){
				ViewPager pager = (ViewPager)((View)object).findViewById(R.id.pager);
				Log.d("KIY", " pager = " +pager);
				if (pager != null) {
					ExoPlayer exoPlayer = ((ExoAdapter)pager.getAdapter()).exoPlayer;
					Log.d("KIY", " exoPlayer = " +exoPlayer   );
					if (exoPlayer != null) {
						exoPlayer.release();
					}
				}
			}
			container.removeView((View)object);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}
	}

	private class ExoAdapter extends PagerAdapter implements ExoPlayer.Listener,AudioManager.OnAudioFocusChangeListener,View.OnClickListener{
//		ManifestFetcher.ManifestCallback<HlsPlaylist>,
//		HlsSampleSource.EventListener

		private Context mContext;
		private LayoutInflater mInflater;


		//- exo player
		private static final int BUFFER_SEGMENT_COUNT = 160;
		private static final int BUFFER_SEGMENT_SIZE = 64 * 1024;
		private static final int MAIN_BUFFER_SEGMENT = 254;
		public static final int TYPE_VIDEO = 0;

		private SurfaceView surfaceView;
		public ExoPlayer exoPlayer;
		private PlayerControl playerControl;
		private String video_url = "http://www.html5videoplayer.net/videos/toystory.mp4";
		private String userAgent;

		public ExoAdapter(Context context) {
			mContext = context;
			mInflater = LayoutInflater.from(mContext);
		}

		private void init(View view){
			surfaceView = (SurfaceView)view.findViewById(R.id.surface_view);
			exoPlayer = ExoPlayer.Factory.newInstance(2);
			playerControl = new PlayerControl(exoPlayer);
//			am = (AudioManager)mContext.getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
//			mainHandler = new Handler();
			userAgent = Util.getUserAgent(mContext, "MainActivity"); //useragent required for hls

			// Build the video/id3 renderers.
			Allocator allocator = new DefaultAllocator(BUFFER_SEGMENT_SIZE);
			DataSource dataSource = new DefaultUriDataSource(mContext, null, userAgent);
			ExtractorSampleSource sampleSource = new ExtractorSampleSource(Uri.parse(video_url), dataSource, allocator, BUFFER_SEGMENT_COUNT * BUFFER_SEGMENT_SIZE);
			MediaCodecVideoTrackRenderer videoRenderer = new MediaCodecVideoTrackRenderer(mContext, sampleSource, MediaCodecSelector.DEFAULT, MediaCodec.VIDEO_SCALING_MODE_SCALE_TO_FIT);
			MediaCodecAudioTrackRenderer audioRenderer = new MediaCodecAudioTrackRenderer(sampleSource, MediaCodecSelector.DEFAULT);
			exoPlayer.prepare(videoRenderer, audioRenderer);
//			 4. Pass the surface to the video renderer.
			exoPlayer.sendMessage(videoRenderer, MediaCodecVideoTrackRenderer.MSG_SET_SURFACE, surfaceView.getHolder().getSurface());
//			 5. Start playback.
			exoPlayer.setPlayWhenReady(true);
			exoPlayer.addListener(new ExoPlayer.Listener() {
				@Override
				public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
					Log.i("KIY" , ">>onPlayerStateChanged :  " + playWhenReady + " /  state : " +playbackState);
				}

				@Override
				public void onPlayWhenReadyCommitted() {

				}

				@Override
				public void onPlayerError(ExoPlaybackException error) {

				}
			});
			playerControl.start();
		}

		@Override
		public int getCount() {
			return 10;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View view;
			LogUtils.d("instantiateItem : " + position);

			switch (position) {
				case 1:
				case 3:
				case 5:
				case 9:
					view = mInflater.inflate(R.layout.layout_class_video, null);
					init(view);
					break;
				default:
					view = mInflater.inflate(R.layout.layout_blank_view, null);
					break;
			}
			container.addView(view);

			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
//			super.destroyItem(container, position, object);
			container.removeView((View) object);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
//			return false;
		}

		@Override
		public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

		}

		@Override
		public void onPlayWhenReadyCommitted() {

		}

		@Override
		public void onPlayerError(ExoPlaybackException error) {

		}

		@Override
		public void onAudioFocusChange(int focusChange) {

		}

		@Override
		public void onClick(View v) {

		}
	}
}
