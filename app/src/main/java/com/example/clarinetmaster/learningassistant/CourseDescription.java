package com.example.clarinetmaster.learningassistant;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clarinetmaster.learningassistant.DB.dbHelper;
import com.example.clarinetmaster.learningassistant.Info.weekday;
import com.example.clarinetmaster.learningassistant.Model.Course;

public class CourseDescription extends AppCompatActivity {

    private static final String TAG = "CourseDescription";
    private Course course;
    private TextView learnTimeData;
    private TextView testTimeData;
    private TextView courseDesc;
    private TextView courseDescLabel;

    private dbHelper mHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_description);

        course = (Course) getIntent().getSerializableExtra("Course");

        getSupportActionBar().setTitle(course.getCourseName());

        mHelper = new dbHelper(this);
        db = mHelper.getWritableDatabase();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addassignmentfab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "fab triggered");
                addAssignment();
            }
        });

        learnTimeData = (TextView) findViewById(R.id.learntimedata);
        testTimeData = (TextView) findViewById(R.id.testtimedata);
        courseDesc = (TextView) findViewById(R.id.courseDescData);
        courseDescLabel = (TextView) findViewById(R.id.courseDescLabel);
        getLearnOnLabel();
        getTestOnLabel();
        courseDesc.setText(course.getCourseDesc());
        courseDescLabel.setText(getResources().getString(R.string.courseDesc)+" :");

    }

    private void addAssignment() {
        Intent intent = new Intent(this, addAssignment.class);
        intent.putExtra("assignOf", course.getId());
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Cursor cursor = db.query(dbHelper.TBLCOURSE, null, dbHelper.COLID+" = "+course.getId(), null, null, null, null);
        cursor.moveToNext();
        course = new Course(
                cursor.getInt(cursor.getColumnIndex(dbHelper.COLID)),
                cursor.getString(cursor.getColumnIndex(dbHelper.COLCOURSENAME)),
                cursor.getInt(cursor.getColumnIndex(dbHelper.COLCOURSEDAY)),
                cursor.getString(cursor.getColumnIndex(dbHelper.COLCOURSESTART)),
                cursor.getString(cursor.getColumnIndex(dbHelper.COLCOURSEFINISH)),
                cursor.getString(cursor.getColumnIndex(dbHelper.COLTESTDAY)),
                cursor.getString(cursor.getColumnIndex(dbHelper.COLTESTSTART)),
                cursor.getString(cursor.getColumnIndex(dbHelper.COLTESTFINISH)),
                cursor.getString(cursor.getColumnIndex(dbHelper.COLCOURSEDESC))
        );

        getTestOnLabel();
        getLearnOnLabel();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.course_desc_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.cd_edit){
            Intent intent = new Intent(this, EditCourseActivity.class);
            intent.putExtra("targetID", course.getId());
            startActivity(intent);
        }else if(id == R.id.cd_delete){
            deleteCourse();
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteCourse() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.confirm);
        //if(Locale.getDefault().getDisplayLanguage() == Locale.JAPAN.toString());
        dialog.setMessage(getResources().getString(R.string.delete_confirm) + " " + course.getCourseName());
        dialog.setNegativeButton(R.string.backButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setPositiveButton(R.string.yesButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.delete(dbHelper.TBLCOURSE, dbHelper.COLID + " = " + course.getId(), null);
                dialog.dismiss();
                deleted();
                finish();
            }
        });
        dialog.show();
    }

    private void deleted() {
        String msg = "\"" + course.getCourseName() + "\"" + " " + getResources().getString(R.string.has_been_deleted);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT);
    }

    private void getLearnOnLabel(){
        learnTimeData.setText(getResources().getString(weekday.getDayCodeByDayIndex(course.getCourseDayIndex()))+" "+
                course.getLearnStart()+" - "+course.getLearnFinish()
        );
    }

    private void getTestOnLabel(){
        testTimeData.setText(course.getTestDay());
    }

}
