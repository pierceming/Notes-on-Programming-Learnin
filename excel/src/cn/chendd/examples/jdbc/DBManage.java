package cn.chendd.examples.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManage {

	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName(oracle.jdbc.driver.OracleDriver.class.getName()).newInstance();
			String url = "jdbc:oracle:thin:@xxx.xxx.xxx.xxx:1521:xxx";
			conn = DriverManager.getConnection(url, "xxx", "xxx");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void main(String[] args) throws Exception {
		
		Connection conn = getConnection();
		PreparedStatement pst = conn.prepareStatement("select 123456 tt from dual");
		ResultSet rs = pst.executeQuery();
		if(rs.next()){
			System.out.println(rs.getString("tt"));
		}
		
		rs.close();
		pst.close();
		conn.close();
		
	}
	
	public static void Close(Connection conn){
		if(conn != null){
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
