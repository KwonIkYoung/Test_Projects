package video;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.kiy.app.test_project.R;
import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import util.LogUtils;
import video.lib.DepthPageTransformer;
import view.VerticalViewPager;

public class UniversalVideoViewActivity extends AppCompatActivity {

	@BindView(R.id.viewpager)
	VerticalViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video_view);
		ButterKnife.bind(this);


		Toast.makeText(UniversalVideoViewActivity.this, "Class : "+UniversalVideoViewActivity.class.getSimpleName() , Toast.LENGTH_SHORT).show();
		mViewPager.setAdapter(new VerticalAdapter(this));
		mViewPager.setPageTransformer(true, new DepthPageTransformer());
	}

	private class VerticalAdapter extends PagerAdapter{
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
			pager.setAdapter(new UvvAdapter(mContext));

			container.addView(view);
			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View)object);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}
	}

	private class UvvAdapter extends PagerAdapter {

		private Context mContext;
		private LayoutInflater mInflater;

		public UvvAdapter(Context context) {
			mContext = context;
			mInflater = LayoutInflater.from(mContext);
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
					view = mInflater.inflate(R.layout.layout_universal_video, null);
					final UniversalVideoView videoView = (UniversalVideoView) view.findViewById(R.id.videoView);
					final UniversalMediaController mMediaController = (UniversalMediaController) view.findViewById(R.id.media_controller);
					videoView.setMediaController(mMediaController);
					videoView.setVideoURI(Uri.parse("http://www.html5videoplayer.net/videos/toystory.mp4"));
					videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
						@Override
						public void onPrepared(MediaPlayer mp) {
							videoView.start();
						}
					});
					videoView.setVideoViewCallback(new UniversalVideoView.VideoViewCallback() {
						@Override
						public void onScaleChange(boolean isFullscreen) {
//							this.isFullscreen = isFullscreen;
//							if (isFullscreen) {
//								ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
//								layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//								layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
//								mVideoLayout.setLayoutParams(layoutParams);
//								//GONE the unconcerned views to leave room for video and controller
//								mBottomLayout.setVisibility(View.GONE);
//							} else {
//								ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
//								layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//								layoutParams.height = this.cachedHeight;
//								mVideoLayout.setLayoutParams(layoutParams);
//								mBottomLayout.setVisibility(View.VISIBLE);
//							}
						}

						@Override
						public void onPause(MediaPlayer mediaPlayer) { // Video pause
							LogUtils.d("onPause UniversalVideoView callback");
						}

						@Override
						public void onStart(MediaPlayer mediaPlayer) { // Video start/resume to play
							LogUtils.d("onStart UniversalVideoView callback");
						}

						@Override
						public void onBufferingStart(MediaPlayer mediaPlayer) {// steam start loading
							LogUtils.d("onBufferingStart UniversalVideoView callback");
						}

						@Override
						public void onBufferingEnd(MediaPlayer mediaPlayer) {// steam end loading
							LogUtils.d("onBufferingEnd UniversalVideoView callback");
						}

					});

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
	}
}
