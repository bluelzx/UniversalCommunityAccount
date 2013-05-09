package com.tenpage.uca.utils;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.tenpage.uca.LoginSinaWeiboActivity;
import com.tenpage.uca.entity.SinaWeiboReturnData;
import com.weibo.sdk.android.Oauth2AccessToken;

/**
 * @author fanshuo
 * @date 2013-5-9 上午10:31:26
 */
public class UCASinaWeiboUtils {

	/**
	 * 进行授权
	 * 
	 * @param context
	 */
	public static void login(Context context) {
		context.startActivity(new Intent(context, LoginSinaWeiboActivity.class));
	}

	/**
	 * 检查是否已经授权
	 * 
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

	public static void shareWeibo(Context context, String content) {
		String access_token = PreferenceManager.getString(context,
				PreferenceManager.KEY_WEIBO_SINA_TOKEN, "");
		Long expires_in = PreferenceManager.getLong(context,
				PreferenceManager.KEY_WEIBO_SINA_EXPIRES_IN, 0L);
		Oauth2AccessToken accessToken = new Oauth2AccessToken(access_token, expires_in.toString());
		
	}
}
