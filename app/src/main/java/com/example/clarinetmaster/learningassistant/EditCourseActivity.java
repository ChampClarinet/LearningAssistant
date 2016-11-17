package com.example.clarinetmaster.learningassistant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clarinetmaster.learningassistant.DB.dbHelper;
import com.example.clarinetmaster.learningassistant.Info.errorAlert;
import com.example.clarinetmaster.learningassistant.Info.weekday;
import com.example.clarinetmaster.learningassistant.Model.Course;
import com.example.clarinetmaster.learningassistant.Model.myTime;
import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.sleepbot.datetimepicker.time.RadialPickerLayout;
import com.sleepbot.datetimepicker.time.TimePickerDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EditCourseActivity extends AppCompatActivity {

    private static final String TAG = "EDIT_COURSE_ACTIVITY";

    private ContentValues cv;

    private EditText newCourseName;
    private Spinner learnDaySpinner;
    private TextView testDayView;
    private TextView startTime;
    private TextView finishTime;
    private TextView testStart;
    private TextView testFinish;
    private EditText courseDesc;

    private Calendar mCalendar;

    private int courseDay;
    private myTime courseStart;
    private myTime courseFinish;
    private myTime testStartTime;
    private myTime testFinishTime;

    private dbHelper mHelper;
    private SQLiteDatabase db;
    private Bundle extras;
    private int targetID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_form);

        mHelper = new dbHelper(this);
        db = mHelper.getWritableDatabase();

        extras = getIntent().getExtras();

        inherits();
        setAdapter();
        setListener();
        getOldData();

    }

    private void getOldData(){

        targetID = extras.getInt("targetID");

        Course oldCourse = mHelper.selectCourseById(db, targetID);

        newCourseName.setText(oldCourse.getCourseName());

        learnDaySpinner.setSelection(oldCourse.getCourseDayIndex());
        testDayView.setText(oldCourse.getTestDay());

        startTime.setText(oldCourse.getLearnStart());
        courseStart = new myTime(oldCourse.getLearnStart());
        finishTime.setText(oldCourse.getLearnFinish());
        courseFinish = new myTime(oldCourse.getLearnFinish());

        testStart.setText(oldCourse.getTestStart());
        testStartTime = new myTime(oldCourse.getTestStart());
        testFinish.setText(oldCourse.getTestFinish());
        testFinishTime = new myTime(oldCourse.getTestFinish());

        if(oldCourse.getCourseDesc() != null) courseDesc.setText(oldCourse.getCourseDesc());

    }

    private void inherits() {
        mCalendar = Calendar.getInstance();

        newCourseName = (EditText) findViewById(R.id.newCourseName);

        learnDaySpinner = (Spinner) findViewById(R.id.learnDaySpinner);
        testDayView = (TextView) findViewById(R.id.testDay);

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
        Date date = mCalendar.getTime();
        String textDate = dateFormat.format(date);
        testDayView.setText(textDate);

        startTime = (TextView) findViewById(R.id.start_time);
        finishTime = (TextView) findViewById(R.id.finish_time);

        testStart = (TextView) findViewById(R.id.test_start);
        testFinish = (TextView) findViewById(R.id.test_finish);

        courseDesc = (EditText) findViewById(R.id.courseDesc);

    }

    private void setListener() {

        findViewById(R.id.activity_add_course).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return true;
            }
        });

        newCourseName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });

        courseDesc.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService((Activity.INPUT_METHOD_SERVICE));
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });

        learnDaySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                courseDay = position;
                Log.i("courseDay", "" + courseDay);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePicker = TimePickerDialog.newInstance(
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(RadialPickerLayout radialPickerLayout, int hourOfDay, int minute) {
                                courseStart.setHour(hourOfDay);
                                courseStart.setMinute(minute);
                                startTime.setText(courseStart.toString());
                            }
                        }, courseStart.getHour(), courseStart.getMinute(), true, false
                );
                timePicker.show(getSupportFragmentManager(), "timePicker");
            }
        });

        finishTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePicker = TimePickerDialog.newInstance(
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
                                courseFinish.setHour(hourOfDay);
                                courseFinish.setMinute(minute);
                                finishTime.setText(courseFinish.toString());
                            }
                        }, courseFinish.getHour(), courseFinish.getMinute(), true, false
                );
                timePicker.show(getSupportFragmentManager(), "timePicker");
            }
        });

        testDayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePicker = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
                                DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
                                mCalendar.set(year, month, day);
                                Date date = mCalendar.getTime();
                                String textDate = dateFormat.format(date);
                                if(validDate(textDate)) testDayView.setText(textDate);
                            }
                        },
                        mCalendar.get(Calendar.YEAR),
                        mCalendar.get(Calendar.MONTH),
                        mCalendar.get(Calendar.DAY_OF_MONTH),
                        false
                );
                datePicker.setYearRange(1903, 2036);
                datePicker.show(getSupportFragmentManager(), "datePicker");
            }
        });

        testStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePicker = TimePickerDialog.newInstance(
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
                                testStartTime.setHour(hourOfDay);
                                testStartTime.setMinute(minute);
                                testStart.setText(testStartTime.toString());
                            }
                        }, testStartTime.getHour(), testStartTime.getMinute(), true, false
                );
                timePicker.show(getSupportFragmentManager(), "timePicker");
            }
        });

        testFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePicker = TimePickerDialog.newInstance(
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
                                testFinishTime.setHour(hourOfDay);
                                testFinishTime.setMinute(minute);
                                testFinish.setText(testFinishTime.toString());
                            }
                        }, testFinishTime.getHour(), testFinishTime.getMinute(), true, false
                );
                timePicker.show(getSupportFragmentManager(), "timePicker");
            }
        });

    }

    private void setAdapter() {
        ArrayAdapter<String> adapterD = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, day());

        learnDaySpinner.setAdapter(adapterD);
        learnDaySpinner.setSelection(0);

    }

    private void update() {
        db.update(dbHelper.TBLCOURSE, cv, dbHelper.COLID+" = "+targetID, null);
        Log.i(TAG, "updated");
        String msg = "\""+cv.getAsString(dbHelper.COLCOURSENAME)+"\""+" "+getResources().getString(R.string.updated);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.form_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save) {
            Log.i(TAG, "save menu triggered");
            if (encapData()) confirmation();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean encapData() {
        Log.i(TAG, "encapsulating");
        String courseName = newCourseName.getText().toString();
        Log.i("courseNameLength", Integer.toString(courseName.length()));
        if (courseName.length() == 0) {
            new errorAlert(this, getResources().getString(R.string.err_course_name)).alert();
            return false;
        }
        if(!checkTime(courseStart, courseFinish)) return false;
        if(!checkTime(testStartTime, testFinishTime)) return  false;

        String courseDescS = courseDesc.getText().toString();
        if (courseDescS.length() == 0) courseDescS = null;
        String learnS = courseStart.toString();
        String learnF = courseFinish.toString();
        String testS = testStartTime.toString();
        String testF = testFinishTime.toString();
        String testDay = testDayView.getText().toString();

        Log.i(TAG, courseName + "\n" +
                courseDay + "\n" +
                learnS + " " + learnF + "\n" +
                testDay + "\n" +
                testS + " " + testF + "\n" +
                courseDescS
        );

        cv = new ContentValues();
        cv.put(dbHelper.COLCOURSENAME, courseName);
        cv.put(dbHelper.COLCOURSEDAY, courseDay);
        cv.put(dbHelper.COLCOURSESTART, learnS);
        cv.put(dbHelper.COLCOURSEFINISH, learnF);
        cv.put(dbHelper.COLTESTDAY, testDay);
        cv.put(dbHelper.COLTESTSTART, testS);
        cv.put(dbHelper.COLTESTFINISH, testF);
        cv.put(dbHelper.COLCOURSEDESC, courseDescS);

        return true;
    }

    private boolean checkTime(myTime timeStart, myTime timeFinish) {
        if(timeStart.getHour() > timeFinish.getHour() || timeStart.getMinute() > timeFinish.getMinute()){
            new errorAlert(this, getResources().getString(R.string.err_time)).alert();
            return false;
        }
        return true;
    }

    private void confirmation() {
        Log.i(TAG, "confirmMethod");
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.confirm);
        dialog.setMessage(getResources().getString(R.string.conMessage) + "\n" + printData());
        dialog.setPositiveButton(R.string.yesButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG, "PositiveDialogButton triggered");
                update();
                dialog.dismiss();
            }
        });
        dialog.setNegativeButton(R.string.backButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG, "NegativeDialogButton triggered");
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private String printData() {
        String s = "Adding course :" + cv.getAsString(dbHelper.COLCOURSENAME) + "\n" +
                "learn on " + getResources().getString(weekday.getDayCodeByDayIndex(cv.getAsInteger(dbHelper.COLCOURSEDAY))) + " " + cv.getAsString(dbHelper.COLCOURSESTART) + " - " + cv.getAsString(dbHelper.COLCOURSEFINISH) + "\n" +
                "test on " + cv.getAsString(dbHelper.COLTESTDAY) + " " + cv.getAsString(dbHelper.COLTESTSTART) + " - " + cv.getAsString(dbHelper.COLTESTFINISH) + "\n";
        String desc = cv.getAsString(dbHelper.COLCOURSEDESC);
        if (desc == null) s += "Description : <not set>";
        else s += "Description : " + desc;
        return s;
    }

    private ArrayList<String> day() {
        ArrayList<String> s = new ArrayList<>();
        for (int x : weekday.getArrayWeekDay()) s.add(getResources().getString(x));
        return s;
    }

    private boolean validDate(String textDate) {
        errorAlert err = new errorAlert(this, getResources().getString(R.string.err_date));
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String curDate = dateformat.format(c.getTime());
        int year = Integer.parseInt(textDate.substring(textDate.indexOf(',')+2));
        Log.i("valid year", year+" "+Integer.parseInt(curDate.substring(0, 4)));
        if(year < Integer.parseInt(curDate.substring(0, 4))){
            err.alert();
            return false;
        }
        String month = textDate.substring(0, textDate.indexOf(' '));
        int m;
        switch (month){
            case "January": m=1;
                break;
            case "February": m=2;
                break;
            case "March": m=3;
                break;
            case "Apirl": m=4;
                break;
            case "May": m=5;
                break;
            case "June": m=6;
                break;
            case "July": m=7;
                break;
            case "August": m=8;
                break;
            case "September": m=9;
                break;
            case "October": m=10;
                break;
            case "November": m=11;
                break;
            case "December": m=12;
                break;
            default: m=-1;
        }
        Log.i("valid month", m+" "+Integer.parseInt(curDate.substring(5, 7)));
        if(m < Integer.parseInt(curDate.substring(5, 7))){
            err.alert();
            return false;
        }
        String d = textDate.substring(textDate.indexOf(' '), textDate.indexOf(','));
        int day = -1;
        for(int i=0;i<d.length();++i){
            if(d.charAt(i)!= ' ') {
                day = Integer.parseInt(d.substring(i));
                break;
            }
        }
        Log.i("valid day", day+" "+Integer.parseInt(curDate.substring(8)));
        if(day < Integer.parseInt(curDate.substring(8))){
            err.alert();
            return false;
        }
        return true;
    }

}
