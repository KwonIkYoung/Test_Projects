package video.lib;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * @author KIY.
 * @Since 2016. 7. 26..
 */
public class DepthPageTransformer implements ViewPager.PageTransformer {

	public void transformPage(View view, float position) {
		int pageHeight = view.getHeight();

		if (position < -1) {
			if (view.getVisibility() == View.GONE) {
				view.setVisibility(View.VISIBLE);
			}
		} else if (position <= 0) {
			view.setTranslationY(1);
			view.setScaleY(1);

			if (view.getVisibility() == View.GONE) {
				view.setVisibility(View.VISIBLE);
			}
		} else if (position < 1) {
			view.setTranslationY(pageHeight * -position);
			view.setScaleY(1);

			if (view.getVisibility() == View.GONE) {
				view.setVisibility(View.VISIBLE);
			}
		} else {
			view.setVisibility(View.GONE);
		}
	}
}