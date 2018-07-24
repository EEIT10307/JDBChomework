package com.iii.EEIT10330;

import java.sql.*;
import java.io.*;

public class insertPicToDatabase {
	public static void main(String[] args) {
		Connection conn = null;
		Connection conn1 = null;
		/*  將圖片存到資料庫中  請先於資料庫的employee表格 alter table新的column    */
		
		try {     
			String connUrl = "jdbc:sqlserver://localhost:1433;databaseName=jdbc";
			conn = DriverManager.getConnection(connUrl, "sa", "passw0rd");
			conn1 = DriverManager.getConnection(connUrl, "sa", "passw0rd");
			String qryStmt = "SELECT empno FROM employee";
			PreparedStatement stmt1 = conn1.prepareStatement(qryStmt);  
			ResultSet rss = stmt1.executeQuery();  // 用來抓員工編號使用
			
			while(rss.next() ) {
			String inFile = "res/"+rss.getInt(1)+".jpg"  ;  // 讀取要輸入的檔案 因檔案名稱等於員工名稱 故使用getint(1) 
			File f = new File(inFile);
			FileInputStream fis = new FileInputStream(f);
			String insertStmt = "UPDATe employee SET pic = ? where empno = ?";		
			PreparedStatement stmt = conn.prepareStatement(insertStmt);
			stmt.setBinaryStream(1, fis, f.length());
			stmt.setInt(2, rss.getInt(1));
			stmt.addBatch();
			stmt.executeBatch();
//			int rrs = stmt.executeUpdate();
//			System.out.println(rrs);
			System.out.println("UPDATA employee is successful!");
			
			stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch(SQLException e) {
				
				}
		}
	}// end of main()
}// end of class BLOBDemo 
