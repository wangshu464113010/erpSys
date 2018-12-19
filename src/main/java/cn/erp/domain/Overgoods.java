package cn.erp.domain;

public class Overgoods {
	private Integer code;//商品编码
	private String model;//商品型号
	private String name;//商品名
	private Integer num;//商品数量
	private Double price;//商品价格
	private Double total;//总价格
	private String unit;//商品单位
	private Integer overflow_list_id;//
	private String type_id;//商品类型编号
	private Integer goods_id;//商品编号
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Integer getOverflow_list_id() {
		return overflow_list_id;
	}
	public void setOverflow_list_id(Integer overflow_list_id) {
		this.overflow_list_id = overflow_list_id;
	}
	public String getType_id() {
		return type_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	public Integer getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
}
