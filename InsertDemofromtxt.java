package com.iii.EEIT10330;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

// Insert from document
public class InsertDemofromtxt {
	public static void main(String[] args) throws IOException {
		
		/*  以作業一輸出的文字檔為基礎  從文字檔讀入資料再放入資料庫    */
		
		Connection conn = null;
		try {
			String connUrl = "jdbc:sqlserver://localhost:1433;databaseName=jdbc";
			conn = DriverManager.getConnection(connUrl, "sa", "passw0rd");

			File fi = new File("D:\\JDBC\\workspace\\advance\\res", "insert.txt");//  文字檔來源
			FileReader fr = new FileReader(fi);
			BufferedReader bf = new BufferedReader(fr); // 讀取要輸入的檔案
			String insSt = "SELECT * FROM employee";
			PreparedStatement pstmt = conn.prepareStatement(insSt);
			ResultSet rs1 = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs1.getMetaData(); // 用來抓目標欄位的個數
			String sb = null; // 把讀到的行塞進去
			int x = 0;
			tk1: while ((sb = bf.readLine()) != null) {
				x++;

				if (sb.equals("")) {
					break tk1;
				}
				if (x != 1) {
					String insStmt = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?)";
					String[] sb1 = sb.split(",");// 目的是要篩選掉 , 符號    
					PreparedStatement pss = conn.prepareStatement(insStmt);
					for (int y = 1; y <= rsmd.getColumnCount(); y++) {
						System.out.println(sb1[y - 1]);
						pss.setObject(y, sb1[y - 1]);  // 因目標欄位有許多不同的屬性 故使用 OBJET放進去
						if (y == 6) {
							pss.addBatch();
							pss.executeBatch();
						}

					}

				}
			}

			pstmt = conn.prepareStatement("SELECT * FROM employee");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.print("name = " + rs.getString("ename") + ", ");
				System.out.println("salary = " + rs.getDouble("salary"));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}// end of main()
}// end of class InsertDemo
