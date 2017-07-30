package com.cuit.bean;

public class Score {
	private int id;
	private int courseID;
	private int studentID;
	private String reason;
	private String time;
	private String num;
	
	
	public Score() {
		super();
	}
	public Score(int id, int courseID, int studentID, String reason,
			String time, String num) {
		super();
		this.id = id;
		this.courseID = courseID;
		this.studentID = studentID;
		this.reason = reason;
		this.time = time;
		this.num = num;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public int getStudentID() {
		return studentID;
	}
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
	
}
