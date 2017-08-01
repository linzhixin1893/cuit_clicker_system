package com.cuit.main;

import com.cuit.bean.Score;
import com.cuit.bean.StudentLogReply;
import com.cuit.bean.Teacher;
import com.cuit.bean.TeacherUploadScore;
import com.google.gson.Gson;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TeacherUploadScore up = new TeacherUploadScore();
		Score score = new Score();
		score.setCourseID(1);
		score.setStudentID(1);
		score.setNum("6");
		score.setReason("kaoqing");
		score.setTime("2017/8/2");
		Score score2 = new Score();
		score2.setCourseID(2);
		score2.setStudentID(1);
		score2.setNum("10");
		score2.setReason("dati");
		score2.setTime("2017/8/3");
		up.getScore().add(score);
		up.getScore().add(score2);
		System.out.println(new Gson().toJson(up));
	}

}
