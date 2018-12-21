package cn.erp.domain;

public class CustomerReturnListGoods {
	//退货商品
	private Integer id;//商品id
	private String code;//商品编号
	private String model;//商品型号
	private String name;//商品名称
	private Integer num;//商品数量
	private Double price;//商品单价
	private Double total;//商品总金额
	private String unit;//商品单位
	private Integer customer_return_list_id;//关联退货表里的id
	private Integer type_id;//关联t_goodstype类型表里的id
	private Integer goods_id;//关联t_goods类型表里的id 
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
}
