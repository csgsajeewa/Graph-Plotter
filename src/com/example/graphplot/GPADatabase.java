package com.example.graphplot;

/**
 * Description of GPADatabase
 *not used, to be removed
 * @author chamath sajeewa
 * chamaths.10@cse.mrt.ac.lk
 */

import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class GPADatabase {
  
  public static final String KEY_ID = "_id";
  public static final String SUGGEST_COLUMN_TEXT_1="suggest_text_1";
  public static final String SUGGEST_COLUMN_INTENT_DATA="suggest_intent_data";
  //The name and column index of each column in your database.
  //These should be descriptive.
  public static final String SGPA ="SGPA";
  public static final String SEMESTER ="SEMESTER";
  
  


  // Database open/upgrade helper
  private MapDBOpenHelper mapDBOpenHelper;
   public GPADatabase() {
	
   }


  public GPADatabase(Context context) {
    mapDBOpenHelper = new MapDBOpenHelper(context, MapDBOpenHelper.DATABASE_NAME, null, 
                                              MapDBOpenHelper.DATABASE_VERSION);
  }
  
  // Called when you no longer need access to the database.
  public void closeDatabase() {
    mapDBOpenHelper.close();
  }

  
  private Cursor getGPACursor(int semester) {
    
    // Specify the result column projection. Return the minimum set
    // of columns required to satisfy your requirements.
    String[] result_columns = new String[] { 
      KEY_ID,SEMESTER , SGPA}; 
    
    // Specify the where clause that will limit our results.
    String where = SEMESTER + "=" + semester ;
    
    // Replace these with valid SQL statements as necessary.
    String whereArgs[] = null;
    String groupBy = null;
    String having = null;
    String order = null;
    
    SQLiteDatabase db = mapDBOpenHelper.getWritableDatabase();
    Cursor cursor = db.query(MapDBOpenHelper.DATABASE_TABLE,  result_columns, where,whereArgs, groupBy, having, order);
    //
    return cursor;
  }
  
  public double getGPA(int semester) {
    Cursor cursor = getGPACursor(semester);// get cursor
    double tempSGPA=0;
    // Find the index to the column(s) being used.
    int SGPA_INDEX =cursor.getColumnIndexOrThrow(SGPA);
    while (cursor.moveToNext()) {
    tempSGPA= cursor.getDouble(SGPA_INDEX);
    }
    	return tempSGPA;
   }
  
  private Cursor getGPASCursor() {
	    
	    // Specify the result column projection. Return the minimum set
	    // of columns required to satisfy your requirements.
	   
	    String[] result_columns = new String[] { 
	      KEY_ID,SEMESTER , SGPA}; 
	    
	    // Specify the where clause that will limit our results.
	    String where = SEMESTER + "=" + 1 ;
	    
	    // Replace these with valid SQL statements as necessary.
	    String whereArgs[] = null;
	    String groupBy = null;
	    String having = null;
	    String order = null;
	    
	    SQLiteDatabase db = mapDBOpenHelper.getWritableDatabase();
	    Cursor cursor = db.query(MapDBOpenHelper.DATABASE_TABLE,  result_columns, where,whereArgs, groupBy, having, order);
	    //
	    return cursor;
	  }
	  
	  public HashMap<Integer,Double> getGPAS() {
	    Cursor cursor = getGPASCursor();// get cursor
	    double tempSGPA;
	    int tempSemester;
	    HashMap<Integer, Double>SGPAs=new HashMap<Integer, Double>();
	    // Find the index to the column(s) being used.
	    int SEMESTER_INDEX=cursor.getColumnIndexOrThrow(SEMESTER);
	    int SGPA_INDEX =cursor.getColumnIndexOrThrow(SGPA);
	    
	    while (cursor.moveToNext()) {
	    	tempSemester=cursor.getInt(SEMESTER_INDEX);
	    	tempSGPA=cursor.getDouble(SGPA_INDEX);
	    	SGPAs.put(tempSemester, tempSGPA);
	      
	    }
	        
	    	return SGPAs;
	   }
	  
  public void addSGPA(int semester,double inputSGPA) {
   
    ContentValues newValues = new ContentValues();
		  newValues.put(SGPA,inputSGPA);
		  newValues.put(SEMESTER, semester);
    
  
		  // Insert the row into your table
		  SQLiteDatabase db = mapDBOpenHelper.getWritableDatabase();
		  db.insert(MapDBOpenHelper.DATABASE_TABLE, null, newValues); 
	  
  }
  
 
  
  
  
  private static class MapDBOpenHelper extends SQLiteOpenHelper {
    
    private static final String DATABASE_NAME = "GPADatabase4.db";
    private static final String DATABASE_TABLE = "GPAS";
    private static final int DATABASE_VERSION = 1;
    
    // SQL Statement to create a new database.
    private static final String DATABASE_CREATE = "create table " +
      DATABASE_TABLE + " (" + KEY_ID +
      " integer primary key autoincrement, " +
     SUGGEST_COLUMN_TEXT_1  + " text not null, " +
     SUGGEST_COLUMN_INTENT_DATA + " text not null, " +
     SGPA + " double, " + 
     SEMESTER + " integer);";

    public MapDBOpenHelper(Context context, String name,
                      CursorFactory factory, int version) {
      super(context, name, factory, version);
    }

    // Called when no database exists in disk and the helper class needs
    // to create a new one.
    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL(DATABASE_CREATE);
    }

    // Called when there is a database version mismatch meaning that
    // the version of the database on disk needs to be upgraded to
    // the current version.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, 
                          int newVersion) {
      // Log the version upgrade.
      Log.w("TaskDBAdapter", "Upgrading from version " +
        oldVersion + " to " +
        newVersion + ", which will destroy all old data");

      // Upgrade the existing database to conform to the new 
      // version. Multiple previous versions can be handled by 
      // comparing oldVersion and newVersion values.

      // The simplest case is to drop the old table and create a new one.
      db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
      // Create a new one.
      onCreate(db);
    }
  }








}