package com.dd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class JdbcSql {
	private static Connection getConn() {
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/test";
	    String username = "root";
	    String password = "123456";
	    Connection conn = null;
	    try {
	        Class.forName(driver); //classLoader,加载对应驱动
	        conn = DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
	
	public static int insert(User user) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "insert into user (longurl,shorturl,time) values(?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, user.getLongurl());
	        pstmt.setString(2, user.getShorturl());
	        pstmt.setInt(3, user.getTime());
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	
	public static int insert_visit(String ip, String shorturl, int time) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "insert into visit (ip,shorturl,time) values(?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, ip);
	        pstmt.setString(2, shorturl);
	        pstmt.setInt(3, time);
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	
	public static String getLong(String shorturl) {
	    Connection conn = getConn();
	    String sql = "select * from user where shorturl=?";
	    PreparedStatement pstmt;
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, shorturl);
	        ResultSet rs = pstmt.executeQuery();
	        if(rs.next() == false) {
	        	return null;
	        }
	        else
	        	return rs.getString("longurl");
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public static int getLong_visit(String shorturl) {
	    Connection conn = getConn();
	    String sql = "select * from visit where shorturl=?";
	    PreparedStatement pstmt;
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, shorturl);
	        ResultSet rs = pstmt.executeQuery();
	        if(rs.next() == false) {
	        	return -1;
	        }
	        else
	        	return rs.getInt("time");
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return 0;
	}
	
	public static int update_visit(int time, String ip, String shorturl) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "update visit set time='" + time + "' where ip='" + ip + "' and shorturl='" + shorturl + "'";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
}
