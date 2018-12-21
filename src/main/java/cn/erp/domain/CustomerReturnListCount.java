package cn.erp.domain;

import java.util.Date;
import java.util.List;

public class CustomerReturnListCount {
	private Integer id;
	private double amount_paid;
	private double amount_payable;
	private Date customer_return_date;
	private String customer_return_number;
	private String remarks;
	private Integer state;
	private Integer user_id;
	private Integer customer_id;
	private User user = new User();
	private Customer customer = new Customer();
	private Integer id1;
	private String code;
	private String model;
	private String name;
	private Integer num;
	private double price;
	private Integer total;
	private String unit;
	private Integer customer_return_list_id;
	private Integer type_id;
	private Integer goods_id;
	private List<CustomerReturnListGoods> customer_return_list_goods_list;
	
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
	public Date getCustomer_return_date() {
		return customer_return_date;
	}
	public void setCustomer_return_date(Date customer_return_date) {
		this.customer_return_date = customer_return_date;
	}
	public String getCustomer_return_number() {
		return customer_return_number;
	}
	public void setCustomer_return_number(String customer_return_number) {
		this.customer_return_number = customer_return_number;
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
	
	public Integer getId1() {
		return id1;
	}
	public void setId1(Integer id1) {
		this.id1 = id1;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Integer getCustomer_return_list_id() {
		return customer_return_list_id;
	}
	public void setCustomer_return_list_id(Integer customer_return_list_id) {
		this.customer_return_list_id = customer_return_list_id;
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
	public List<CustomerReturnListGoods> getCustomer_return_list_goods_list() {
		return customer_return_list_goods_list;
	}
	public void setCustomer_return_list_goods_list(List<CustomerReturnListGoods> customer_return_list_goods_list) {
		this.customer_return_list_goods_list = customer_return_list_goods_list;
	}
	
}
