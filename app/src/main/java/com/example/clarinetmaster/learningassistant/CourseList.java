package com.example.clarinetmaster.learningassistant;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.clarinetmaster.learningassistant.Adapters.CourseAdapter;
import com.example.clarinetmaster.learningassistant.DB.dbHelper;
import com.example.clarinetmaster.learningassistant.Model.Course;

import java.util.ArrayList;

public class CourseList extends AppCompatActivity {

    private ListView listView;
    private String TAG = "COURSE_LIST";

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

        registerForContextMenu(listView);

    }

    @Override
    protected void onResume() {
        super.onResume();

        fecthingData();

    }

    private void fecthingData() {
        Cursor cursor = db.query(dbHelper.TBLCOURSE, null, null, null, null, null, dbHelper.COLCOURSENAME);
        courseArrayList.clear();
        while(cursor.moveToNext()){
            courseArrayList.add(new Course(cursor.getInt(cursor.getColumnIndex(dbHelper.COLID)),
                    cursor.getString(cursor.getColumnIndex(dbHelper.COLCOURSENAME)),
                    cursor.getInt(cursor.getColumnIndex(dbHelper.COLCOURSEDAY)),
                    cursor.getString(cursor.getColumnIndex(dbHelper.COLCOURSESTART)),
                    cursor.getString(cursor.getColumnIndex(dbHelper.COLCOURSEFINISH)),
                    cursor.getInt(cursor.getColumnIndex(dbHelper.COLTESTDAY)),
                    cursor.getString(cursor.getColumnIndex(dbHelper.COLTESTSTART)),
                    cursor.getString(cursor.getColumnIndex(dbHelper.COLTESTFINISH)),
                    cursor.getString(cursor.getColumnIndex(dbHelper.COLCOURSEDESC))
            ));
        }
        CourseAdapter adapter = new CourseAdapter(this, R.layout.course_list_item, courseArrayList);
        listView.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if(v.getId() == R.id.courseListView){
            getMenuInflater().inflate(R.menu.edit_delete_course_menu, menu);
        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int courseID = courseArrayList.get(info.position).getId();
        switch (item.getItemId()){
            case R.id.edit_course_menu: Intent i = new Intent(this, EditCourseActivity.class);
                i.putExtra("targetID", courseID);
                startActivity(i);
                break;
            case R.id.delete_course_menu: deleteCourse(courseID);
            default: return false;
        }
        return false;
    }

    private void deleteCourse(final int id) {
        final String cname = mHelper.selectCourseById(db, id).getCourseName();
        Log.i(TAG, cname);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.confirm);
        //if(Locale.getDefault().getDisplayLanguage() == Locale.JAPAN.toString());
        dialog.setMessage(getResources().getString(R.string.delete_confirm)+" "+cname);
        dialog.setNegativeButton(R.string.backButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setPositiveButton(R.string.yesButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.delete(dbHelper.TBLCOURSE, dbHelper.COLID+" = "+id, null);
                dialog.dismiss();
                Intent i = getIntent();
                deleted(cname);
                finish();
                startActivity(i);
            }
        });
        dialog.show();
    }

    private void deleted(String name){
        String msg = "\""+name+"\""+" "+getResources().getString(R.string.has_been_deleted);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT);
    }

}
