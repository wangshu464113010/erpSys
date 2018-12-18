package cn.erp.domain;

import java.util.ArrayList;
import java.util.List;

public class GoodsData {
	private int total;
	private List<Goods> rows = new ArrayList<>();
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<Goods> getRows() {
		return rows;
	}
	public void setRows(List<Goods> rows) {
		this.rows = rows;
	}
	
	
}
