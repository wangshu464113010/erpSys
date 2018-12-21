package cn.erp.domain;

import java.util.Date;

public class StatisticsByDayVO {
	private Double amountCost;
	private Double amountSale;
	private Double amountProfit;
	private Date date;
	public Double getAmountCost() {
		return amountCost;
	}
	public void setAmountCost(Double amountCost) {
		this.amountCost = amountCost;
	}
	public Double getAmountSale() {
		return amountSale;
	}
	public void setAmountSale(Double amountSale) {
		this.amountSale = amountSale;
	}
	public Double getAmountProfit() {
		return amountProfit;
	}
	public void setAmountProfit(Double amountProfit) {
		this.amountProfit = amountProfit;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
