package com.base.bean;

public class Demo extends BaseBean {
	private Integer id;
	private String password;
	private String username;

	public Demo(Integer id, String password, String username) {
		super();
		this.id = id;
		this.password = password;
		this.username = username;
	}

	public Demo() {
	}

	public Demo(String password, String username) {
		this.password = password;
		this.username = username;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return id + "-" + username + "-" + password;
	}
}
