package com.example.clarinetmaster.learningassistant.Model;

import android.util.Log;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class myTime implements Serializable{

    private int hour;
    private int min;
    private Calendar calendar;

    public myTime(int hour, int min) {
        this.hour = hour;
        this.min = min;
    }

    public myTime(){
        calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String curTime = dateFormat.format(calendar.getTime());
        Log.i("myTime", curTime);
        stringTimeExtractor(curTime);
    }

    public myTime(String time){
        stringTimeExtractor(time);
    }

    private void stringTimeExtractor(String time){
        this.hour = Integer.parseInt(time.substring(0, time.indexOf(':')));
        this.min = Integer.parseInt(time.substring(time.indexOf(':')+1));
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return min;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int min) {
        this.min = min;
    }

    @Override
    public String toString() {
        String time;
        if(hour < 10) time = "0" + Integer.toString(hour);
        else time = Integer.toString(hour);
        time = time + ":";
        if(min < 10) time = time + "0" + Integer.toString(min);
        else time = time + Integer.toString(min);
        return  time;
    }
}
