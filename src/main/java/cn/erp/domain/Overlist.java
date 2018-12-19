package cn.erp.domain;

import java.util.Date;

public class Overlist {
	private Integer id;
	private Date overflow_date;
	private String overflow_number;
	private String remarks;
	private Integer user_id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
}
