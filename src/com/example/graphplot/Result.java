package com.example.graphplot;

public class Result {
	
	private double credit;
	private String grade;
	private String moduleCode;
	
	public Result(  String moduleCode , double credit,String grade) {
		this.grade=grade;
		this.moduleCode=moduleCode;
		this.credit=credit;
	}
	
	public String getModuleCode() {
		return moduleCode;
	}
	
	public double getCredit() {
		return credit;
	}
	
	public String getGrade() {
		return grade;
	}
	

}
