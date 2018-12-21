package cn.erp.domain;

public class SaleListCondition {
	private String saleNumber;
	private int customer_id;
	private int state;
	private String bSaleDate;
	private String eSaleDate;
	public String getSaleNumber() {
		return saleNumber;
	}
	public void setSaleNumber(String saleNumber) {
		this.saleNumber = saleNumber;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getbSaleDate() {
		return bSaleDate;
	}
	public void setbSaleDate(String bSaleDate) {
		this.bSaleDate = bSaleDate;
	}
	public String geteSaleDate() {
		return eSaleDate;
	}
	public void seteSaleDate(String eSaleDate) {
		this.eSaleDate = eSaleDate;
	}
	@Override
	public String toString() {
		return "SaleListCondition [saleNumber=" + saleNumber + ", customer_id=" + customer_id + ", state=" + state
				+ ", bSaleDate=" + bSaleDate + ", eSaleDate=" + eSaleDate + "]";
	}
	
}
