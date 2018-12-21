package cn.erp.domain;

public class User {
	private int id;
	private String bz;
	private String password;
	private String true_name;
	private String user_name;
	private String remakes;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTrue_name() {
		return true_name;
	}
	public void setTrue_name(String true_name) {
		this.true_name = true_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getRemakes() {
		return remakes;
	}
	public void setRemakes(String remakes) {
		this.remakes = remakes;
	}
}
