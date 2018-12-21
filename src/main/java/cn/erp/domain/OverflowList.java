package cn.erp.domain;

import java.sql.Date;

public class OverflowList {
	private int id;
	private Date overflow_date;
	private String overflow_number;
	private String remarks;
	private int user_id;
	private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getOverflow_date() {
		return overflow_date;
	}
	public void setOverflow_date(Date overflow_date) {
		this.overflow_date = overflow_date;
	}
	public String getOverflow_number() {
		return overflow_number;
	}
	public void setOverflow_number(String overflow_number) {
		this.overflow_number = overflow_number;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
}
