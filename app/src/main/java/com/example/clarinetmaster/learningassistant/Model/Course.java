package com.example.clarinetmaster.learningassistant.Model;

import android.content.res.Resources;

import com.example.clarinetmaster.learningassistant.Info.weekday;

import java.io.Serializable;

public class Course implements Serializable{

    private int id;
    private String courseName;
    private int courseDayIndex;
    private String learnStart;
    private String learnFinish;
    private int testDayIndex;
    private String testStart;
    private String testFinish;
    private String courseDesc;

    public Course(int id, String courseName, int courseDayIndex, String learnStart, String learnFinish, int testDayIndex, String testStart, String testFinish, String courseDesc) {
        this.id = id;
        this.courseName = courseName;
        this.courseDayIndex = courseDayIndex;
        this.learnStart = learnStart;
        this.learnFinish = learnFinish;
        this.testDayIndex = testDayIndex;
        this.testStart = testStart;
        this.testFinish = testFinish;
        this.courseDesc = courseDesc;
    }

    public Course(String courseName, int courseDayIndex, String learnStart, String learnFinish, int testDayIndex, String testStart, String testFinish, String courseDesc) {
        this.courseName = courseName;
        this.courseDayIndex = courseDayIndex;
        this.learnStart = learnStart;
        this.learnFinish = learnFinish;
        this.testDayIndex = testDayIndex;
        this.testStart = testStart;
        this.testFinish = testFinish;
        this.courseDesc = courseDesc;
    }

    public Course(String courseName, int courseDayIndex, String learnStart, String learnFinish, int testDayIndex, String testStart, String testFinish) {
        this.courseName = courseName;
        this.courseDayIndex = courseDayIndex;
        this.learnStart = learnStart;
        this.learnFinish = learnFinish;
        this.testDayIndex = testDayIndex;
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

    public int getTestDayIndex() {
        return testDayIndex;
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
