package com.cuit.main;

import com.cuit.bean.StudentLogReply;
import com.google.gson.Gson;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String json = "{\"studentID\":1,\"homeWork\":[{\"id\":1,\"courseID\":1,\"content\":\"第一章：2,3,4\",\"startTime\":\"2017.7.30\",\"endTime\":\"2017.8.5\"}],\"course\":[{\"id\":1,\"teacherId\":1,\"teacherName\":\"李艳\",\"teacherPhoto\":\"photo path\",\"name\":\"高数\",\"courseTime\":\"1-20:3:1\",\"weekTime\":\"both\",\"location\":\"2107\",\"description\":\"高等数学下\"}]}";
		StudentLogReply r = new Gson().fromJson(json, StudentLogReply.class);
		System.out.println(new Gson().toJson(r));
	}

}
