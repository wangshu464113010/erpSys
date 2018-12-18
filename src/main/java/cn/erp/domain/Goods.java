package cn.erp.domain;


public class Goods {
	private Integer id;
	private String code;
	private Integer inventory_quantity;
	private Integer min_num;
	private String model;
	private String name;
	private String producer;
	private Double purchasing_price;
	private String remarks;
	private Double selling_price;
	private String unit;
	private Integer type_id;
	private Integer state;
	private Double last_purchasing_price;
	
	private GoodsType type ;


	public Double getLast_purchasing_price() {
		return last_purchasing_price;
	}
	public void setLast_purchasing_price(Double last_purchasing_price) {
		this.last_purchasing_price = last_purchasing_price;
	}
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
	public Integer getInventory_quantity() {
		return inventory_quantity;
	}
	public void setInventory_quantity(Integer inventory_quantity) {
		this.inventory_quantity = inventory_quantity;
	}
	public Integer getMin_num() {
		return min_num;
	}
	public void setMin_num(Integer min_num) {
		this.min_num = min_num;
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
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public Double getPurchasing_price() {
		return purchasing_price;
	}
	public void setPurchasing_price(Double purchasing_price) {
		this.purchasing_price = purchasing_price;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Double getSelling_price() {
		return selling_price;
	}
	public void setSelling_price(Double selling_price) {
		this.selling_price = selling_price;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public GoodsType getType() {
		return type;
	}
	public void setType(GoodsType type) {
		this.type = type;
	}

}
