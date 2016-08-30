package video;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kiy.app.test_project.R;
import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import util.LogUtils;
import video.lib.DepthPageTransformer;
import video.view.VideoView;
import view.VerticalViewPager;

public class VideoViewActivity extends AppCompatActivity {
	@BindView(R.id.viewpager)
	VerticalViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video_view);
		ButterKnife.bind(this);

		Toast.makeText(VideoViewActivity.this, "Class : "+VideoViewActivity.class.getSimpleName() , Toast.LENGTH_SHORT).show();
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
					view = mInflater.inflate(R.layout.layout_class_video, null);
					VideoView videoView = (VideoView)view.findViewById(R.id.videoView);
					videoView.setVideoPath("http://www.html5videoplayer.net/videos/toystory.mp4");
					videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
						@Override
						public void onPrepared(MediaPlayer mp) {
							mp.start();
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
