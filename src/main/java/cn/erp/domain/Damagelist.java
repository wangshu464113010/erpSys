package cn.erp.domain;

import java.util.Date;

public class Damagelist {
	private Integer id;
	private Date damage_date;
	private String damage_number;
	private String remarks;
	private Integer user_id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDamage_date() {
		return damage_date;
	}
	public void setDamage_date(Date damage_date) {
		this.damage_date = damage_date;
	}
	public String getDamage_number() {
		return damage_number;
	}
	public void setDamage_number(String damage_number) {
		this.damage_number = damage_number;
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
