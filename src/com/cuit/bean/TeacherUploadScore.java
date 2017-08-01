package com.cuit.bean;

import java.util.ArrayList;
import java.util.List;

public class TeacherUploadScore {
	private List<Score> score = new ArrayList<Score>();

	public TeacherUploadScore() {
		super();
	}

	public TeacherUploadScore(List<Score> score) {
		super();
		this.score = score;
	}

	public List<Score> getScore() {
		return score;
	}

	public void setScore(List<Score> score) {
		this.score = score;
	}
	
}
