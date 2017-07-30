package com.cuit.bean;

public class HomeWork {
	private int id;
	private int courseID;
	private String content;
	private String startTime;
	private String endTime;
	
	public HomeWork() {
		super();
	}
	public HomeWork(int id, int courseID, String content, String startTime,
			String endTime) {
		super();
		this.id = id;
		this.courseID = courseID;
		this.content = content;
		this.startTime = startTime;
		this.endTime = endTime;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
}
