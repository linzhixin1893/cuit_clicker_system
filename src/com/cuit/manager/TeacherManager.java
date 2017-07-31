package com.cuit.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cuit.bean.Course;
import com.cuit.bean.Student;
import com.cuit.bean.Teacher;
import com.cuit.bean.TeacherLogReply;
import com.cuit.db.JDBCUtils;
import com.cuit.util.Util;

public class TeacherManager {

	private JDBCUtils mDBUtil;
	public String register(Teacher teacher) throws ClassNotFoundException, SQLException {
		mDBUtil = JDBCUtils.getInstance();
		boolean check = mDBUtil.check(
				"teacher",
				new String[]{"name", "phone"},
				new String[]{Util.getDBParam(teacher.getName()),
						Util.getDBParam(teacher.getPhone())}
		);
		if (check) {
			return "Teacher Existed";
		}
		boolean insert = mDBUtil.insert(
				"teacher",
				new String[]{"name", "phone", "code"},
				new String[]{
						Util.getDBParam(teacher.getName()),
						Util.getDBParam(teacher.getPhone()),
						Util.getDBParam(teacher.getCode())
						}
		);
		if (!insert) {
			return "Teacher Register Error";
		}
		
		return "Teacher Register Succeed";
	}
	public TeacherLogReply log(String phone, String code) throws ClassNotFoundException, SQLException {
		mDBUtil = JDBCUtils.getInstance();
		boolean check = mDBUtil.check(
				"teacher",
				new String[]{"phone", "code"},
				new String[]{Util.getDBParam(phone),
						Util.getDBParam(code)}
		);
		if (!check) {
			return null;
		}
		HashMap<String, Object> query = mDBUtil.query("teacher", "phone = " + Util.getDBParam(phone));
		ResultSet set = (ResultSet) query.get("set");
		Statement statement = (Statement) query.get("statement");
		Connection conn = (Connection) query.get("conn");
		TeacherLogReply reply = new TeacherLogReply();
		while (set.next()) {
			int id = set.getInt("id");
			reply.setTeacherID(id);
		}
		mDBUtil.closeStream(conn, statement, set);
		if (reply.getTeacherID() == -1) {
			return null;
		}
		HashMap<String, Object> query2 = mDBUtil.query("course", "teacher_id = " + reply.getTeacherID());
		ResultSet set2 = (ResultSet) query2.get("set");
		Statement statement2 = (Statement) query2.get("statement");
		Connection conn2 = (Connection) query2.get("conn");
		while (set2.next()) {
			Course course = new Course();
			course.setId(set2.getInt("id"));
			
			course.setTeacherId(reply.getTeacherID());
			course.setTeacherName(set2.getString("teacher_name"));
			course.setTeacherPhoto(set2.getString("teacher_photo"));
			
			course.setName(set2.getString("name"));
			course.setLocation(set2.getString("location"));
			course.setCourseTime(set2.getString("course_time"));
			course.setWeekTime(set2.getString("week_time"));
			course.setDescription(set2.getString("description"));
			
			reply.getCourse().add(course);
		}
		mDBUtil.closeStream(conn2, statement2, set2);
		
		
		for (int i = 0; i < reply.getCourse().size(); i++) {
			int courseID = reply.getCourse().get(i).getId();
			HashMap<String, Object> query3 = mDBUtil.query("student_course_info", "course_id = " + courseID);
			ResultSet set3 = (ResultSet) query3.get("set");
			Statement statement3 = (Statement) query3.get("statement");
			Connection conn3 = (Connection) query3.get("conn");
			List<Student> studentList = new ArrayList<Student>();
			while (set3.next()) {
				Student student = new Student();
				student.setId(set3.getInt("student_id"));
				studentList.add(student);
			}
			reply.getStudentMap().put(courseID, studentList);
			mDBUtil.closeStream(conn3, statement3, set3);
		}
		for (int i = 0; i < reply.getCourse().size(); i++) {
			int courseID = reply.getCourse().get(i).getId();
			List<Student> studentList = reply.getStudentMap().get(courseID);
			for (Student s : studentList) {
				HashMap<String, Object> query3 = mDBUtil.query("student", "id = " + s.getId());
				ResultSet set3 = (ResultSet) query3.get("set");
				Statement statement3 = (Statement) query3.get("statement");
				Connection conn3 = (Connection) query3.get("conn");
				while (set3.next()) {
					s.setName(set3.getString("name"));
					s.setNumber(set3.getString("number"));
					s.setPhone(set3.getString("phone"));
					s.setCode(set3.getString("code"));
				}
				mDBUtil.closeStream(conn3, statement3, set3);
			}
		}
		
		
		return reply;
	}

}
