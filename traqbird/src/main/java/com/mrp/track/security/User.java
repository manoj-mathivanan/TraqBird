package com.mrp.track.security;


public class User {
	private String username;
	private String password;
	private String error;
	private Boolean valid;
	private String orgid;
	private String name;
	private int verified;

	public User(){
		this.username = "";
		this.password = "";
		this.valid = false;
		this.error = "";
	}
	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
		this.valid = false;
		this.error = "DB connection failed";
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
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Boolean getValid() {
		return valid;
	}
	public void setValid(Boolean valid) {
		this.valid = valid;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getVerified() {
		return verified;
	}
	public void setVerified(int verified) {
		this.verified = verified;
	}

}
