package com.cuit.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JDBCUtils {
	
	private static JDBCUtils mUtils = null;
	
	private JDBCUtils() {
		
	}
	
	public static JDBCUtils getInstance() {
		if (mUtils == null) {
			mUtils = new JDBCUtils();
		}
		return mUtils;
	}
	
	/**
	 * 获取数据库的连接
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/cuit";
		String name = "root";
//		String code = "512003";
		String code = "123456";
		return DriverManager.getConnection(url, name, code);	
	}
	
	
	/**
	 * 向指定数据库中插入数据
	 * @param table
	 * @param columns
	 * @param values
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean insert(String table, String[] columns, String[] values) throws ClassNotFoundException, SQLException {
		String sql = "insert into " + table + '(';
		for (int i = 0; i < columns.length; i++) {
			sql += columns[i];
			if (i != columns.length - 1) {
				sql += ',';
			}
		}
		sql += ") values(";
		for (int i = 0; i < values.length; i++) {
			sql += values[i];
			if (i != values.length - 1) {
				sql += ',';
			}
		}
		sql += ");";
		Connection conn = this.getConnection();
		Statement statement = conn.createStatement();
		int update = statement.executeUpdate(sql);
		this.closeStream(conn, statement, null);
		if (update > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 查询相应表中满足条件的记录是否存在，存在返回true，不存在返回false
	 * @param table		表名
	 * @param columns	字段数组
	 * @param values	值数值
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean check(String table, String[] columns, String[] values) throws ClassNotFoundException, SQLException {
		Connection conn = this.getConnection();
		Statement statement = conn.createStatement();
		String sql = "select * from " + table + " where ";
		int size = columns.length;
		for (int i = 0; i < size; i++) {
			sql += columns[i] + "=" + values[i];
			if (i != size -1) {
				sql += " and ";
			}
		}
		sql += ';';
		ResultSet resultSet = statement.executeQuery(sql);
		if (resultSet.next()) {
			this.closeStream(conn, statement, resultSet);
			return true;
		}
		this.closeStream(conn, statement, resultSet);
		return false;
	}
	
	public int queryID(String table, String[] columns, String[] values) throws ClassNotFoundException, SQLException {
		Connection conn = this.getConnection();
		Statement statement = conn.createStatement();
		String sql = "select * from " + table + " where ";
		int size = columns.length;
		for (int i = 0; i < size; i++) {
			sql += columns[i] + "=" + values[i];
			if (i != size -1) {
				sql += " and ";
			}
		}
		sql += ';';
		ResultSet set = statement.executeQuery(sql);
		int res = 0;
		while (set.next()) {
			res = set.getInt("id");
		}
		this.closeStream(conn, statement, set);
		return res;
	}
	
	public void closeStream(Connection conn, Statement statement, ResultSet set) throws SQLException {
		if (conn != null) {
			conn.close();
		}
		if (statement != null) {
			statement.close();
		}
		if (set != null) {
			set.close();
		}
	}

	public boolean update(String table, String where, String change) throws ClassNotFoundException, SQLException {
		Connection conn = this.getConnection();
		Statement statement = conn.createStatement();
		String sql = "update " + table + " set " + change + " where " + where + ";";
		int res = statement.executeUpdate(sql);
		if (res > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 通过组名获得组绑定的设备id
	 * @param groupName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int getDeviceID(String groupName) throws ClassNotFoundException, SQLException {
		Connection conn = this.getConnection();
		Statement statement = conn.createStatement();
		String sql = "select * from groups where group_name = '" + groupName + "';";
		ResultSet set = statement.executeQuery(sql);
		set.next();
		int id = set.getInt("device_id");
		this.closeStream(conn, statement, set);
		return id;
	}

	public HashMap<String, Object> query(String table, String where) throws ClassNotFoundException, SQLException {
		Connection conn = this.getConnection();
		Statement statement = conn.createStatement();
		String sql = "select * from " + table + " where " + where + ";";
		ResultSet set = statement.executeQuery(sql);
		HashMap<String, Object> res = new HashMap<String, Object>();
		res.put("conn", conn);
		res.put("statement", statement);
		res.put("set", set);
		return res;
	}

	public String getUserName(String userID) throws ClassNotFoundException, SQLException {
		Connection conn = this.getConnection();
		Statement statement = conn.createStatement();
		String sql = "select * from users where id = " + userID + ";";
		ResultSet set = statement.executeQuery(sql);
		set.next();
		String name = set.getString("account");
		this.closeStream(conn, statement, set);
		return name;
	}



}
