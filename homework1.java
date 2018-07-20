package com.iii.EEIT103.Kuo;

import java.util.ArrayList;

public class homework1 {

	public static void main(String[] args) {
		// System.out.println("----------第一------------");
		// String s = "SELECT ename, salary, FROM employee";
		// String [] s1 = s.split(", | ") ;
		// for (String s2 : s1) {
		// System.out.println(s2);
		// }

		// System.out.println("----------第二------------");
		// String s = "SELECT ename, salary, ART, TRTR, FROM employee";
		// String s1 = s.replace(",","" ) ;
		// String [] s2 = s1.split(" ") ;
		// for (String s3 : s2) {
		// System.out.println(s3);
		// }

		System.out.println("----------亂加空白+全部轉小寫+刪除FROM+Employee移到第二個------------");
		String s = " SELECT  ename,        salAry,   ART, FRF    FRoM    employee TEST1 TEST2,,,      ";
		String[] s2 = s.split(",| ");
		ArrayList<String> list = new ArrayList<>();
		for (String s3 : s2) {
			if (!s3.isEmpty() & !s3.equalsIgnoreCase("from")) {
				list.add(s3);
			}
		}		
		for ( int x = 0 ; x< list.size() ; x ++    ) {			
			if (list.get(x).equals("employee")) {
				list.add(1, list.get(x));
				list.remove(x+1);		
			}						
		}				
		for (String s4 : list)
			System.out.println(s4.toLowerCase());
	}
}
