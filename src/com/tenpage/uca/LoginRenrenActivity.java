package com.tenpage.uca;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.renren.api.connect.android.Renren;
import com.renren.api.connect.android.exception.RenrenAuthError;
import com.renren.api.connect.android.view.RenrenAuthListener;
import com.tenpage.uca.utils.ActivityUtils;

/**
 * @author fanshuo
 * @date 2013-5-9 下午3:20:45
 */
public class LoginRenrenActivity extends Activity {
	public static final String API_KEY = "6b1016db20c540e78bd1b20be4c707a3";
	public static final String SECRET_KEY = "4723a695c09e4ddebbe8d87393d95fb4";
	public static final String APP_ID = "105381";
	private Renren renren;
	private Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		renren = new Renren(API_KEY, SECRET_KEY, APP_ID, this);
		renren.init(this);
		RenrenAuthListener listener = new RenrenAuthListener() {
			@Override
			public void onComplete(final Bundle values) {
				handler.post(new Runnable() {
					@Override
					public void run() {
						ActivityUtils.showCenterToast(LoginRenrenActivity.this,
								"授权成功", Toast.LENGTH_SHORT);
						LoginRenrenActivity.this.finish();
					}
				});
			}
			@Override
			public void onRenrenAuthError(RenrenAuthError renrenAuthError) {
				handler.post(new Runnable() {
					@Override
					public void run() {
						ActivityUtils.showCenterToast(LoginRenrenActivity.this,
								"授权出错", Toast.LENGTH_SHORT);
						LoginRenrenActivity.this.finish();
					}
				});
			}
			@Override
			public void onCancelLogin() {
				handler.post(new Runnable() {
					@Override
					public void run() {
						ActivityUtils.showCenterToast(LoginRenrenActivity.this,
								"取消授权", Toast.LENGTH_SHORT);
						LoginRenrenActivity.this.finish();
					}
				});
			}
			@Override
			public void onCancelAuth(Bundle values) {
				handler.post(new Runnable() {
					@Override
					public void run() {
						ActivityUtils.showCenterToast(LoginRenrenActivity.this,
								"取消授权", Toast.LENGTH_SHORT);
						LoginRenrenActivity.this.finish();
					}
				});
			}
		};
		renren.authorize(LoginRenrenActivity.this, null, listener, 1);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (renren != null) {
			renren.authorizeCallback(requestCode, resultCode, data);
		}
	}

}
