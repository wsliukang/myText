package com.xiteng.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBUtil {
	public static final String user="root";
	public static final String password="admin";
	public static final String url="jdbc:mysql://localhost:3306/xiteng";
	public static final String Drive="com.mysql.jdbc.Driver";
	
	static {
		try {
			Class.forName(Drive);
		}
		catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(url,user,password);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void closeJDBC(Connection con,Statement st, ResultSet rs) {
		if(st!=null) {
			try {
				st.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if(rs!=null) {
			try {
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

}
