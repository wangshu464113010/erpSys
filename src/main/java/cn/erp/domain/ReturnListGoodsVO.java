package cn.erp.domain;

public class ReturnListGoodsVO {
	private Integer id;
	private String code;
	private String model;
	private String name;
	
	private Integer num;
	private Double price;
	private Double total;
	private String unit;
	private Integer goodsId;
	
	private Integer purchaseListId;
	private Return_List returnList;
	
	private Integer typeId;
	private GoodsType type;
	
	private String codeOrName;
	
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
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
	public Integer getPurchaseListId() {
		return purchaseListId;
	}
	public void setPurchaseListId(Integer purchaseListId) {
		this.purchaseListId = purchaseListId;
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
	public GoodsType getType() {
		return type;
	}
	public void setType(GoodsType type) {
		this.type = type;
	}
	public Return_List getReturnList() {
		return returnList;
	}
	public void setReturnList(Return_List returnList) {
		this.returnList = returnList;
	}
	public String getCodeOrName() {
		return codeOrName;
	}
	public void setCodeOrName(String codeOrName) {
		this.codeOrName = codeOrName;
	}
	
}
