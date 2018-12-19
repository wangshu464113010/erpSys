package cn.erp.domain;

import java.util.ArrayList;
import java.util.List;

public class Purchase {
	private Integer id;
	private String name;
	private Integer p_id;
	private Integer state;
	private String icon;
	private List<Purchase> children = new ArrayList<>();
	
	
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public List<Purchase> getChildren() {
		return children;
	}
	public void setChildren(List<Purchase> children) {
		this.children = children;
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
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}
