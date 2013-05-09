package com.tenpage.uca;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.tenpage.uca.utils.ActivityUtils;
import com.tenpage.uca.utils.PreferenceManager;

public class LoginQqActivity extends Activity {

	private Tencent mTencent;
	private static final String APP_ID = "100415388";
	private static final String SCOPE = "all";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTencent = Tencent.createInstance(APP_ID, this);
		qqLogin(null);
	}

	public void qqLogin(View v) {
		if (!mTencent.isSessionValid()) {
			IUiListener listener = new BaseUiListener();
			mTencent.login(this, SCOPE, listener);
		} else {
//			mTencent.logout(this);
			ActivityUtils.showCenterToast(this, "已经授权过了", Toast.LENGTH_SHORT);
		}
	}

	private class BaseUiListener implements IUiListener {

		@Override
		public void onComplete(JSONObject response) {
			doComplete(response);
		}

		protected void doComplete(JSONObject values) {
			// 保存到SharedPreferences
			String access_token = values.optString("access_token");
			String openid = values.optString("openid");
			String expires_in = values.optString("expires_in");
			Long expires_time = System.currentTimeMillis()
					+ Long.parseLong(expires_in) * 1000;
			PreferenceManager.putString(LoginQqActivity.this,
					PreferenceManager.KEY_LOGIN_QQ_ACCESS_TOKEN, access_token);
			PreferenceManager.putString(LoginQqActivity.this,
					PreferenceManager.KEY_LOGIN_QQ_OPEN_ID, openid);
			PreferenceManager.putLong(LoginQqActivity.this,
					PreferenceManager.KEY_LOGIN_QQ_EXPIRES_TIME, expires_time);
			LoginQqActivity.this.finish();
		}

		@Override
		public void onError(UiError e) {
			ActivityUtils.showCenterToast(LoginQqActivity.this, "onError----"
					+ "code:" + e.errorCode + ", msg:" + e.errorMessage
					+ ", detail:" + e.errorDetail, Toast.LENGTH_SHORT);
			LoginQqActivity.this.finish();
		}

		@Override
		public void onCancel() {
			ActivityUtils.showCenterToast(LoginQqActivity.this, "取消授权",
					Toast.LENGTH_SHORT);
			LoginQqActivity.this.finish();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		mTencent.onActivityResult(requestCode, resultCode, data);
	}

}
