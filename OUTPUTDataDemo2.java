
package com.iii.EEIT10330;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class OUTPUTDataDemo2 {
	public static void main(String[] args) throws IOException {		
File f1 = new File("D:\\JDBC\\workspace\\advance\\res" , "output.txt") ; 
		if(!f1.getParentFile().exists()) {
			f1.getParentFile().mkdirs() ; 		
				f1.createNewFile() ;		 
		}else {
				f1.createNewFile() ;	
		} 	
		FileWriter fw = new FileWriter(f1) ;  		
		Connection conn = null;
		try {     
			String connUrl = "jdbc:sqlserver://localhost:1433;databaseName=jdbc";
			conn = DriverManager.getConnection(connUrl, "sa", "passw0rd");
			
			String qryStmt = "SELECT * FROM employee";
			PreparedStatement stmt = conn.prepareStatement(qryStmt);
			ResultSet rs = stmt.executeQuery();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			StringBuffer S = new StringBuffer()  ;  // �s�W���{��
			for(int i = 1; i <= count; i++) {
				if(i == count) {
					System.out.print(rsmd.getColumnLabel(i)) ;
				S.append(rsmd.getColumnLabel(i)) ; 	 // �s�W���{��
				}
				else {
				System.out.print(rsmd.getColumnLabel(i) + ",");
				S.append(rsmd.getColumnLabel(i)+",") ; }	 // �s�W���{��	
			}		
			fw.write(S.toString() + "\r\n");	 // �s�W���{��
			System.out.print("\n");
			S = new StringBuffer() ;   // �s�W���{��
			
			while(rs.next()) {
	     		for(int i = 1; i <= count; i++)
	     			if( i == count) {
	     				System.out.print(rs.getString(i) ); 
	     				S.append(rs.getString(i)+"\n") ;   // �s�W���{��
	     			} else{
	         		System.out.print(rs.getString(i) + ","); 
	         		S.append(rs.getString(i)+",") ;// �s�W���{��
	     			}
	     		System.out.print("\n");
	     		fw.write(S.toString() + "\r\n"); // �s�W���{��
	     		S = new StringBuffer() ; // �s�W���{��
	     		
			}
			
			fw.close(); // �s�W���{��
			rs.close();
			stmt.close();
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
}// end of class ResultSetMetaDataDemo 
