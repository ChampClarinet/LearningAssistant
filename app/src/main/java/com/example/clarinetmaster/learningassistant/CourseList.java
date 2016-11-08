package com.example.clarinetmaster.learningassistant;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.clarinetmaster.learningassistant.Adapters.CourseAdapter;
import com.example.clarinetmaster.learningassistant.DB.dbHelper;
import com.example.clarinetmaster.learningassistant.Model.Course;

import java.util.ArrayList;

public class CourseList extends AppCompatActivity {

    private ListView listView;

    private dbHelper mHelper;
    private SQLiteDatabase db;
    private ArrayList<Course> courseArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_database);

        courseArrayList = new ArrayList<>();

        listView = (ListView) findViewById(R.id.courseListView);
        mHelper = new dbHelper(this);
        db = mHelper.getWritableDatabase();

        fecthingData();

    }

    private void fecthingData() {
        Cursor cursor = db.query(dbHelper.TBLCOURSE, null, null, null, null, null, dbHelper.COLCOURSENAME);
        courseArrayList.clear();
        while(cursor.moveToNext()){
            courseArrayList.add(new Course(cursor.getString(cursor.getColumnIndex(dbHelper.COLCOURSENAME)),
                    cursor.getString(cursor.getColumnIndex(dbHelper.COLCOURSEDAY)),
                    cursor.getString(cursor.getColumnIndex(dbHelper.COLCOURSESTART)),
                    cursor.getString(cursor.getColumnIndex(dbHelper.COLCOURSEEND)),
                    cursor.getString(cursor.getColumnIndex(dbHelper.COLTESTDAY)),
                    cursor.getString(cursor.getColumnIndex(dbHelper.COLTESTSTART)),
                    cursor.getString(cursor.getColumnIndex(dbHelper.COLTESTFINISH)),
                    cursor.getString(cursor.getColumnIndex(dbHelper.COLCOURSEDESC))
            ));
        }
        CourseAdapter adapter = new CourseAdapter(this, R.layout.course_list_item, courseArrayList);
        listView.setAdapter(adapter);
    }
}
