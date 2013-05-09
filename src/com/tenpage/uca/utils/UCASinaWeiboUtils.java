package com.tenpage.uca.utils;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.tenpage.uca.LoginSinaWeiboActivity;
import com.tenpage.uca.entity.SinaWeiboReturnData;
import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.api.StatusesAPI;
import com.weibo.sdk.android.net.RequestListener;

/**
 * @author fanshuo
 * @date 2013-5-9 上午10:31:26
 */
public class UCASinaWeiboUtils {
	/**
	 * 进行授权
	 * @param context
	 */
	public static void login(Context context) {
		context.startActivity(new Intent(context, LoginSinaWeiboActivity.class));
	}

	/**
	 * 检查是否已经授权
	 * @param context
	 * @return
	 */
	public static boolean hasAlreadyLogin(Context context) {
		try {
			String access_token = PreferenceManager.getString(context,
					PreferenceManager.KEY_LOGIN_QQ_ACCESS_TOKEN, "");
			String openid = PreferenceManager.getString(context,
					PreferenceManager.KEY_LOGIN_QQ_OPEN_ID, "");
			if (!TextUtils.isEmpty(openid) && !TextUtils.isEmpty(access_token)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 获取本地保存的授权信息
	 * 
	 * @param context
	 * @return
	 */
	public static SinaWeiboReturnData getLoginData(Context context) {
		SinaWeiboReturnData data = new SinaWeiboReturnData();
		String access_token = PreferenceManager.getString(context,
				PreferenceManager.KEY_WEIBO_SINA_TOKEN, "");
		Long expires_time = PreferenceManager.getLong(context,
				PreferenceManager.KEY_WEIBO_SINA_EXPIRES_TIME, 0L);
		data.setAccess_token(access_token);
		data.setExpires_time(expires_time);
		return data;
	}

	/**
	 * 发送一条微博
	 * @param context
	 * @param content
	 * @param listener
	 */
	public static void shareWeibo(final Context context, String content,
			RequestListener listener) {
		Long sinaExpiresTime = PreferenceManager.getLong(context,
				PreferenceManager.KEY_WEIBO_SINA_EXPIRES_TIME, 0L);
		// 如果超过了过期时间或尚未授权，就进行授权
		if (System.currentTimeMillis() > sinaExpiresTime) {
			login(context);
			ActivityUtils.showCenterToast(context, "授权时间已过期，需要重新授权", Toast.LENGTH_SHORT);
		} else {
			String access_token = PreferenceManager.getString(context,
					PreferenceManager.KEY_WEIBO_SINA_TOKEN, "");
			Long expires_in = PreferenceManager.getLong(context,
					PreferenceManager.KEY_WEIBO_SINA_EXPIRES_IN, 0L);
			Oauth2AccessToken accessToken = new Oauth2AccessToken(access_token,
					expires_in.toString());
			StatusesAPI api = new StatusesAPI(accessToken);
			api.update(content, "0.0", "0.0", listener);
		}
	}
}
