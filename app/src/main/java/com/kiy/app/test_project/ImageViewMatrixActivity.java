package com.kiy.app.test_project;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ImageViewMatrixActivity extends AppCompatActivity {

	Button resetButton, reversxButton, reversyButton, upscaleButton, downscaleButton, moveleftButton, moverightButton, skewButton;

	ImageView iv;
	Bitmap bitmap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_view_matrix);

		iv = (ImageView) findViewById(R.id.imageViewForMatrix);
		iv.setScaleType(ImageView.ScaleType.MATRIX);//혹은 현재 xml에서 android:scaleType="matrix" 처리

		bitmap = BitmapFactory.decodeResource(getResources(),  R.drawable.test);
		iv.setImageBitmap(bitmap);
		// iv.setImageBitmap(setReverse());

		// y축을 기준으로 x축 바꾸기
		resetButton = (Button) findViewById(R.id.reset);
		resetButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				bitmap = BitmapFactory.decodeResource(getResources(),  R.drawable.test);
				iv.setImageBitmap(bitmap);
			}
		});


		// y축을 기준으로 x축 바꾸기
		reversxButton = (Button) findViewById(R.id.reversx);
		reversxButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//setReverse();
				iv.setImageBitmap(setReverse());
			}
		});

		// x축을 기준으로 y축 바꾸기
		reversyButton = (Button) findViewById(R.id.reversy);
		reversyButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				iv.setImageBitmap(setReverseY());
			}
		});


		upscaleButton = (Button) findViewById(R.id.upscale);
		upscaleButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				iv.setImageBitmap(setScale(1));
			}
		});

		downscaleButton = (Button) findViewById(R.id.downscale);
		downscaleButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				iv.setImageBitmap(setScale(2));
			}
		});

		moveleftButton = (Button) findViewById(R.id.moveleft);
		moveleftButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//iv.setImageBitmap(setMove(1));
				setMove(1);
			}
		});

		moverightButton = (Button) findViewById(R.id.moveright);
		moverightButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//iv.setImageBitmap(setMove(2));
				setMove(2);
			}
		});


		skewButton = (Button) findViewById(R.id.skew);
		skewButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				iv.setImageBitmap(setSkew());
				//setSkew();
			}
		});



	}

	private Bitmap setReverse(){
		//private void setReverse(){
		Bitmap rtnBitmap;
		Matrix matrix = new Matrix();
		matrix.postScale(-1, 1); //y 축을 대칭으로 x를 반대로 한다.

		//iv.setImageMatrix(matrix);
		//기존 이미지의 크기(height & width)를 구한다.
		int w	= bitmap.getWidth();
		int h	= bitmap.getHeight();

		//앞에서 제작된 y축 대칭 매트릭스를 이미지에 적용한다.
		bitmap	= rtnBitmap	= Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, false);

		return rtnBitmap;
	}

	private Bitmap setReverseY(){
		Bitmap rtnBitmap;
		Matrix matrix = new Matrix();
		matrix.postScale(1, -1); //x 축을 대칭으로 y를 반대로 한다.

		//기존 이미지의 크기(height & width)를 구한다.
		//bitmap	= BitmapFactory.decodeResource(getResources(), R.drawable.sample_image_jpg);
		int w	= bitmap.getWidth();
		int h	= bitmap.getHeight();

		//앞에서 제작된 y축 대칭 매트릭스를 이미지에 적용한다.
		bitmap	= rtnBitmap	= Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, false);
		return rtnBitmap;
	}

	private Bitmap setScale(int flag){
		Bitmap rtnBitmap;
		Matrix matrix = new Matrix();
		switch(flag){
			case 1://확대
				matrix.postScale(1.1f, 1.1f); //
				break;
			case 2://축소
				matrix.postScale(0.9f, 0.9f); //
				break;
		}

		//기존 이미지의 크기(height & width)를 구한다.
		int w	= bitmap.getWidth();
		int h	= bitmap.getHeight();

		//앞에서 제작된 y축 대칭 매트릭스를 이미지에 적용한다.
		bitmap	= rtnBitmap	= Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, false);
		return rtnBitmap;
	}

	//private Bitmap setMove(int flag){
	private void setMove(int flag){
		//Bitmap rtnBitmap;
		Matrix matrix = new Matrix();
		switch(flag){
			case 1://left
				Log.i("setMove", "left");
				matrix.postTranslate(0, 0); //
				break;
			case 2://right
				Log.i("setMove", "right");
				int bWidth = bitmap.getWidth()/2;
				int imgWidth = iv.getWidth();
				matrix.postTranslate(-imgWidth+20, 0); //
				break;
		}
//		img.getWidth()/2 - ((bWidth * f[0]) / 2)

		//기존 이미지의 크기(height & width)를 구한다.
		//int w	= bitmap.getWidth();
		//int h	= bitmap.getHeight();

		//앞에서 제작된 y축 대칭 매트릭스를 이미지에 적용한다.
		iv.setImageMatrix(matrix);//이미지에 매트릭스 적용
		//bitmap	= rtnBitmap	= Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, false);
		//return rtnBitmap;
	}


	//private void setSkew(){
	private Bitmap setSkew(){
		Bitmap rtnBitmap;
		Matrix matrix = new Matrix();
		matrix.postSkew(0.88f, -0.16f); //

		//기존 이미지의 크기(height & width)를 구한다.
		int w	= bitmap.getWidth();
		int h	= bitmap.getHeight();

		//iv.setImageMatrix(matrix);//이미지에 매트릭스 적용
		bitmap	= rtnBitmap	= Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, false);
		return rtnBitmap;
	}
}
