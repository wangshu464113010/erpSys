package cn.erp.domain;

public class Purchase_List_Goods {
	private Integer id;
	private String code;
	private String model;
	private String name;
	private Integer num;
	private Float price;
	private Float total;
	private String util;
	private Integer purchase_list_id;
	private GoodsType type;
	private Purchase_List purchaseList;
	private Integer type_id;
	private Integer goods_id;
	
	public GoodsType getType() {
		return type;
	}
	public void setType(GoodsType type) {
		this.type = type;
	}
	public Purchase_List getPurchaseList() {
		return purchaseList;
	}
	public void setPurchaseList(Purchase_List purchaseList) {
		this.purchaseList = purchaseList;
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
	public String getUtil() {
		return util;
	}
	public void setUtil(String util) {
		this.util = util;
	}
	public Integer getPurchase_list_id() {
		return purchase_list_id;
	}
	public void setPurchase_list_id(Integer purchase_list_id) {
		this.purchase_list_id = purchase_list_id;
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
