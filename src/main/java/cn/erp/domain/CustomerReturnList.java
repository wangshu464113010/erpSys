package cn.erp.domain;

public class CustomerReturnList {
	//退货商品
	private Integer id;//关联退货id
	private Double amount_paid;//已付
	private Double amount_payable;//应付
	private String customer_return_date;//退货日期
	private String customer_return_number;//单号
	private String remarks;//备注
	private Integer state;//付款状态
	private Integer user_id;//用户
	private Integer customer_id;//关联客户id
	private User user = new User();
	private Customer customer = new Customer();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getAmount_paid() {
		return amount_paid;
	}
	public void setAmount_paid(Double amount_paid) {
		this.amount_paid = amount_paid;
	}
	public Double getAmount_payable() {
		return amount_payable;
	}
	public void setAmount_payable(Double amount_payable) {
		this.amount_payable = amount_payable;
	}
	public String getCustomer_return_date() {
		return customer_return_date;
	}
	public void setCustomer_return_date(String customer_return_date) {
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
	@Override
	public String toString() {
		return "CustomerReturnList [id=" + id + ", amount_paid=" + amount_paid + ", amount_payable=" + amount_payable
				+ ", customer_return_date=" + customer_return_date + ", customer_return_number="
				+ customer_return_number + ", remarks=" + remarks + ", state=" + state + ", user_id=" + user_id
				+ ", customer_id=" + customer_id + ", user=" + user + ", customer=" + customer + "]";
	}
	
}
