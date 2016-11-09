package com.example.clarinetmaster.learningassistant.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.clarinetmaster.learningassistant.Info.weekday;
import com.example.clarinetmaster.learningassistant.Model.Course;

public class dbHelper extends SQLiteOpenHelper{

    private static final String DATABASENAME = "MYDB";
    private static final int DATABASEVERSION = 1;

    private static final String TAG = "DatabaseHelper";

    public static final String TBLAPPOINTMENT = "APPOINTMENT";
    public static final String TBLCOURSE = "COURSE";
    public static final String TBLASSIGNMENT = "ASSIGNMENT";
    public static final String COLID = "_ID";

    public static final String COLAPPOINTLABEL = "APPOINTLABEL";
    public static final String COLAPPOINTLOCATION = "APPOINTLOCATION";
    public static final String COLAPPOINTDAY = "APPOINTDAY";
    public static final String COLAPPOINTSTART = "APPOINTSTART";
    public static final String COLAPPOINTEND = "APPOINTEND";
    public static final String COLAPPOINTDESC = "APPOINTDESC";

    public static final String COLCOURSENAME = "COURSENAME";
    public static final String COLCOURSEDESC = "COURSEDESC";
    public static final String COLCOURSEDAY = "COURSEDAY";
    public static final String COLCOURSESTART = "COURSESTART";
    public static final String COLCOURSEFINISH = "COURSEFINISH";
    public static final String COLTESTDAY = "TESTDAY";
    public static final String COLTESTSTART = "TESTSTART";
    public static final String COLTESTFINISH = "TESTFINISH";

    public static final String COLASSIGNMENTNAME = "ASSIGNMENTNAME";
    public static final String COLASSIGNMENTDESC = "ASSIGNMENTDESC";
    public static final String COLASSIGNMENTDEADLINE = "ASSIGNMENTDEADLINE";

    private static final String SQL_CREATE_TABLE_APPOINTMENT = "CREATE TABLE "+TBLAPPOINTMENT+" (" +
            COLID+" INTEGER PRIMARY KEY AUTOINCREMENT," + /**Constraint to create this field every table*/
            COLAPPOINTLABEL+" TEXT," +
            COLAPPOINTLOCATION+" TEXT," +
            COLAPPOINTDAY+" INTEGER,"+
            COLAPPOINTSTART+" TEXT," +
            COLAPPOINTEND+" TEXT," +
            COLAPPOINTDESC+" TEXT" +
            ")";

    private static final String SQL_CREATE_TABLE_COURSE = "CREATE TABLE "+TBLCOURSE+" (" +
            COLID+" INTEGER PRIMARY KEY AUTOINCREMENT," + /**Constraint to create this field every table*/
            COLCOURSENAME+" TEXT," +
            COLCOURSEDESC+" TEXT," +
            COLCOURSEDAY+" INTEGER,"+
            COLCOURSESTART+" TEXT," +
            COLCOURSEFINISH+" TEXT," +
            COLTESTDAY+" INTEGER," +
            COLTESTSTART +" TEXT," +
            COLTESTFINISH +" TEXT"+
            ")";

    private static final String SQL_CREATE_TABLE_ASSIGNMENT = "CREATE TABLE "+TBLASSIGNMENT+" (" +
            COLID+" INTEGER PRIMARY KEY AUTOINCREMENT," + /**Constraint to create this field every table*/
            COLASSIGNMENTNAME+" TEXT," +
            COLASSIGNMENTDESC+" TEXT," +
            COLASSIGNMENTDEADLINE+" INTEGER" +
            ")";

    public dbHelper(Context context){
        super(context, DATABASENAME, null, DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    private void createTables(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_TABLE_COURSE);
        //db.execSQL(SQL_CREATE_TABLE_ASSIGNMENT);
        //db.execSQL(SQL_CREATE_TABLE_APPOINTMENT);
        insertExampleCourse(db);
    }

    private void dropAllTables(SQLiteDatabase db){
        db.execSQL("DROP TABLE " + TBLCOURSE);
    }

    private void insertExampleCourse(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put(COLCOURSENAME, "Computer Programming I");
        cv.put(COLCOURSEDESC, "Programming Essentials");
        cv.put(COLCOURSEDAY, weekday.getDayIndexByDayCode(weekday.MONDAY()));
        cv.put(COLCOURSESTART, "12:00");
        cv.put(COLCOURSEFINISH, "15:00");
        db.insert(TBLCOURSE, null, cv);

        cv = new ContentValues();
        cv.put(COLCOURSENAME, "English I");
        cv.put(COLCOURSEDESC, "Just English");
        cv.put(COLCOURSEDAY, weekday.getDayIndexByDayCode(weekday.THURSDAY()));
        cv.put(COLCOURSESTART, "8:00");
        cv.put(COLCOURSEFINISH, "10:20");
        db.insert(TBLCOURSE, null, cv);

        cv = new ContentValues();
        cv.put(COLCOURSENAME, "Calculus I");
        cv.put(COLCOURSEDESC, "Calculus");
        cv.put(COLCOURSEDAY, weekday.getDayIndexByDayCode(weekday.FRIDAY()));
        cv.put(COLCOURSESTART, "9:00");
        cv.put(COLCOURSEFINISH, "12:05");
        db.insert(TBLCOURSE, null, cv);
    }

    public Course selectCourseById(SQLiteDatabase db, int id){
        Cursor c = db.query(TBLCOURSE, null, COLID+" = "+id, null, null, null, null);
        c.moveToFirst();
        return new Course(c.getString(c.getColumnIndex(COLCOURSENAME)),
                c.getInt(c.getColumnIndex(COLCOURSEDAY)),
                c.getString(c.getColumnIndex(COLCOURSESTART)),
                c.getString(c.getColumnIndex(COLCOURSEFINISH)),
                c.getInt(c.getColumnIndex(COLTESTDAY)),
                c.getString(c.getColumnIndex(COLCOURSESTART)),
                c.getString(c.getColumnIndex(COLCOURSEFINISH)),
                c.getString(c.getColumnIndex(COLCOURSEDESC))
                );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
