package com.example.graphplot;

public class Result {
	
	private int credit;
	private String grade;
	private String moduleCode;
	
	public Result(  String moduleCode , int credit,String grade) {
		this.grade=grade;
		this.moduleCode=moduleCode;
		this.credit=credit;
	}
	
	public String getModuleCode() {
		return moduleCode;
	}
	
	public int getCredit() {
		return credit;
	}
	
	public String getGrade() {
		return grade;
	}
	

}
