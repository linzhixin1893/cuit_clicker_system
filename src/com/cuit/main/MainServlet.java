package com.cuit.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cuit.bean.Course;
import com.cuit.bean.Student;
import com.cuit.bean.StudentLogReply;
import com.cuit.bean.Teacher;
import com.cuit.bean.TeacherLogReply;
import com.cuit.manager.StudentManager;
import com.cuit.manager.TeacherManager;
import com.google.gson.Gson;
import com.cuit.util.*;

public class MainServlet extends HttpServlet {

	private StudentManager mStudentManager = new StudentManager();
	private TeacherManager mTeacherManager = new TeacherManager();
	
	/**
	 * Constructor of the object.
	 */
	public MainServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		System.out.println(action);
		if (action == null || "".equals(action.trim())) {
			out.print("parameter is null");
			out.flush();
			out.close();
			return;
		}
		if ("student_register".equals(action)) {
			/**
			 * 学生注册
			 * 由手机端发起
			 */
			String json = request.getParameter("param");
			Student student = null;
			if (json != null && !"".equals(json)) {
				student = new Gson().fromJson(json, Student.class);
			}
			if (student == null || student.isAnyParamEmpty()) {
				out.print("student is null");
				out.flush();
				out.close();
				return;
			}
			System.out.println(new Gson().toJson(student));
			String res = null;
			try {
				res = mStudentManager.register(student);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print(res);
		} else if ("student_log".equals(action)) {
			/**
			 * 学生登录，获取课程、得分、作业等所有信息
			 * 由手机端发起
			 */
			String number = request.getParameter("number");
			String code = request.getParameter("code");
			if (Util.isEmptyOrNull(number) || Util.isEmptyOrNull(code)) {
				out.print("param is null");
				out.flush();
				out.close();
				return;
			}
			StudentLogReply logReply = null;
			try {
				logReply = mStudentManager.log(number, code);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (logReply == null) {
				out.print("Log Failed");
			} else {
				out.print(new Gson().toJson(logReply));
			}
		} else if ("teacher_register".equals(action)) {
			/**
			 * 教师注册，有教室端发起
			 */
			String json = request.getParameter("param");
			if (Util.isEmptyOrNull(json)) {
				out.print("param is null");
				out.flush();
				out.close();
				return;
			}
			Teacher teacher = new Gson().fromJson(json, Teacher.class);
			System.out.println(new Gson().toJson(teacher));
			String res = null;
			try {
				res = mTeacherManager.register(teacher);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print(res);
		} else if ("teacher_log".equals(action)) {
			/**
			 * 教师登录，获取所有课程、和该课程的所有学生
			 * 由教室端发起
			 */
			String phone = request.getParameter("phone");
			String code = request.getParameter("code");
			TeacherLogReply teacherLogReply = null;
			try {
				teacherLogReply = mTeacherManager.log(phone, code);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (teacherLogReply == null) {
				out.print("Log Failed");
			} else {
				out.print(new Gson().toJson(teacherLogReply));
			}
		} else if ("upload_score".equals(action)) {
			/**
			 * 上传学生得分，由教室端发起
			 */
		} else if ("student_add_course".equals(action)) {
			/**
			 * 学生选课程
			 * 由手机端发起
			 */
			String courseID = request.getParameter("courseID");
			String studentID = request.getParameter("studentID");
			if (Util.isEmptyOrNull(courseID) || Util.isEmptyOrNull(studentID)) {
				out.print("param is null");
				out.flush();
				out.close();
				return;
			}
			String res = null;
			try {
				res = mStudentManager.addCourse(studentID, courseID);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print(res);
		}
		
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
