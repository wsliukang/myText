package com.xiteng.bean;

public class UserInfo {
	private String nickName;
	private String sex;
	private int age;
	private String address;
	private String telephone;
	
	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserInfo(String nickName, String sex, int age, String address, String telephone) {
		super();
		this.nickName = nickName;
		this.sex = sex;
		this.age = age;
		this.address = address;
		this.telephone = telephone;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public boolean isMan() {
		return "ÄÐ".equals(sex)?true:false;
	}
	
}
