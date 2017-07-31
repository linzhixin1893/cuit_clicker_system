package com.cuit.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeacherLogReply {
	private int teacherID = -1;
	private List<Course> course = new ArrayList<Course>();
	private Map<Integer, List<Student>> studentMap = new HashMap<Integer, List<Student>>();
	
	public TeacherLogReply() {
		super();
	}
	public TeacherLogReply(int teacherID, List<Course> course,
			Map<Integer, List<Student>> studentMap) {
		super();
		this.teacherID = teacherID;
		this.course = course;
		this.studentMap = studentMap;
	}
	public int getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
	}
	public List<Course> getCourse() {
		return course;
	}
	public void setCourse(List<Course> course) {
		this.course = course;
	}
	public Map<Integer, List<Student>> getStudentMap() {
		return studentMap;
	}
	public void setStudentMap(Map<Integer, List<Student>> studentMap) {
		this.studentMap = studentMap;
	}

	
}
