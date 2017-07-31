package com.cuit.main;

import com.cuit.bean.StudentLogReply;
import com.cuit.bean.Teacher;
import com.google.gson.Gson;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Teacher t = new Teacher();
		t.setCode("12345");
		t.setName("张三");
		t.setPhone("138123456");
		t.setPhoto("no photo");
		System.out.println(new Gson().toJson(t));
	}

}
