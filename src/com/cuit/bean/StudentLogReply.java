package com.cuit.bean;

import java.util.ArrayList;
import java.util.List;

public class StudentLogReply {
	private int studentID = -1;
	private List<HomeWork> homeWork = new ArrayList<HomeWork>();;
	private List<Course> course = new ArrayList<Course>();
	private List<Score> score = new ArrayList<Score>();
	
	public StudentLogReply(List<HomeWork> homeWork, List<Course> course, int studentID, List<Score> score) {
		super();
		this.homeWork = homeWork;
		this.course = course;
		this.studentID = studentID;
		this.score = score;
	}
	
	public StudentLogReply() {
		super();
	}
	
	public List<HomeWork> getHomeWork() {
		return homeWork;
	}
	public void setHomeWork(List<HomeWork> homeWork) {
		this.homeWork = homeWork;
	}
	public List<Course> getCourse() {
		return course;
	}
	public void setCourse(List<Course> course) {
		this.course = course;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public List<Score> getScore() {
		return score;
	}

	public void setScore(List<Score> score) {
		this.score = score;
	}
	
	
	
	
}
