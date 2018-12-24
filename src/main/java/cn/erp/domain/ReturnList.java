package cn.erp.domain;

import java.util.ArrayList;
import java.util.List;

public class ReturnList {

	private Integer id;
	private String name;
	private Integer p_id;
	private String state;
	private String iconCls;
	private Attribute attributes=new Attribute();
	private List<ReturnList> children = new ArrayList<>();
	
	public Attribute getAttributes() {
		return attributes;
	}
	public void setAttributes(Attribute attributes) {
		this.attributes = attributes;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getP_id() {
		return p_id;
	}
	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public List<ReturnList> getChildren() {
		return children;
	}
	public void setChildren(List<ReturnList> children) {
		this.children = children;
	}
	
}

