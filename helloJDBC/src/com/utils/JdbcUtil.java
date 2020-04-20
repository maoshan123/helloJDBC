package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtil {
	static String driverClass = "oracle.jdbc.driver.OracleDriver"; // oracle的驱动
	static String url = "jdbc:oracle:thin:@localhost:1521:ORCL";// 连接oracle路径方式
	static String user = "system";//  //user是数据库的用户名
	static String password = "123456";//   //用户登录密码

	public static Connection getconn() {

		Connection conn = null;
		try {
			// //首先建立驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 驱动成功后进行连接
			conn = DriverManager.getConnection(url, user, password);

			System.out.println("连接成功");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) {
		String sql = "select acc from BRCH_QRY_DTL";
		System.out.println(sql);
		Connection conn = getconn();
		PreparedStatement pt;
		try {
			pt = conn.prepareStatement(sql);

			ResultSet rs = pt.executeQuery();
			System.out.println(rs);

			while (rs.next()) {
				String acc = rs.getString("acc");
				System.out.println(acc);
			}
			rs.close();
			pt.close();
			conn.close();
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		}

	}

}
