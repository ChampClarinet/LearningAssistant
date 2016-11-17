package com.example.clarinetmaster.learningassistant.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.clarinetmaster.learningassistant.Info.weekday;
import com.example.clarinetmaster.learningassistant.Model.Course;
import com.example.clarinetmaster.learningassistant.R;

import java.util.ArrayList;

public class CourseAdapter extends ArrayAdapter<Course> {

    private Context context;
    private int resource;
    private ArrayList<Course> courseArrayList;

    public CourseAdapter(Context context, int resource, ArrayList<Course> courseArrayList){
        super(context, resource, courseArrayList);
        this.context = context;
        this.resource = resource;
        this.courseArrayList = courseArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemLayout = convertView;

        if (itemLayout == null) {
            itemLayout = View.inflate(context, resource, null);
        }

        TextView courseName = (TextView) itemLayout.findViewById(R.id.course);
        TextView courseDay = (TextView) itemLayout.findViewById(R.id.day);
        TextView courseTime = (TextView) itemLayout.findViewById(R.id.time);

        Course course = courseArrayList.get(position);

        String cName = course.getCourseName();
        courseName.setText(cName);

        int cDay = weekday.getDayCodeByDayIndex(course.getCourseDayIndex());
        courseDay.setText(cDay);

        String cTime = course.getLearnStart() + " - " + course.getLearnFinish();
        courseTime.setText(cTime);

        return itemLayout;
    }
}
