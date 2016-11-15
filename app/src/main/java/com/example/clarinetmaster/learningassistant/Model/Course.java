package com.example.clarinetmaster.learningassistant.Model;

import android.content.res.Resources;

import com.example.clarinetmaster.learningassistant.Info.weekday;

import java.io.Serializable;

public class Course implements Serializable {

    private int id;
    private String courseName;
    private int courseDayIndex;
    private String learnStart;
    private String learnFinish;
    private String testDay;
    private String testStart;
    private String testFinish;
    private String courseDesc;

    public Course(int id, String courseName, int courseDayIndex, String learnStart, String learnFinish, String testDay, String testStart, String testFinish, String courseDesc) {
        this.id = id;
        this.courseName = courseName;
        this.courseDayIndex = courseDayIndex;
        this.learnStart = learnStart;
        this.learnFinish = learnFinish;
        this.testDay = testDay;
        this.testStart = testStart;
        this.testFinish = testFinish;
        this.courseDesc = courseDesc;
    }

    public Course(String courseName, int courseDayIndex, String learnStart, String learnFinish, String testDay, String testStart, String testFinish, String courseDesc) {
        this.courseName = courseName;
        this.courseDayIndex = courseDayIndex;
        this.learnStart = learnStart;
        this.learnFinish = learnFinish;
        this.testDay = testDay;
        this.testStart = testStart;
        this.testFinish = testFinish;
        this.courseDesc = courseDesc;
    }

    public Course(String courseName, int courseDayIndex, String learnStart, String learnFinish, String testDay, String testStart, String testFinish) {
        this.courseName = courseName;
        this.courseDayIndex = courseDayIndex;
        this.learnStart = learnStart;
        this.learnFinish = learnFinish;
        this.testDay = testDay;
        this.testStart = testStart;
        this.testFinish = testFinish;
        this.courseDesc = null;
    }

    public int getId(){
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCourseDayIndex() {
        return courseDayIndex;
    }

    public String getLearnStart() {
        return learnStart;
    }

    public String getLearnFinish() {
        return learnFinish;
    }

    public String getTestDay() {
        /*String result = testDay.substring(0, testDay.indexOf(' '));
        switch (result){
            case "January": result = resources.getString(R.string.jan);
            case "Febuary": result = resources.getString(R.string.feb);
            case "March": result = resources.getString(R.string.mar);
            case "Apirl": result = resources.getString(R.string.apr);
            case "May": result = resources.getString(R.string.may);
            case "June": result = resources.getString(R.string.jun);
            case "July": result = resources.getString(R.string.jul);
            case "August": result = resources.getString(R.string.aug);
            case "September": result = resources.getString(R.string.sep);
            case "October": result = resources.getString(R.string.oct);
            case "November": result = resources.getString(R.string.nov);
            case "December": result = resources.getString(R.string.dec);
            default: result = "E "+ result;
        }
        return result + testDay.substring(testDay.indexOf(' '));*/
        return testDay;
    }

    public String getTestStart() {
        return testStart;
    }

    public String getTestFinish() {
        return testFinish;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

}
