package com.example.clarinetmaster.learningassistant.Model;

public class Course {

    private int id;
    private String courseName;
    private int courseDay;
    private String learnStart;
    private String learnFinish;
    private int testDay;
    private String testStart;
    private String testFinish;
    private String courseDesc;

    public Course(int id, String courseName, int courseDay, String learnStart, String learnFinish, int testDay, String testStart, String testFinish, String courseDesc) {
        this.id = id;
        this.courseName = courseName;
        this.courseDay = courseDay;
        this.learnStart = learnStart;
        this.learnFinish = learnFinish;
        this.testDay = testDay;
        this.testStart = testStart;
        this.testFinish = testFinish;
        this.courseDesc = courseDesc;
    }

    public Course(String courseName, int courseDay, String learnStart, String learnFinish, int testDay, String testStart, String testFinish, String courseDesc) {
        this.courseName = courseName;
        this.courseDay = courseDay;
        this.learnStart = learnStart;
        this.learnFinish = learnFinish;
        this.testDay = testDay;
        this.testStart = testStart;
        this.testFinish = testFinish;
        this.courseDesc = courseDesc;
    }

    public Course(String courseName, int courseDay, String learnStart, String learnFinish, int testDay, String testStart, String testFinish) {
        this.courseName = courseName;
        this.courseDay = courseDay;
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
        return courseDay;
    }

    public String getLearnStart() {
        return learnStart;
    }

    public String getLearnFinish() {
        return learnFinish;
    }

    public int getTestDayIndex() {
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
