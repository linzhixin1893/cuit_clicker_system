package com.cuit.bean;

public class Course {
	private int id;
	private int teacherId;
	private String teacherName;
	private String teacherPhoto;
	private String name;
	private String courseTime;//上课时间: 1-8:3:2 (1到8周，周3，第2节)
	private String weekTime;//单双周： single, double
	private String location; //上课地点
	private String description;//描述
	
	public Course() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCourseTime() {
		return courseTime;
	}
	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}
	public String getWeekTime() {
		return weekTime;
	}
	public void setWeekTime(String weekTime) {
		this.weekTime = weekTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getTeacherPhoto() {
		return teacherPhoto;
	}
	public void setTeacherPhoto(String teacherPhoto) {
		this.teacherPhoto = teacherPhoto;
	}
	public boolean isAnyParamEmpty() {
		boolean res = false;
		if (name == null || "".equals(name.trim())) {
			res = true;
		}
		if (name == null || "".equals(name.trim())) {
			res = true;
		}
		if (courseTime == null || "".equals(courseTime.trim())) {
			res = true;
		}
		if (weekTime == null || "".equals(weekTime.trim())) {
			res = true;
		}
		if (location == null || "".equals(location.trim())) {
			res = true;
		}
		if (description == null || "".equals(description.trim())) {
			res = true;
		}
		
		if (teacherPhoto == null || "".equals(teacherPhoto.trim())) {
			res = true;
		}
		if (teacherName == null || "".equals(teacherName.trim())) {
			res = true;
		}
		return res;
	}
	
	
}
