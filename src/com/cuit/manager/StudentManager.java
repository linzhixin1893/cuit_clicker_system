package com.cuit.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cuit.bean.Course;
import com.cuit.bean.HomeWork;
import com.cuit.bean.Score;
import com.cuit.bean.Student;
import com.cuit.bean.StudentLogReply;
import com.cuit.db.JDBCUtils;
import com.cuit.util.*;
import com.google.gson.Gson;

public class StudentManager {

	private JDBCUtils mDBUtil;
	
	public String register(Student student) throws ClassNotFoundException, SQLException {
		mDBUtil = JDBCUtils.getInstance();
		boolean check = mDBUtil.check(
				"student",
				new String[]{"number"},
				new String[]{Util.getDBParam(student.getNumber())}
		);
		if (check) {
			return "Student Existed";
		}
		boolean insert = mDBUtil.insert(
				"student",
				new String[]{"name", "code", "number", "phone"},
				new String[]{
						Util.getDBParam(student.getName()),
						Util.getDBParam(student.getCode()),
						Util.getDBParam(student.getNumber()),
						Util.getDBParam(student.getPhone())
						}
		);
		if (!insert) {
			return "Student Register Error";
		}
		
		return "Student Register Succeed";
	}

	public String addCourse(String studentID, String courseID) throws ClassNotFoundException, SQLException {
		mDBUtil = JDBCUtils.getInstance();
		boolean check = mDBUtil.check(
				"student_course_info", 
				new String[]{"student_id", "course_id"}, 
				new String[]{studentID, courseID});
		if (check) {
			return "Existed";
		}
		boolean insert = mDBUtil.insert("student_course_info", 
				new String[]{"student_id", "course_id"}, 
				new String[]{studentID, courseID});
		if (!insert) {
			return "Failed";
		}
		return "Succeed";
	}

	public StudentLogReply log(String number, String code) throws ClassNotFoundException, SQLException {
		mDBUtil = JDBCUtils.getInstance();
		boolean check = mDBUtil.check(
				"student", 
				new String[]{"number", "code"}, 
				new String[]{Util.getDBParam(number), Util.getDBParam(code)});
		System.out.println(check);
		if (!check) {
			return null;
		}
		HashMap<String,Object> query = mDBUtil.query("student", "number = " + Util.getDBParam(number));
		ResultSet set = (ResultSet) query.get("set");
		Statement statement = (Statement) query.get("statement");
		Connection conn = (Connection) query.get("conn");
		StudentLogReply reply = new StudentLogReply();
		while (set.next()) {
			int id = set.getInt("id");
			reply.setStudentID(id);
		}
		mDBUtil.closeStream(conn, statement, set);
		if (reply.getStudentID() == -1) {
			return null;
		}
		HashMap<String,Object> query2 = mDBUtil.query("student_course_info", "student_id = " + reply.getStudentID());
		ResultSet set2 = (ResultSet) query2.get("set");
		Statement statement2 = (Statement) query2.get("statement");
		Connection conn2 = (Connection) query2.get("conn");
		List<Integer> courseIDList = new ArrayList<Integer>();
		while (set2.next()) {
			int courseID = set2.getInt("course_id");
			courseIDList.add(courseID);
			System.out.println(courseID);
		}
		mDBUtil.closeStream(conn2, statement2, set2);
		for (int i = 0; i < courseIDList.size(); i++) {
			HashMap<String,Object> query3 = mDBUtil.query("course", "id = " + courseIDList.get(i));
			ResultSet set3 = (ResultSet) query3.get("set");
			Statement statement3 = (Statement) query3.get("statement");
			Connection conn3 = (Connection) query3.get("conn");
			set3.next();
			Course course = new Course();
			course.setId(courseIDList.get(i));
			
			course.setTeacherId(set3.getInt("teacher_id"));
			course.setTeacherName(set3.getString("teacher_name"));
			course.setTeacherPhoto(set3.getString("teacher_photo"));
			
			course.setName(set3.getString("name"));
			course.setLocation(set3.getString("location"));
			course.setCourseTime(set3.getString("course_time"));
			course.setWeekTime(set3.getString("week_time"));
			course.setDescription(set3.getString("description"));
			
			reply.getCourse().add(course);
			System.out.println(new Gson().toJson(course));
			mDBUtil.closeStream(conn3, statement3, set3);
		}
		
		for (int i = 0; i < courseIDList.size(); i++) {
			HashMap<String,Object> query3 = mDBUtil.query("home_work", "course_id = " + courseIDList.get(i));
			ResultSet set3 = (ResultSet) query3.get("set");
			Statement statement3 = (Statement) query3.get("statement");
			Connection conn3 = (Connection) query3.get("conn");
			while (set3.next()) {
				HomeWork work = new HomeWork();
				work.setId(set3.getInt("id"));
				work.setCourseID(courseIDList.get(i));
				work.setContent(set3.getString("work_content"));
				work.setStartTime(set3.getString("start_time"));
				work.setEndTime(set3.getString("end_time"));
				reply.getHomeWork().add(work);
				
				
			}
			mDBUtil.closeStream(conn3, statement3, set3);

		}
		
		HashMap<String,Object> query4 = mDBUtil.query("score", "student_id = " + reply.getStudentID());
		ResultSet set4 = (ResultSet) query4.get("set");
		Statement statement4 = (Statement) query4.get("statement");
		Connection conn4 = (Connection) query4.get("conn");
		while (set4.next()){
			Score score = new Score();
			score.setCourseID(set4.getInt("course_id"));
			score.setId(set4.getInt("id"));
			score.setNum(set4.getString("num"));
			score.setReason(set4.getString("reason"));
			score.setStudentID(reply.getStudentID());
			score.setTime(set4.getString("score_time"));
			
			reply.getScore().add(score);
		}
		mDBUtil.closeStream(conn4, statement4, set4);
		return reply;
	}

}
