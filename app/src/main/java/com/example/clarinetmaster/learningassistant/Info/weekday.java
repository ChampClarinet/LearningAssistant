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

    public static int getDayIndexByDayCode(int id){
        switch (id){
            case R.string.sun: return 0;
            case R.string.mon: return 1;
            case R.string.tue: return 2;
            case R.string.wed: return 3;
            case R.string.thu: return 4;
            case R.string.fri: return 5;
            case R.string.sat: return 6;
            default:return -1;
        }
    }

    public static int getDayCodeByDayIndex(int dayCode){
        switch (dayCode){
            case 0: return R.string.sun;
            case 1: return R.string.mon;
            case 2: return R.string.tue;
            case 3: return R.string.wed;
            case 4: return R.string.thu;
            case 5: return R.string.fri;
            case 6: return R.string.sat;
            default:return -1;
        }
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
