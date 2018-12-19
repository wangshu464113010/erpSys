package cn.erp.domain;

import java.util.Date;

public class SaleList {
	private int id;
	private double amount_paid;
	private double amount_payable;
	private String remarks;
	private Date sale_date;
	private String sale_number;
	private int state;
	private int user_id;
	private int customer_id;
	private Customer customer = new Customer();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getAmount_paid() {
		return amount_paid;
	}
	public void setAmount_paid(double amount_paid) {
		this.amount_paid = amount_paid;
	}
	public double getAmount_payable() {
		return amount_payable;
	}
	public void setAmount_payable(double amount_payable) {
		this.amount_payable = amount_payable;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getSale_date() {
		return sale_date;
	}
	public void setSale_date(Date sale_date) {
		this.sale_date = sale_date;
	}
	public String getSale_number() {
		return sale_number;
	}
	public void setSale_number(String sale_number) {
		this.sale_number = sale_number;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
