package com.plthanh.model;

public class Subscriber {
	private String userId;
	private String username;
	private String password;
	private String domain;
	private String email;
	
	
	public Subscriber() {
		super();
	}

	public Subscriber(String userId, String username, String password,
			String domain, String email) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.domain = domain;
		this.email = email;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
