package cn.erp.domain;

import java.util.Date;
import java.util.List;

/**
 * 采购统计中的退货单查询VO对象
 * @author wangshu
 *
 */
public class ProcurementReturnStatisticsVO {
	private  Integer   id	;
	private  String   returnNumber	;
	private  Date  returnDate	;
	private  Double  amountPayable	;
	private  Double  amountPaid	;
	private  Integer  state	;
	private   String  remarks	;
	private   Date bReturnDate	;
	private   Date eReturnDate	;
	private   Integer typeId	;
	private   Integer goodsId	;
	private   String  unit	;
	private   Double price	;
	private  Integer  num	;
	private   Double total	;
	private  String  codeOrName	;
	
	private   User user	;
	private Integer userId;
	
	private  Supplier  supplier;
	private Integer supplierID;
	private List<ReturnListGoodsVO> returnListGoodsList;//退货表
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getReturnNumber() {
		return returnNumber;
	}
	public void setReturnNumber(String returnNumber) {
		this.returnNumber = returnNumber;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public Double getAmountPayable() {
		return amountPayable;
	}
	public void setAmountPayable(Double amountPayable) {
		this.amountPayable = amountPayable;
	}
	public Double getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getbReturnDate() {
		return bReturnDate;
	}
	public void setbReturnDate(Date bReturnDate) {
		this.bReturnDate = bReturnDate;
	}
	public Date geteReturnDate() {
		return eReturnDate;
	}
	public void seteReturnDate(Date eReturnDate) {
		this.eReturnDate = eReturnDate;
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getCodeOrName() {
		return codeOrName;
	}
	public void setCodeOrName(String codeOrName) {
		this.codeOrName = codeOrName;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public Integer getSupplierID() {
		return supplierID;
	}
	public void setSupplierID(Integer supplierID) {
		this.supplierID = supplierID;
	}
	public List<ReturnListGoodsVO> getReturnListGoodsList() {
		return returnListGoodsList;
	}
	public void setReturnListGoodsList(List<ReturnListGoodsVO> returnListGoodsList) {
		this.returnListGoodsList = returnListGoodsList;
	}
}
