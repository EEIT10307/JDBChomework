package com.iii.EEIT10330;

import java.sql.*;

public class BatchUpdateDemo2 {
	public static void main(String[] args) {
		Connection conn = null;

		try {     
			String connUrl = "jdbc:sqlserver://localhost:1433;databaseName=jdbc";
			conn = DriverManager.getConnection(connUrl, "sa", "passw0rd");
			
			String qryStmt = "SELECT empno, salary FROM employee";
			PreparedStatement pstmt = conn.prepareStatement(qryStmt);
			ResultSet rs = pstmt.executeQuery();
			
			String updateStmt = "UPDATE employee SET salary = ? WHERE empno = ?";
			pstmt = conn.prepareStatement(updateStmt);
			
			int batchsize = 3 ; 
			int x = 0 ; 
			boolean b ; 
			while (b=rs.next()) {
				pstmt.setDouble(1, rs.getDouble(2) * 1.1);
				pstmt.setInt(2, rs.getInt(1));
				pstmt.addBatch();
			  x++ ; 
			  if(x % batchsize == 0) {
				  pstmt.executeBatch();
				continue;}
			}
			 pstmt.executeBatch();
			
			qryStmt = "SELECT ename, salary FROM employee";
			pstmt = conn.prepareStatement(qryStmt);
			rs = pstmt.executeQuery();
			while(rs.next()) {
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
				} catch(SQLException e) {
					e.printStackTrace();
				}
		}
	}// end of main()
}// end of class BatchUpdateDemo
