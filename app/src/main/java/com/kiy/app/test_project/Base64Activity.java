package com.kiy.app.test_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import util.LogUtils;

public class Base64Activity extends AppCompatActivity {

	@BindView(R.id.tv_result)
	TextView tvResult;
	@BindView(R.id.et_base64_test_txt)
	EditText etBase64TestTxt;
	@BindView(R.id.btn_result)
	Button btnResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base64);
		ButterKnife.bind(this);
	}

	@OnClick(R.id.btn_result)
	public void onClick() {
		String txt = etBase64TestTxt.getText().toString();
		LogUtils.d("Onclick : " + txt);
		try {
			byte[] data = txt.getBytes("UTF-8");
			String base64Encode = Base64.encodeToString(data, Base64.NO_WRAP);
			tvResult.setText(base64Encode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
