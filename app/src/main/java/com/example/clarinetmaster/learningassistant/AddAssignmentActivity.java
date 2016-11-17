package com.example.clarinetmaster.learningassistant;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clarinetmaster.learningassistant.DB.dbHelper;
import com.example.clarinetmaster.learningassistant.Info.errorAlert;
import com.example.clarinetmaster.learningassistant.Model.myTime;
import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.sleepbot.datetimepicker.time.RadialPickerLayout;
import com.sleepbot.datetimepicker.time.TimePickerDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddAssignmentActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    private TextView newAssignmentName;
    private TextView deadlineDate;
    private TextView deadlineTime;
    private TextView description;

    private ContentValues cv;
    private int courseID;

    private dbHelper helper;
    private SQLiteDatabase db;

    private Calendar calendar;
    private DateFormat dateFormat;
    private Date date;
    private myTime time;

    private String TAG = "AddAsignmentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignment_form);

        Bundle extras = getIntent().getExtras();

        courseID = extras.getInt("courseID");
        Log.i("courseID", courseID+"");

        inherits();
        setListener();

    }

    private void setListener() {

        helper = new dbHelper(this);
        db = helper.getWritableDatabase();

        findViewById(R.id.assignment_form).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return true;
            }
        });
        newAssignmentName.setOnFocusChangeListener(this);
        description.setOnFocusChangeListener(this);

        deadlineDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePicker = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
                                calendar.set(year, month, day);
                                String textDate = dateFormat.format(date);
                                Log.i(TAG, textDate);
                                if (validDate(textDate)){
                                    deadlineDate.setText(textDate);
                                }
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH),
                        false
                );
                datePicker.setYearRange(1903, 2036);
                datePicker.show(getSupportFragmentManager(), "datePicker");
            }
        });
        deadlineTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myTime curTime = new myTime();
                TimePickerDialog timePicker = TimePickerDialog.newInstance(
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
                                String textTime = new myTime(hourOfDay, minute).toString();
                                Log.i(TAG, textTime);
                                deadlineTime.setText(textTime);
                                time = new myTime(hourOfDay, minute);
                            }
                        },
                        curTime.getHour() ,
                        curTime.getMinute(),
                        true,
                        false
                );
                timePicker.show(getSupportFragmentManager(), "timePicker");
            }
        });
    }

    private void inherits() {
        newAssignmentName = (TextView) findViewById(R.id.newAssignmentName);
        deadlineDate = (TextView) findViewById(R.id.submitDate);
        deadlineTime = (TextView) findViewById(R.id.submitTime);
        description = (TextView) findViewById(R.id.description);

        calendar = Calendar.getInstance();
        dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
        date = calendar.getTime();
        deadlineDate.setText(dateFormat.format(date));
        time = new myTime();
        deadlineTime.setText(time.toString());
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
            if(checkData()){
                pushData();
                Toast.makeText(this, newAssignmentName.getText().toString()+" "+R.string.added, Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean checkData() {
        if(newAssignmentName.getText().toString().length() > 0) return true;
        new errorAlert(this, "Please type the assignment name.").alert();
        return false;
    }

    private void pushData() {
        String name = newAssignmentName.getText().toString();
        String date = deadlineDate.getText().toString();
        String desc = description.getText().toString();

        cv = new ContentValues();
        cv.put(dbHelper.COLASSIGNMENTNAME, name);
        cv.put(dbHelper.COLASSIGNMENTOF, courseID);
        cv.put(dbHelper.COLASSIGNMENTDEADLINEDATE, date);
        cv.put(dbHelper.COLASSIGNMENTDEADLINEHOUR, time.getHour());
        cv.put(dbHelper.COLASSIGNMENTDEADLINEMIN, time.getMinute());
        if(desc.length() > 0) cv.put(dbHelper.COLASSIGNMENTDESC, desc);
        Log.i("Values",
                cv.getAsString(dbHelper.COLASSIGNMENTNAME)+"\n"+
                        cv.getAsString(dbHelper.COLASSIGNMENTOF)+"\n"+
                        cv.getAsString(dbHelper.COLASSIGNMENTDEADLINEDATE)+"\n"+
                        cv.getAsString(dbHelper.COLASSIGNMENTDEADLINEHOUR)+"\n"+
                        cv.getAsString(dbHelper.COLASSIGNMENTDEADLINEMIN)+"\n"+
                        cv.getAsString(dbHelper.COLASSIGNMENTDESC)
        );
        db.insert(dbHelper.TBLASSIGNMENT, null, cv);
        Log.i(TAG, "inserted");
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    private boolean validDate(String textDate) {
        errorAlert err = new errorAlert(this, getResources().getString(R.string.err_date));
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String curDate = dateformat.format(c.getTime());
        int year = Integer.parseInt(textDate.substring(textDate.indexOf(',') + 2));
        Log.i("valid year", year + " " + Integer.parseInt(curDate.substring(0, 4)));
        if (year < Integer.parseInt(curDate.substring(0, 4))) {
            err.alert();
            return false;
        }
        String month = textDate.substring(0, textDate.indexOf(' '));
        int m;
        switch (month) {
            case "January":
                m = 1;
                break;
            case "February":
                m = 2;
                break;
            case "March":
                m = 3;
                break;
            case "Apirl":
                m = 4;
                break;
            case "May":
                m = 5;
                break;
            case "June":
                m = 6;
                break;
            case "July":
                m = 7;
                break;
            case "August":
                m = 8;
                break;
            case "September":
                m = 9;
                break;
            case "October":
                m = 10;
                break;
            case "November":
                m = 11;
                break;
            case "December":
                m = 12;
                break;
            default:
                m = -1;
        }
        Log.i("valid month", m + " " + Integer.parseInt(curDate.substring(5, 7)));
        if (m < Integer.parseInt(curDate.substring(5, 7))) {
            err.alert();
            return false;
        }
        String d = textDate.substring(textDate.indexOf(' '), textDate.indexOf(','));
        int day = -1;
        for (int i = 0; i < d.length(); ++i) {
            if (d.charAt(i) != ' ') {
                day = Integer.parseInt(d.substring(i));
                break;
            }
        }
        Log.i("valid day", day + " " + Integer.parseInt(curDate.substring(8)));
        if (day < Integer.parseInt(curDate.substring(8))) {
            err.alert();
            return false;
        }
        return true;
    }

}
