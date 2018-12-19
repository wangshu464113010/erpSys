package cn.erp.domain;

public class GoodsJson {
	private String code;
	private String name;
	private String unit;
	private String model;
	private String price;
	private String num;
	private Float total;
	private Integer typeId;
	private Integer goodsId;
	private Integer inventoryQuantity;
	private Integer lastPurchasingPrice;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getInventoryQuantity() {
		return inventoryQuantity;
	}
	public void setInventoryQuantity(Integer inventoryQuantity) {
		this.inventoryQuantity = inventoryQuantity;
	}
	public Integer getLastPurchasingPrice() {
		return lastPurchasingPrice;
	}
	public void setLastPurchasingPrice(Integer lastPurchasingPrice) {
		this.lastPurchasingPrice = lastPurchasingPrice;
	}
	
	
}
