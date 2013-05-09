package com.tenpage.uca.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.renren.api.connect.android.AsyncRenren;
import com.renren.api.connect.android.Renren;
import com.renren.api.connect.android.common.AbstractRequestListener;
import com.renren.api.connect.android.exception.RenrenAuthError;
import com.renren.api.connect.android.status.StatusSetRequestParam;
import com.renren.api.connect.android.status.StatusSetResponseBean;
import com.renren.api.connect.android.view.RenrenAuthListener;
import com.tenpage.uca.LoginRenrenActivity;
import com.tenpage.uca.entity.RenrenReturnData;

/**
 * @author fanshuo
 * @date 2013-5-9 上午10:31:26
 */
public class UCARenrenUtils {
	/**
	 * 进行授权
	 * 
	 * @param context
	 */
	public static void login(Context context) {
		context.startActivity(new Intent(context, LoginRenrenActivity.class));
	}

	/**
	 * 获取本地保存的授权信息
	 * 
	 * @param context
	 * @return
	 */
	public static RenrenReturnData getLoginData(Context context) {
		RenrenReturnData data = new RenrenReturnData();
		return data;
	}

	/**
	 * 发送一条状态
	 * @param context
	 * @param content
	 * @param listener
	 */
	public static void publishStatus(final Activity context, String content,
			AbstractRequestListener<StatusSetResponseBean> listener) {
		StatusSetRequestParam param = new StatusSetRequestParam(content.trim());
		Renren renren = new Renren(LoginRenrenActivity.API_KEY,
				LoginRenrenActivity.SECRET_KEY, LoginRenrenActivity.APP_ID,
				context);
		renren.authorize(context, null, new RenrenAuthListener() {
			
			@Override
			public void onRenrenAuthError(RenrenAuthError renrenAuthError) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onComplete(Bundle values) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onCancelLogin() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onCancelAuth(Bundle values) {
				// TODO Auto-generated method stub
				
			}
		}, 1);
		renren.init(context);
		AsyncRenren aRenren = new AsyncRenren(renren);
		aRenren.publishStatus(param, listener, true);// 对结果进行监听若超过140字符，则自动截短
	}
}
