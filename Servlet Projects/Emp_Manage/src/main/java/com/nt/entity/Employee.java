package com.nt.entity;

public class Employee {

	int id;
	String name ;
	
	int sal;
	String dep;
	
	public Employee(int id, String name, int sal) {
		super();
		this.id = id;
		this.name = name;
		this.sal = sal;
	}

	public Employee(int id, String name, int sal, String dep) {
		super();
		this.id = id;
		this.name = name;
		this.sal = sal;
		this.dep = dep;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}
}
