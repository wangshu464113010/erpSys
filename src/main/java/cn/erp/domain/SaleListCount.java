package cn.erp.domain;

import java.util.Date;
import java.util.List;

public class SaleListCount {
	private Integer id;
	private double amount_paid;
	private double amount_payable;
	private String sale_date;
	private String sale_number;
	private String remarks;
	private Integer state;
	private Integer user_id;
	private Integer customer_id;
	private User user = new User();
	private Customer customer = new Customer();
	private Integer sale_list_id;
	private Integer type_id;
	private Integer goods_id;
	private List<SaleListGoods> sale_list_goods_list;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public String getSale_date() {
		return sale_date;
	}
	public void setSale_date(String sale_date) {
		this.sale_date = sale_date;
	}
	public String getSale_number() {
		return sale_number;
	}
	public void setSale_number(String sale_number) {
		this.sale_number = sale_number;
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
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Integer getSale_list_id() {
		return sale_list_id;
	}
	public void setSale_list_id(Integer sale_list_id) {
		this.sale_list_id = sale_list_id;
	}
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	public Integer getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	public List<SaleListGoods> getSale_list_goods_list() {
		return sale_list_goods_list;
	}
	public void setSale_list_goods_list(List<SaleListGoods> sale_list_goods_list) {
		this.sale_list_goods_list = sale_list_goods_list;
	}
	
	
	
}
