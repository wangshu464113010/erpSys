package cn.erp.domain;
/**
 * 供应商表
 * @author wangshu
 *
 */
public class Supplier {
	
	private Integer id; //主键
	private String address;//地址
	private String contact;//联系人
	private String name;//供应商全称
	private String number;//电话号码  格式：xxxx-xxxxxxx
	private String remarks;//备注
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
