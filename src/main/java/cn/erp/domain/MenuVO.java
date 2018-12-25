package cn.erp.domain;

import java.util.List;

/**
 * @author Administrator
 *
 */
public class MenuVO {
	private Integer id;
	private String text; //name
	private String state;
	private Boolean checked;
	private String icon;
	private List<MenuVO> children;
	private Integer p_id;
	 
	public Integer getP_id() {
		return p_id;
	}
	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public List<MenuVO> getChildren() {
		return children;
	}
	public void setChildren(List<MenuVO> children) {
		this.children = children;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
	
	
	
}
