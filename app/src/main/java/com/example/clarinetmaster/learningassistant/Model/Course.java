package com.example.clarinetmaster.learningassistant.Model;

public class Course {

    private String courseName;
    private String courseDay;
    private String learnStart;
    private String learnFinish;
    private String testDay;
    private String testStart;
    private String testFinish;
    private String courseDesc;

    public Course(String courseName, String courseDay, String learnStart, String learnFinish, String testDay, String testStart, String testFinish, String courseDesc) {
        this.courseName = courseName;
        this.courseDay = courseDay;
        this.learnStart = learnStart;
        this.learnFinish = learnFinish;
        this.testDay = testDay;
        this.testStart = testStart;
        this.testFinish = testFinish;
        this.courseDesc = courseDesc;
    }

    public Course(String courseName, String courseDay, String learnStart, String learnFinish, String testDay, String testStart, String testFinish) {
        this.courseName = courseName;
        this.courseDay = courseDay;
        this.learnStart = learnStart;
        this.learnFinish = learnFinish;
        this.testDay = testDay;
        this.testStart = testStart;
        this.testFinish = testFinish;
        this.courseDesc = null;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseDay() {
        return courseDay;
    }

    public String getLearnStart() {
        return learnStart;
    }

    public String getLearnFinish() {
        return learnFinish;
    }

    public String getTestDay() {
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
