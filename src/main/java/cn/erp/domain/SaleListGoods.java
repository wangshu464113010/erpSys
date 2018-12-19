package cn.erp.domain;

public class SaleListGoods {
	private int id;
	private String code;
	private String model;
	private String name;
	private int num;
	private double price;
	private double total;
	private String unit;
	private int sale_list_id;
	private int type_id;
	private int goods_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getSale_list_id() {
		return sale_list_id;
	}
	public void setSale_list_id(int sale_list_id) {
		this.sale_list_id = sale_list_id;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	@Override
	public String toString() {
		return "SaleListGoods [id=" + id + ", code=" + code + ", model=" + model + ", name=" + name + ", num=" + num
				+ ", price=" + price + ", total=" + total + ", unit=" + unit + ", sale_list_id=" + sale_list_id
				+ ", type_id=" + type_id + ", goods_id=" + goods_id + "]";
	}
}
