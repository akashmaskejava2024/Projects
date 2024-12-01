package com.nt;

import com.nt.dao.EmpDAO;

public class inset_Test_emp {

	
	public static void main(String[] args) {
		
		EmpDAO d = new EmpDAO();
		
		d.InsertEmp(1, "akash", 1000, true);
		
	}
}
