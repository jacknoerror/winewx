package com.weixun.cn.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@SuppressWarnings("serial")
public class SpaceTalkDto extends SpaceTalk {
	
	//  顶踩  动作,  目前该值为  up , 未操作: 为null or ""
	private String   action;

	
	private String showtime;

	
	private String  showTime;
	

	/**
	 * @return the showtime
	 */
	public String getShowtime() {
		getShowTime();
		return showtime;
	}

	/**
	 * @param showtime the showtime to set
	 */
	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}

	public String getShowTime() {
		
		if(this.getReplyTime() != null){
			
			Long  l = System.currentTimeMillis() - this.getReplyTime().getTime();
			l = l/(1000 * 60);
			if(l < 60) {
				if(l< 5) 
					showTime = "刚刚";
				else 	
				showTime = l+"分钟前"; 
			}else {
				l = l/60;
				if(l < 24){
					showTime = l+"小时前";
				}else {
					DateFormat dateFormat = new SimpleDateFormat("MM-dd");
					showTime = dateFormat.format(this.getReplyTime());
				}
			}	
			
		}
		showtime = showTime;
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	
	
}