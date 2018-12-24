package cn.erp.domain;

import java.util.Date;

public class Log {
	private Integer id;//编号
	private String content;//操作内容，具体指什么操作，比如查找用户信息
	private Date time;//时间
	private String type;//操作类型，比如登录操作，查询操作
	private Integer userid;//操作用户的ID
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}	
	
}
