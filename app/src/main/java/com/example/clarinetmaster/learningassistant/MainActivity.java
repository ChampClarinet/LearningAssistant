package com.example.clarinetmaster.learningassistant;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.clarinetmaster.learningassistant.DB.dbHelper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SQLiteDatabase db;
    private dbHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getResources().getString(R.string.feed));

        mHelper = new dbHelper(this);
        db = mHelper.getWritableDatabase();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        testAssignment(db);
    }

    private void testAssignment(SQLiteDatabase db){
        Cursor cursor = db.query(dbHelper.TBLASSIGNMENT, null, null, null, null, null, null);
        cursor.moveToNext();
        Log.i(dbHelper.COLASSIGNMENTNAME, cursor.getString(cursor.getColumnIndex(dbHelper.COLASSIGNMENTNAME)));
        Log.i(dbHelper.COLASSIGNMENTDESC, cursor.getString(cursor.getColumnIndex(dbHelper.COLASSIGNMENTDESC)));
        Log.i(dbHelper.COLASSIGNMENTDEADLINE, cursor.getString(cursor.getColumnIndex(dbHelper.COLASSIGNMENTDEADLINE)));
        String getAssociateCourseSQL = dbHelper.COLID+" = "+cursor.getInt(cursor.getColumnIndex(dbHelper.COLASSIGNMENTOF));
        Cursor cursor2 = db.query(dbHelper.TBLCOURSE, null, getAssociateCourseSQL, null, null, null, null);
        Log.i("SQLQUERY", getAssociateCourseSQL);
        cursor2.moveToNext();
        Log.i(dbHelper.COLASSIGNMENTOF, ""+cursor.getInt(cursor.getColumnIndex(dbHelper.COLASSIGNMENTOF)));
        Log.i(dbHelper.COLCOURSENAME, cursor2.getString(cursor2.getColumnIndex(dbHelper.COLCOURSENAME)));
    }

    @Override
    protected void onResume() {
        super.onResume();

        //new navBarContent(this, (ListView) findViewById(R.id.courseListView)).listNavBar();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.scheduleNavBar) startActivity(new Intent(this, Schedule.class));
        if(id == R.id.courseNavBar) startActivity(new Intent(this, CourseList.class));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
