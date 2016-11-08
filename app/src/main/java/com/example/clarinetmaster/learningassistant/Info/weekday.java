package com.example.clarinetmaster.learningassistant.Info;

import com.example.clarinetmaster.learningassistant.R;

public class weekday {

    public static final int[] getArrayWeekDay(){
        int s[] =  {
                SUNDAY(),
                MONDAY(),
                TUESDAY(),
                WEDNESDAY(),
                THURSDAY(),
                FRIDAY(),
                SATURDAY()
        };
        return s;
    }

    public static final int MONDAY(){
        return R.string.mon;
    }

    public static final int TUESDAY(){
        return R.string.tue;
    }

    public static final int WEDNESDAY(){
        return R.string.wed;
    }

    public static final int THURSDAY(){
        return R.string.thu;
    }

    public static final int FRIDAY(){
        return R.string.fri;
    }

    public static final int SATURDAY(){
        return R.string.sat;
    }

    public static final int SUNDAY(){
        return R.string.sun;
    }

}
