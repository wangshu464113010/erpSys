package cn.erp.domain;

import java.sql.Date;

public class DamageList{
	private int id;
	private Date damage_date;
	private String damage_number;
	private String remarks;
	private int user_id;
	private DamageListGoods damage_list_id;
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

	public DamageListGoods getDamage_list_id() {
		return damage_list_id;
	}
	public void setDamage_list_id(DamageListGoods damage_list_id) {
		this.damage_list_id = damage_list_id;
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
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	
}
