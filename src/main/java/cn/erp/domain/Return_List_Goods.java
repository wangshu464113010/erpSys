package cn.erp.domain;

public class Return_List_Goods {
	private Integer id;
	private String code;
	private String model;
	private String name;
	private Integer num;
	private Float price;
	private Float total;
	private String unit;
	private Integer return_list_id;
	private GoodsType type;
	private Return_List returnList;
	private Integer type_id;
	private Integer goods_id;
	
	public GoodsType getType() {
		return type;
	}
	public void setType(GoodsType type) {
		this.type = type;
	}
	public Return_List getreturnList() {
		return returnList;
	}
	public void setReturnList(Return_List purchaseList) {
		this.returnList = purchaseList;
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
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String util) {
		this.unit = util;
	}
	public Integer getReturn_list_id() {
		return return_list_id;
	}
	public void setReturn_list_id(Integer purchase_list_id) {
		this.return_list_id = purchase_list_id;
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

