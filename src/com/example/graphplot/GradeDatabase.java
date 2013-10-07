package com.example.graphplot;


/**
 * Description of GradeDatabase
 * Represent the underlying grade database
 *
 * @author chamath sajeewa
 * chamaths.10@cse.mrt.ac.lk
 */

import java.util.Iterator;
import java.util.LinkedList;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

public class GradeDatabase extends ContentProvider{
  
  public static final String KEY_ID = "_id";
  public static final String SUGGEST_COLUMN_TEXT_1="suggest_text_1";
  public static final String SUGGEST_COLUMN_INTENT_DATA="suggest_intent_data";
  //The name and column index of each column in your database.
  //These should be descriptive.
  public static final String MODULE_CODE ="MODULE_CODE";
  public static final String GRADE ="GRADE";
  public static final String CREDIT ="CREDIT";
  public static final String SEMESTER ="SEMESTER";
  
  


  // Database open/upgrade helper
  private MapDBOpenHelper mapDBOpenHelper;
   public GradeDatabase() {
	
    }


  public GradeDatabase(Context context) {
    mapDBOpenHelper = new MapDBOpenHelper(context, MapDBOpenHelper.DATABASE_NAME, null, 
                                              MapDBOpenHelper.DATABASE_VERSION);
  }
  
  // Called when you no longer need access to the database.
  public void closeDatabase() {
    mapDBOpenHelper.close();
  }

  
  private Cursor getResultsCursor(int semester) {
    
    // Specify the result column projection. Return the minimum set
    // of columns required to satisfy your requirements.
    String[] result_columns = new String[] { 
      KEY_ID, MODULE_CODE,GRADE,CREDIT,SEMESTER }; 
    
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
  
  public Exam getResults(int semester) {
    Cursor cursor = getResultsCursor(semester);// get cursor
    Result result;
    LinkedList<Result>results=new LinkedList<Result>();
    Exam exam;
    // Find the index to the column(s) being used.
    int CODE_INDEX =cursor.getColumnIndexOrThrow(MODULE_CODE);
    int GRADE_INDEX =cursor.getColumnIndexOrThrow(GRADE);
    int CREDIT_INDEX =cursor.getColumnIndexOrThrow(CREDIT);
    int SEMESTER_INDEX =cursor.getColumnIndexOrThrow(SEMESTER);

    // Iterate over the cursors rows. 
    // The Cursor is initialized at before first, so we can 
    // check only if there is a "next" row available. If the
    // result Cursor is empty this will return false.
    while (cursor.moveToNext()) {
    	result=new Result(cursor.getString(CODE_INDEX), cursor.getDouble(CREDIT_INDEX), cursor.getString(GRADE_INDEX));
    	results.add(result);
      
    }

    exam=new Exam(semester, results.size(), results);
    
    return exam;
  }
  
  public void addResults(Exam exam) {
   
    // Create a new row of values to insert.
	  Iterator<Result>iter=exam.getResults().listIterator();
	  Result result;
	  while(iter.hasNext()){
		  ContentValues newValues = new ContentValues();
		 
		  
           result=iter.next();
		  // Assign values for each row.
           String grade=result.getGrade();
		  newValues.put(MODULE_CODE, result.getModuleCode());
		  newValues.put(SUGGEST_COLUMN_TEXT_1 , result.getModuleCode());
		  newValues.put(SUGGEST_COLUMN_INTENT_DATA ,result.getModuleCode());
		  newValues.put(GRADE,grade);
		  newValues.put(CREDIT,result.getCredit());
		  newValues.put(SEMESTER, exam.getSemester());
    
  
		  // Insert the row into your table
		  SQLiteDatabase db = mapDBOpenHelper.getWritableDatabase();
		  db.insert(MapDBOpenHelper.DATABASE_TABLE, null, newValues); 
	  }
  }
  
 
  
  
  /**
   * Listing 8-2: Implementing an SQLite Open Helper
   */
  private static class MapDBOpenHelper extends SQLiteOpenHelper {
    
    private static final String DATABASE_NAME = "GradeDatabase3.db";
    private static final String DATABASE_TABLE = "GRADES";
    private static final int DATABASE_VERSION = 1;
    
    // SQL Statement to create a new database.
    private static final String DATABASE_CREATE = "create table " +
      DATABASE_TABLE + " (" + KEY_ID +
      " integer primary key autoincrement, " +
     MODULE_CODE + " text not null, " +
     SUGGEST_COLUMN_TEXT_1  + " text not null, " +
     SUGGEST_COLUMN_INTENT_DATA + " text not null, " +
     GRADE + " text not null, " +
     CREDIT + " double, " + 
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


@Override
public int delete(Uri arg0, String arg1, String[] arg2) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public String getType(Uri arg0) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Uri insert(Uri arg0, ContentValues arg1) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public boolean onCreate() {
	
		mapDBOpenHelper = new MapDBOpenHelper(getContext(), MapDBOpenHelper.DATABASE_NAME, null, MapDBOpenHelper.DATABASE_VERSION);
		return true;
	

}

@Override
public Cursor query(Uri arg0, String[] arg1, String arg2, String[] arg3,
		String arg4) {

	Log.w("dsdsd","sdsdsdsddsds");
	 String[] result_columns = new String[] {  KEY_ID, SUGGEST_COLUMN_TEXT_1,SUGGEST_COLUMN_INTENT_DATA}; 
		 
		    // Specify the where clause that will limit our results.
	  String query = arg0.getLastPathSegment();
		    String where = SUGGEST_COLUMN_TEXT_1+ " Like "+"'%"+ query +"%'";
		    
		    // Replace these with valid SQL statements as necessary.
		    String whereArgs[] = null;
		    String groupBy = null;
		    String having = null;
		    String order = null;
		  
		    SQLiteDatabase db = mapDBOpenHelper.getWritableDatabase();
		    Cursor cursor = db.query(MapDBOpenHelper.DATABASE_TABLE,  result_columns, where,whereArgs, groupBy, having, order);
		   Cursor c=cursor;
		    return cursor;
	  
}

@Override
public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
	// TODO Auto-generated method stub
	return 0;
}



}