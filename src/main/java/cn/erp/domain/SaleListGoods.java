package cn.erp.domain;

public class SaleListGoods {
	private Integer id;
	private String code;
	private String model;
	private String name;
	private Integer num;
	private double price;
	private double total;
	private String unit;
	private Integer sale_list_id;
	private Integer type_id;
	private Integer goods_id;
	private GoodsType type;
	private SaleList saleList;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public GoodsType getType() {
		return type;
	}
	public void setType(GoodsType type) {
		this.type = type;
	}
	public SaleList getSaleList() {
		return saleList;
	}
	public void setSaleList(SaleList saleList) {
		this.saleList = saleList;
	}
	@Override
	public String toString() {
		return "SaleListGoods [id=" + id + ", code=" + code + ", model=" + model + ", name=" + name + ", num=" + num
				+ ", price=" + price + ", total=" + total + ", unit=" + unit + ", sale_list_id=" + sale_list_id
				+ ", type_id=" + type_id + ", goods_id=" + goods_id + ", type=" + type + ", saleList=" + saleList + "]";
	}
	
}
