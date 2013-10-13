package com.example.graphplot;
/**
 * Description of Result
 * Represent individual result details
 *
 * @author chamath sajeewa
 * chamaths.10@cse.mrt.ac.lk
 */


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
