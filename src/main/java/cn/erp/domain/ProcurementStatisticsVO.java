package cn.erp.domain;

import java.util.Date;
import java.util.List;
/**
 * �ɹ�ͳ���еĽ�������ѯVO����
 * @author wangshu
 *
 */
public class ProcurementStatisticsVO {
	private Integer id;
	private String purchaseNumber;
	private Date purchaseDate;
	private Date bPurchaseDate;
	private Date ePurchaseDate;
	private Double amountPayable;
	private Double amountPaid;
	private Integer state;
	private String remarks;
	
	private Integer userId;
	private Integer supplierID;
	
	private Supplier supplier;//��Ӧ��
	private User user;//�û�
	private List<PurchaseListGoodsVO> purchaseListGoodsList;//������
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getSupplierID() {
		return supplierID;
	}
	public void setSupplierID(Integer supplierID) {
		this.supplierID = supplierID;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPurchaseNumber() {
		return purchaseNumber;
	}
	public void setPurchaseNumber(String purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public Date getbPurchaseDate() {
		return bPurchaseDate;
	}
	public void setbPurchaseDate(Date bPurchaseDate) {
		this.bPurchaseDate = bPurchaseDate;
	}
	public Date getePurchaseDate() {
		return ePurchaseDate;
	}
	public void setePurchaseDate(Date ePurchaseDate) {
		this.ePurchaseDate = ePurchaseDate;
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
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<PurchaseListGoodsVO> getPurchaseListGoodsList() {
		return purchaseListGoodsList;
	}
	public void setPurchaseListGoodsList(List<PurchaseListGoodsVO> purchaseListGoodsList) {
		this.purchaseListGoodsList = purchaseListGoodsList;
	}
	
}
