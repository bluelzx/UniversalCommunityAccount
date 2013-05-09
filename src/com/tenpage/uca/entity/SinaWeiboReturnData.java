package com.tenpage.uca.entity;

/**
 * @author fanshuo
 * @date 2013-5-9 上午9:45:03
 */
public class SinaWeiboReturnData {

	private String access_token;
	private Long expires_time;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public Long getExpires_time() {
		return expires_time;
	}

	public void setExpires_time(Long expires_time) {
		this.expires_time = expires_time;
	}

}
