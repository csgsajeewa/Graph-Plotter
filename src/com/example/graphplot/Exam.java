package com.example.graphplot;

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
	
	public void setResults() {
		
		for (int i = 0; i < results.size(); i++) {
			
		}
	}

}
