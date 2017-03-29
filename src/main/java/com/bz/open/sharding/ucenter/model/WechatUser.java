package com.bz.open.sharding.ucenter.model;

import java.io.Serializable;

public class WechatUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7325587704000719399L;

	private String openid;
	private String nickname;
	private String avatar;
	private Integer gender;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

}
