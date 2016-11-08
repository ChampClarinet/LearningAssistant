package com.example.clarinetmaster.learningassistant.Info;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.clarinetmaster.learningassistant.DB.dbHelper;

import java.util.ArrayList;

public class navBarContent {

    private Context context;
    private SQLiteDatabase db;
    private dbHelper mHelper;
    private ListView courseListView;
    private ArrayList<String> courseArrayList = new ArrayList<>();

    public navBarContent(Context context, ListView courseListView){
        this.courseListView = courseListView;
        this.context = context;
        mHelper = new dbHelper(context);
        db = mHelper.getWritableDatabase();
    }

    public void listNavBar(){

        courseArrayList.clear(); /**clear ArrayList Before commit reQuery to avoid dulpication*/

        Cursor cursor = db.query(dbHelper.TBLCOURSE, null, null, null, null, null, null); /**Cursor = Result set of Query*/

        while(cursor.moveToNext()){
            String course = cursor.getString(cursor.getColumnIndex(dbHelper.COLCOURSENAME));
            courseArrayList.add(course);
        }

        courseListView.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, courseArrayList));
    }

}
