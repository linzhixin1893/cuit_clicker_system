package com.cuit.bean;

public class Student {
	private int id;
	private String name;
	private String code;
	private String number;
	private String phone;
	
	public Student() {
		super();
	}
	public Student(int id, String name, String code, String number,
			String phone) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.number = number;
		this.phone = phone;
	}
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	public boolean isAnyParamEmpty() {
		boolean res = false;
		if (name == null || "".equals(name.trim())) {
			res = true;
		}
		if (code == null || "".equals(code.trim())) {
			res = true;
		}
		if (number == null || "".equals(number.trim())) {
			res = true;
		}
		if (phone == null || "".equals(phone.trim())) {
			res = true;
		}
		return res;
	}
	
}
