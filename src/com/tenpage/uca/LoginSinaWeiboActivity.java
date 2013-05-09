package com.tenpage.uca;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.tenpage.uca.utils.ActivityUtils;
import com.tenpage.uca.utils.PreferenceManager;
import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.Weibo;
import com.weibo.sdk.android.WeiboAuthListener;
import com.weibo.sdk.android.WeiboDialogError;
import com.weibo.sdk.android.WeiboException;
import com.weibo.sdk.android.sso.SsoHandler;

/**
 * @author fanshuo
 * @date 2013-5-9 上午11:28:20
 */
public class LoginSinaWeiboActivity extends Activity {

	private SsoHandler mSsoHandler;
	public static final String SinaWeibo_APPKEY = "1158881934";//1770632969
	public static final String SinaWeibo_APPSECRET = "070b33b4ecd7a5088b785699eb3d4542";//69f141e65bfab9e2b1abc382c6780687
	public static final String SinaWeibo_RedirectUrl = "http://www.weibo.com";
	public static final String SCOPE = "email,direct_messages_read,direct_messages_write," +
			"friendships_groups_read,friendships_groups_write,statuses_to_me_read," +
				"follow_app_official_microblog";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		authSina();
	}
	
	/**
	 * 新浪微博授权
	 */
	private void authSina() {
		Weibo mWeibo = Weibo.getInstance(SinaWeibo_APPKEY,
				SinaWeibo_APPSECRET);
		mWeibo.setupConsumerConfig(SinaWeibo_APPKEY,
				SinaWeibo_RedirectUrl);
		mSsoHandler = new SsoHandler(this, mWeibo);
		mSsoHandler.authorize(new WeiboAuthListener() {
			@Override
			public void onWeiboException(WeiboException e) {
				Toast.makeText(LoginSinaWeiboActivity.this,
						"授权出错 : " + e.getMessage(), Toast.LENGTH_LONG).show();
				LoginSinaWeiboActivity.this.finish();
			}
			
			@Override
			public void onError(WeiboDialogError e) {
				Toast.makeText(LoginSinaWeiboActivity.this,
						"授权出错 : " + e.getMessage(), Toast.LENGTH_LONG).show();
				LoginSinaWeiboActivity.this.finish();
			}

			@Override
			public void onComplete(Bundle values) {
				String token = values.getString("access_token");
				String expires_in = values.getString("expires_in");
				Oauth2AccessToken accessToken = new Oauth2AccessToken(token, expires_in);
				PreferenceManager.putString(LoginSinaWeiboActivity.this,
						PreferenceManager.KEY_WEIBO_SINA_TOKEN, token);
				PreferenceManager
						.putLong(LoginSinaWeiboActivity.this,
								PreferenceManager.KEY_WEIBO_SINA_EXPIRES_IN,
								Long.parseLong(expires_in));
				// 根据EXPIRE_IN计算出具体在哪个时间过期
				Long expiresTime = System.currentTimeMillis()
						+ Long.parseLong(expires_in) * 1000;
				PreferenceManager.putLong(LoginSinaWeiboActivity.this,
						PreferenceManager.KEY_WEIBO_SINA_EXPIRES_TIME,
						expiresTime);
				ActivityUtils.showCenterToast(LoginSinaWeiboActivity.this,
						"授权成功", Toast.LENGTH_SHORT);
				LoginSinaWeiboActivity.this.finish();
			}

			@Override
			public void onCancel() {
				Toast.makeText(LoginSinaWeiboActivity.this, "授权取消",
						Toast.LENGTH_LONG).show();
				LoginSinaWeiboActivity.this.finish();
			}
		});
	}

	@Override
	protected void onActivityResult(final int pRequestCode, int pResultCode,
			final Intent pData) {
		super.onActivityResult(pRequestCode, pResultCode, pData);
		if (mSsoHandler != null) {
			mSsoHandler.authorizeCallBack(pRequestCode, pResultCode, pData);
		}
	}

}
