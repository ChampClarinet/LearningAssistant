package com.example.clarinetmaster.learningassistant.Info;

import java.util.ArrayList;

public class Time {
    public static final ArrayList<String> HOUR(){
        ArrayList<String> s = new ArrayList<>();
        for(int i=0;i<10;++i) s.add("0"+Integer.toString(i));
        for(int i=10;i<24;++i) s.add(Integer.toString(i));
        return s;
    }
    public static final ArrayList<String> MINUTE(){
        ArrayList<String> s = new ArrayList<>();
        for(int i=0;i<10;++i) s.add("0"+Integer.toString(i));
        for(int i=10;i<60;++i) s.add(Integer.toString(i));
        return s;
    }

    public static String timeJoiner(String h, String m){
        return h + ":" + m;
    }

}
