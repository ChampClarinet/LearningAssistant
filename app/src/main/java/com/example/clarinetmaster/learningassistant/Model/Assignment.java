package com.example.clarinetmaster.learningassistant.Model;

import java.io.Serializable;

public class Assignment implements Serializable{

    private int id;
    private int courseID;
    private String name;
    private String deadlineDate;
    private myTime deadlineMyTime;
    private String description;

    public Assignment(int id, int courseID, String name, String deadlineDate, int deadlineHour, int deadlineMin, String description) {
        this.id = id;
        this.courseID = courseID;
        this.name = name;
        this.deadlineDate = deadlineDate;
        this.deadlineMyTime = new myTime(deadlineHour, deadlineMin);
        this.description = description;
    }

    public Assignment(int id, int courseID, String name, String deadlineDate, int deadlineHour, int deadlineMin) {
        this.id = id;
        this.courseID = courseID;
        this.name = name;
        this.deadlineDate = deadlineDate;
        this.deadlineMyTime = new myTime(deadlineHour, deadlineMin);
    }

    public Assignment(String name, int courseID, String deadlineDate, int deadlineHour, int deadlineMin, String description) {
        this.name = name;
        this.courseID = courseID;
        this.deadlineDate = deadlineDate;
        this.deadlineMyTime = new myTime(deadlineHour, deadlineMin);
        this.description = description;
    }

    public Assignment(String name, int courseID, String deadlineDate, int deadlineHour, int deadlineMin) {
        this.name = name;
        this.courseID = courseID;
        this.deadlineDate = deadlineDate;
        this.deadlineMyTime = new myTime(deadlineHour, deadlineMin);
    }

    public int getId() {
        return id;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getName() {
        return name;
    }

    public String getDeadlineDate() {
        return deadlineDate;
    }

    public myTime getDeadlineTime(){
        return deadlineMyTime;
    }

    public String getDescription() {
        return description;
    }
}

