package com.kiy.app.test_project;

import android.lib.recaptcha.ReCaptcha;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CaptchaActivity extends AppCompatActivity implements View.OnClickListener, ReCaptcha.OnShowChallengeListener, ReCaptcha.OnVerifyAnswerListener {
//	private static final String PUBLIC_KEY = "6LcPWugSAAAAAC-MP5sg6wp_CQiyxHvPvkQvVlVf";
//	private static final String PRIVATE_KEY = "6LcPWugSAAAAALWMp-gg9QkykQQyO6ePBSUk-Hjg";
	private static final String PUBLIC_KEY = "6Lc6rCYTAAAAAE9szsLCG3KCE-Y6hUqPStxHzary";
	private static final String PRIVATE_KEY = "6Lc6rCYTAAAAALTIiBCGOf2C2b-2M4ynzkD1M5P5";
	@BindView(R.id.recaptcha)
	ReCaptcha reCaptcha;
	@BindView(R.id.progress)
	ProgressBar progress;
	@BindView(R.id.answer)
	EditText answer;
	@BindView(R.id.verify)
	Button verify;
	@BindView(R.id.reload)
	Button reload;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_captcha);
		ButterKnife.bind(this);

		verify.setOnClickListener(this);
		reload.setOnClickListener(this);
		showChallenge();
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.verify:
				this.verifyAnswer();
				break;
			case R.id.reload:
				this.showChallenge();
				break;
		}
	}

	@Override
	public void onChallengeShown(boolean shown) {
		this.progress.setVisibility(View.GONE);

		if (shown) {
			// If a CAPTCHA is shown successfully, displays it for the user to enter the words
			this.reCaptcha.setVisibility(View.VISIBLE);
		} else {
			Toast.makeText(this, R.string.show_error, Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onAnswerVerified(boolean success) {
		if (success) {
			Toast.makeText(this, R.string.verification_success, Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, R.string.verification_failed, Toast.LENGTH_SHORT).show();
		}

		// (Optional) Shows the next CAPTCHA
		this.showChallenge();
	}

	private void showChallenge() {
		// Displays a progress bar while downloading CAPTCHA
		this.progress.setVisibility(View.VISIBLE);
		this.reCaptcha.setVisibility(View.GONE);

		this.reCaptcha.setLanguageCode("ko");
		this.reCaptcha.showChallengeAsync(CaptchaActivity.PUBLIC_KEY, this);
	}

	private void verifyAnswer() {
		if (TextUtils.isEmpty(this.answer.getText())) {
			Toast.makeText(this, R.string.instruction, Toast.LENGTH_SHORT).show();
		} else {
			// Displays a progress bar while submitting the answer for verification
			this.progress.setVisibility(View.VISIBLE);
			this.reCaptcha.verifyAnswerAsync(CaptchaActivity.PRIVATE_KEY, this.answer.getText().toString(), this);
		}
	}
}
