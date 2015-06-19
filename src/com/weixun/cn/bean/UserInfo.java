package com.weixun.cn.bean;

import java.util.Date;



/**
 * 用户类
 */
@SuppressWarnings("serial")
public class UserInfo  {
	private Integer id;
	/**
	 * 账号 微熏号
	 */
	private String account;
	/**
	 * 手机  登入手机号
	 */
	private String mobile;
	/**
	 * 邮箱
	 */
	private String eamil;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 头像
	 */
	private String image;
	/**
	 * qq第三方ID
	 */
	private String qqId;

	private String weixingId;

	private String weiboId;

	private String faceId;
	/**
	 * 地址 不用了 移到userMore了
	 */
	private String address;

	private Date updateTime;

	// 签名
	private String more1;

	private String more2;

	private String more3;

	private Integer int1;

	private Integer int2;
	/**
	 * 是否冻结
	 */
	private Boolean isFreeze;
	
//	private String prefixChat;

	
	
//	public String getPrefixChat() {
//		return prefixChat;
//	}
//
//	public void setPrefixChat(String prefixChat) {
//		this.prefixChat = prefixChat;
//	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Boolean getIsFreeze() {
		return isFreeze;
	}

	public void setIsFreeze(Boolean isFreeze) {
		this.isFreeze = isFreeze;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id == null ? null : id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account == null ? null : account.trim();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getEamil() {
		return eamil;
	}

	public void setEamil(String eamil) {
		this.eamil = eamil == null ? null : eamil.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname == null ? null : nickname.trim();
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image == null ? null : image.trim();
	}

	public String getQqId() {
		return qqId;
	}

	public void setQqId(String qqId) {
		this.qqId = qqId == null ? null : qqId.trim();
	}

	public String getWeixingId() {
		return weixingId;
	}

	public void setWeixingId(String weixingId) {
		this.weixingId = weixingId == null ? null : weixingId.trim();
	}

	public String getWeiboId() {
		return weiboId;
	}

	public void setWeiboId(String weiboId) {
		this.weiboId = weiboId == null ? null : weiboId.trim();
	}

	public String getFaceId() {
		return faceId;
	}

	public void setFaceId(String faceId) {
		this.faceId = faceId == null ? null : faceId.trim();
	}

	// public String getAddress() {
	// return address;
	// }
	//
	// public void setAddress(String address) {
	// this.address = address == null ? null : address.trim();
	// }

	public String getMore1() {
		return more1;
	}

	public void setMore1(String more1) {
		this.more1 = more1 == null ? null : more1.trim();
	}

	public String getMore2() {
		return more2;
	}

	public void setMore2(String more2) {
		this.more2 = more2 == null ? null : more2.trim();
	}

	public String getMore3() {
		return more3;
	}

	public void setMore3(String more3) {
		this.more3 = more3 == null ? null : more3.trim();
	}

	public Integer getInt1() {
		return int1;
	}

	public void setInt1(Integer int1) {
		this.int1 = int1;
	}

	public Integer getInt2() {
		return int2;
	}

	public void setInt2(Integer int2) {
		this.int2 = int2;
	}

	// public String getProv() {
	// return prov;
	// }
	//
	// public void setProv(String prov) {
	// this.prov = prov;
	// }
	//
	// public String getCity() {
	// return city;
	// }
	//
	// public void setCity(String city) {
	// this.city = city;
	// }
	//
	// public String getDist() {
	// return dist;
	// }
	//
	// public void setDist(String dist) {
	// this.dist = dist;
	// }

}