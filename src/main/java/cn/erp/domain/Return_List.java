package cn.erp.domain;

import java.util.Date;

public class Return_List {
	private Integer id;
	private Float amount_paid;
	private Float amount_payable;
	private Date return_date;
	private String remarks;
	private Integer state;
	private String return_number;
	private Integer supplier_id;
	private Integer user_id;
	private Supplier supplier;
	private User user;
	
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Float getAmount_paid() {
		return amount_paid;
	}
	public void setAmount_paid(Float amount_paid) {
		this.amount_paid = amount_paid;
	}
	public Float getAmount_payable() {
		return amount_payable;
	}
	public void setAmount_payable(Float amount_payable) {
		this.amount_payable = amount_payable;
	}
	public Date getReturn_date() {
		return return_date;
	}
	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getReturn_number() {
		return return_number;
	}
	public void setReturn_number(String return_number) {
		this.return_number = return_number;
	}
	public Integer getSupplier_id() {
		return supplier_id;
	}
	public void setSupplier_id(Integer supplier_id) {
		this.supplier_id = supplier_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	
}
