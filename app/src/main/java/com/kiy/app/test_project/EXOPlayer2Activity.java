package com.kiy.app.test_project;

import android.content.Context;
import android.media.MediaCodec;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;

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

public class EXOPlayer2Activity extends AppCompatActivity {
	private static final int BUFFER_SEGMENT_COUNT = 160;
	private static final int BUFFER_SEGMENT_SIZE = 64 * 1024;
	private static final int MAIN_BUFFER_SEGMENT = 254;
	public static final int TYPE_VIDEO = 0;

	private Context mContext;

	private SurfaceView surfaceView;
	private ExoPlayer exoPlayer;
	private PlayerControl playerControl;
	private String video_url = "http://www.html5videoplayer.net/videos/toystory.mp4";
	private String userAgent;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exoplayer2);
		mContext = this;

		surfaceView = (SurfaceView)findViewById(R.id.surface_view);
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

		playerControl.start();
	}

}
