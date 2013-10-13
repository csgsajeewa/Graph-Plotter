package com.example.graphplot;
/**
 * Description of SGPAComparsionWindow
 * Provides a graph which compares number of subjects with SGPA
 *
 * @author chamath sajeewa
 * chamaths.10@cse.mrt.ac.lk
 */

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.XYStepMode;

public class SGPAComparsionWindow extends Activity{
	
	private XYPlot plot;
	
	private List<Double> SGPAS; 
	private List<Integer> numOfSubjects;
	 
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.simple_xy_plot_example);
        SGPAS =new ArrayList<Double>();
    	numOfSubjects=new ArrayList<Integer>();
    	
    	/////////////////////////////configure graph////////////////////////////////
        // initialize  XYPlot reference:
        plot = (XYPlot) findViewById(R.id.mySimpleXYPlot);
        //set up the two axis labels
        plot.setDomainLabel("Semesters");
        plot.setRangeLabel("GPA,Subject");
         // remove the decimal places:
        plot.setDomainValueFormat(new DecimalFormat("#"));
        plot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 1);// set configuration for x axis
        plot.setRangeStep(XYStepMode.INCREMENT_BY_VAL, 0.1);// set configuration for y axis
        plot.setDomainLeftMax(0); //set end points of domain
        plot.setDomainRightMin(9);//set end points of domain
        plot.setRangeTopMin(9);
        /////////////////////////////end of configuration////////////////////////////////////////////
        
        getSGPAS(); // obtain SGPA values
      
        ///////////////////////first series///////////////////////////////////
        //give values to plot - Number of subjects
      
        List <Integer> seriesNumbers=new ArrayList<Integer>();
        for(int i=0; i< numOfSubjects.size(); i++){
        	seriesNumbers.add(i+1);
        }
       
        XYSeries series1 = new SimpleXYSeries(seriesNumbers,numOfSubjects,"Num Subjects");// Set the display title of the series
 
        // Create a formatter to use for drawing a series using LineAndPointRenderer
        // and configure it from xml:
        LineAndPointFormatter series1Format = new LineAndPointFormatter();
        series1Format.setPointLabelFormatter(new PointLabelFormatter());
        series1Format.configure(getApplicationContext(),R.layout.line_point_formatter_with_plf1);
 
        // add a new series' to the xyplot:
        plot.addSeries(series1, series1Format);
        //////////////////////end of first series//////////////////////////////////////
        
       ////////////////////////second series//////////////////////////////////////////////
       //give values to plot - My GPA
       
       
 
       
        XYSeries series2 = new SimpleXYSeries(seriesNumbers,SGPAS,"Your GPA");// Set the display title of the series
 
       // Create a formatter to use for drawing a series using LineAndPointRenderer
        // and configure it from xml:
        LineAndPointFormatter series2Format = new LineAndPointFormatter();
        series2Format.setPointLabelFormatter(new PointLabelFormatter());
        series2Format.configure(getApplicationContext(),R.layout.line_point_formatter_with_plf2);
 
        // add a new series' to the xyplot:
        plot.addSeries(series2, series2Format);
 
       //////////////////////////end of second series///////////////////////////////////////////////////////
 
        // reduce the number of range labels
        plot.setTicksPerRangeLabel(3);
        plot.getGraphWidget().setDomainLabelOrientation(-45);
 
    }
    //obtain SGPA values
    void getSGPAS(){
    	Exam exam;
    	GradeDatabase gradeDatabase=new GradeDatabase(this);
    	int semester=1;
    	
    	while(true){
    		exam=gradeDatabase.getResults(semester);
    		if(!exam.getResults().isEmpty()){
    		 numOfSubjects.add(exam.getNumOfSubjects());
    		double SGPA= Math.round(exam.calculateSGPA()*10000.0)/10000.0;
    		 SGPAS.add(SGPA);
    		 semester++;
    		}
    		else{
    			break;
    		}
    		
    	}
    	
    	
    }
}
