package com.example.graphplot;

/**
 * Description of Exam
 * represent exam details
 *
 * @author chamath sajeewa
 * chamaths.10@cse.mrt.ac.lk
 */


import java.util.Iterator;
import java.util.LinkedList;

public class Exam {
	
	private int semester;
	private int numOfSubjects;
	private LinkedList<Result>results;
	
	
	public Exam(int semester, int numOfSubjects, LinkedList<Result> results) {
		this.semester=semester;
		this.numOfSubjects=numOfSubjects;
		this.results=results;
	}
	
	public int getSemester() {
		return semester;
	}
	
	public int getNumOfSubjects() {
		return numOfSubjects;
	}
	
	public LinkedList<Result> getResults() {
		return results;
	}
	
	public double calculateSGPA(){
		
		Iterator<Result>iter=results.listIterator();
		String grade;
		double totalCredit=0;
		double obtainedValue=0;
		double gradeCredit=0;
		Result result;
		while(iter.hasNext()){
			result=iter.next();
			grade=result.getGrade();
			//I-CA etc
			if(grade.length()==3){
				gradeCredit=0;
			}
			if(grade.length()==2){
				if(grade.charAt(0)=='A' && grade.charAt(1)=='+')
					gradeCredit=4.2;
				if(grade.charAt(0)=='A' && grade.charAt(1)=='-')
					gradeCredit=3.7;
				if(grade.charAt(0)=='B' && grade.charAt(1)=='+')
					gradeCredit=3.3;
				if(grade.charAt(0)=='B' && grade.charAt(1)=='-')
					gradeCredit=2.7;
				if(grade.charAt(0)=='C' && grade.charAt(1)=='+')
					gradeCredit=2.3;
				if(grade.charAt(0)=='C' && grade.charAt(1)=='-')
					gradeCredit=1.5;
				
			}
			else{
				if(grade.charAt(0)=='A' )
					gradeCredit=4.0;
				if(grade.charAt(0)=='B' )
					gradeCredit=3.0;
				if(grade.charAt(0)=='C' )
					gradeCredit=2.0;
				if(grade.charAt(0)=='D' )
					gradeCredit=1.0;
				if(grade.charAt(0)=='I' )
					gradeCredit=0;
				
				
			}
			obtainedValue=obtainedValue+ gradeCredit*result.getCredit();
			totalCredit=totalCredit+result.getCredit();
		}
		
		double GPA=obtainedValue/totalCredit;
		return GPA;
	}
	
}
