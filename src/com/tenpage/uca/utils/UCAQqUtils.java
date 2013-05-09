package com.tenpage.uca.utils;

import com.tenpage.uca.LoginQqActivity;
import com.tenpage.uca.entity.QqReturnData;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/**
 * @author fanshuo
 * @date 2013-5-9 上午10:31:26
 */
public class UCAQqUtils {

	/**
	 * 进行授权
	 * @param context
	 */
	public static void login(Context context) {
		context.startActivity(new Intent(context, LoginQqActivity.class));
	}
	
	/**
	 * 检查是否已经授权
	 * @param context
	 * @return
	 */
	public static boolean hasAlreadyLogin(Context context){
		try {
			String access_token = PreferenceManager.getString(context,
					PreferenceManager.KEY_LOGIN_QQ_ACCESS_TOKEN, "");
			String openid = PreferenceManager.getString(context,
					PreferenceManager.KEY_LOGIN_QQ_OPEN_ID, "");
			if(!TextUtils.isEmpty(openid) && !TextUtils.isEmpty(access_token)){
				return true;
			}
			else{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 获取本地保存的授权信息
	 * @param context
	 * @return
	 */
	public static QqReturnData getLoginData(Context context) {
		QqReturnData data = new QqReturnData();
		try {
			String access_token = PreferenceManager.getString(context,
					PreferenceManager.KEY_LOGIN_QQ_ACCESS_TOKEN, "");
			String openid = PreferenceManager.getString(context,
					PreferenceManager.KEY_LOGIN_QQ_OPEN_ID, "");
			Long expires_time = PreferenceManager.getLong(context,
					PreferenceManager.KEY_LOGIN_QQ_EXPIRES_TIME, 0L);
			data.setAccess_token(access_token);
			data.setOpenid(openid);
			data.setExpires_time(expires_time);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return data;
	}

	public static void shareWeibo(Context context) {

	}

}
