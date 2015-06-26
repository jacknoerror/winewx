package com.weixun.cn.bean;

import java.util.Date;

public class SpaceTalk  implements CmListItem {
    private Integer id;
    /**
     * 空间类型
     */
    private String type;

 // friend, public, private(不用)
    /**
     * 权限
     */
    private String purview;
    /**
     * 主图
     */
    private String logo;
    
    private String url;

    private Integer userId;
    /**
     * 点赞数
     */
    private Integer upAmount;
    /**
     * 踩量数
     */
    private Integer downAmount;
    /**
     * 回复数
     */
    private Integer replyAmount;
    
    /**  模块id  目前不用   **/
    private  Integer  blockId;
    
    /** 浏览量    **/
    private   Integer scanAmount;

    private Date createTime;

    private Date updateTime;

    private Date replyTime;
    /**
     * 创建者昵称
     */
    private String nickname;
    /**
     * 用户头像
     */
    private String userImage;
    /**
     * 上传图片
     */
    private String images;

    /**  入库的 酒  ids,  隔开  **/
    //TODO  功能整合进去
    private String more1;

    private String more2;

    private String more3;
    
    /**
     *  活动 id
     *  //TODO
     */
    private Integer int1;
    
    private String title;

    private Boolean topStatus;

	/**
     * 内容
     */
    private String msg;
    
    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getTopStatus() {
		return topStatus;
	}

	public void setTopStatus(Boolean topStatus) {
		this.topStatus = topStatus;
	}



    public Integer getBlockId() {
		return blockId;
	}

	public void setBlockId(Integer blockId) {
		this.blockId = blockId;
	}

	public Integer getScanAmount() {
		return scanAmount;
	}

	public void setScanAmount(Integer scanAmount) {
		this.scanAmount = scanAmount;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getPurview() {
        return purview;
    }

    public void setPurview(String purview) {
        this.purview = purview == null ? null : purview.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUpAmount() {
        return upAmount;
    }

    public void setUpAmount(Integer upAmount) {
        this.upAmount = upAmount;
    }

    public Integer getDownAmount() {
        return downAmount;
    }

    public void setDownAmount(Integer downAmount) {
        this.downAmount = downAmount;
    }

    public Integer getReplyAmount() {
        return replyAmount;
    }

    public void setReplyAmount(Integer replyAmount) {
        this.replyAmount = replyAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage == null ? null : userImage.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

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
        this.int1 = int1 == null ? null : int1;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

	public void addUpAmount(int i) {
		upAmount =upAmount ==null? 0: upAmount;
		upAmount +=i;
	}

	public void addScannerAmount(int  i) {
		scanAmount = scanAmount ==null? 0: scanAmount;
		scanAmount +=i;
	}

	public void addReplyAmount(int i) {
		replyAmount = replyAmount ==null? 0: replyAmount;
		replyAmount +=1;
		
	}
}