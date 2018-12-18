package cn.erp.domain;

import java.util.ArrayList;
import java.util.List;

public class Goodstype {
	private int id;
	private String name;
	private int p_id;
	private int state;
	private String icon;
	
	private List<Goodstype> child = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<Goodstype> getChild() {
		return child;
	}

	public void setChild(List<Goodstype> child) {
		this.child = child;
	}

	@Override
	public String toString() {
		return "Goodstype [id=" + id + ", name=" + name + ", p_id=" + p_id + ", state=" + state + ", icon=" + icon
				+ ", child=" + child + "]";
	}
}
