package cn.erp.domain;

public class Goods {
	private int id;
	private String code;
	private int inventory_quantity;
	private int min_num;
	private String model;
	private String name;
	private String producer;
	private double purchasing_price;
	private int saleTotal;
	private String remarks;
	private double selling_price;
	private String unit;
	private int type_id;
	private GoodsType type;
	private int state;
	private double last_purchasing_price;
	private String codeOrName;
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
	public int getInventory_quantity() {
		return inventory_quantity;
	}
	public void setInventory_quantity(int inventory_quantity) {
		this.inventory_quantity = inventory_quantity;
	}
	public int getMin_num() {
		return min_num;
	}
	public void setMin_num(int min_num) {
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
	public double getPurchasing_price() {
		return purchasing_price;
	}
	public void setPurchasing_price(double purchasing_price) {
		this.purchasing_price = purchasing_price;
	}
	public int getSaleTotal() {
		return saleTotal;
	}
	public void setSaleTotal(int saleTotal) {
		this.saleTotal = saleTotal;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public double getSelling_price() {
		return selling_price;
	}
	public void setSelling_price(double selling_price) {
		this.selling_price = selling_price;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public GoodsType getType() {
		return type;
	}
	public void setType(GoodsType type) {
		this.type = type;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public double getLast_purchasing_price() {
		return last_purchasing_price;
	}
	public void setLast_purchasing_price(double last_purchasing_price) {
		this.last_purchasing_price = last_purchasing_price;
	}
	public String getCodeOrName() {
		return codeOrName;
	}
	public void setCodeOrName(String codeOrName) {
		this.codeOrName = codeOrName;
	}

}
