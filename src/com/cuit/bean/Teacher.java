package com.cuit.bean;

import com.cuit.util.*;

public class Teacher {
	private int id;
	private String name;
	private String phone;
	private String code;
	private String photo;
	
	
	public Teacher(int id, String name, String phone, String code, String photo) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.code = code;
		this.photo = photo;
	}
	public Teacher() {
		super();
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public boolean isEmptyOrNull() {
		boolean res = false;
		if (Util.isEmptyOrNull(name)
				|| Util.isEmptyOrNull(phone)
				|| Util.isEmptyOrNull(code)) {
			res = true;
		}
		return res;
	}
	
}
