package cn.erp.domain;

public class GoodsGoosTypeVO {
		private Integer id;
		private String code;
		private String name;//商品名称
		private String model;
		private String tname;//商品类别S名
		private String unit;
		private Double lastPurchasingPrice;
		private Double purchasingPrice;
		private Double sellingPrice;
		private Integer inventoryQuantity;
		private Integer minNum;
		private Integer state;
		private Integer saleTotal;
		private String producer;
		private String remarks;
		private String codeOrName;	//搜索框输入的东西	
		private Integer typeId;		
		private GoodsType type;
		public Integer getTypeId() {
			return typeId;
		}

		public void setTypeId(Integer typeId) {
			this.typeId = typeId;
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

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		public String getTname() {
			return tname;
		}

		public void setTname(String tname) {
			this.tname = tname;
		}

		public String getUnit() {
			return unit;
		}

		public void setUnit(String unit) {
			this.unit = unit;
		}

		public Double getLastPurchasingPrice() {
			return lastPurchasingPrice;
		}

		public void setLastPurchasingPrice(Double lastPurchasingPrice) {
			this.lastPurchasingPrice = lastPurchasingPrice;
		}

		public Double getPurchasingPrice() {
			return purchasingPrice;
		}

		public void setPurchasingPrice(Double purchasingPrice) {
			this.purchasingPrice = purchasingPrice;
		}

		public Double getSellingPrice() {
			return sellingPrice;
		}

		public void setSellingPrice(Double sellingPrice) {
			this.sellingPrice = sellingPrice;
		}

		public Integer getInventoryQuantity() {
			return inventoryQuantity;
		}

		public void setInventoryQuantity(Integer inventoryQuantity) {
			this.inventoryQuantity = inventoryQuantity;
		}

		public Integer getMinNum() {
			return minNum;
		}

		public void setMinNum(Integer minNum) {
			this.minNum = minNum;
		}

		public Integer getState() {
			return state;
		}

		public void setState(Integer state) {
			this.state = state;
		}

		public Integer getSaleTotal() {
			return saleTotal;
		}

		public void setSaleTotal(Integer saleTotal) {
			this.saleTotal = saleTotal;
		}

		public String getProducer() {
			return producer;
		}

		public void setProducer(String producer) {
			this.producer = producer;
		}

		public String getRemarks() {
			return remarks;
		}

		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}

		public String getCodeOrName() {
			return codeOrName;
		}

		public void setCodeOrName(String codeOrName) {
			this.codeOrName = codeOrName;
		}

		public GoodsType getType() {
			return type;
		}

		public void setType(GoodsType type) {
			this.type = type;
		}
}
