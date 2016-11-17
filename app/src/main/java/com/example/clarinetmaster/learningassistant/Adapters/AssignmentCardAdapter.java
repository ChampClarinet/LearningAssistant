package com.example.clarinetmaster.learningassistant.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.clarinetmaster.learningassistant.Model.Assignment;
import com.example.clarinetmaster.learningassistant.R;

import java.util.ArrayList;

public class AssignmentCardAdapter extends RecyclerView.Adapter<AssignmentCardAdapter.GenericHolder> {

    private Context context;
    private ArrayList<Assignment> assignment;

    public AssignmentCardAdapter(Context context, ArrayList<Assignment> assignment) {
        this.context = context;
        this.assignment = assignment;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public GenericHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout, parent, false);
        ViewHolder holder = new ViewHolder(v, assignment, context);
        return holder;
    }

    @Override
    public void onBindViewHolder(GenericHolder holder, int position) {
        holder.setViewData(assignment.get(position));
        //holder.cardView.setCardBackgroundColor(getColorByDate(position));
    }

    public int getColorByDate(int position){
        /*
        Calendar calendar = Calendar.getInstance();
        if(assignment.get(position) < 30) return Color.RED;
        else if(assignment.get(position).getScore() < 50) return Color.YELLOW;
        else return Color.GREEN;
        */
        return 0;
    }

    @Override
    public int getItemCount() {
        return assignment.size(); // Recycler view ends at return value
    }

    public abstract static class GenericHolder extends RecyclerView.ViewHolder{

        public CardView cardView = (CardView) itemView.findViewById(R.id.cv);

        public GenericHolder(View itemView){
            super(itemView);
        }

        abstract public void setViewData(Assignment assignment);

    }

    public static class ViewHolder extends GenericHolder /*implements View.OnClickListener*/{

        private final ArrayList<Assignment> assignment;

        private final TextView nameTV;
        private final TextView dateTV;
        private final TextView timeTV;
        private final TextView descTV;
        private final Context context;

        public ViewHolder(final View itemView, ArrayList<Assignment> assignment, final Context context){
            super(itemView);
            this.assignment = assignment;
            this.context = context;
            nameTV = (TextView) itemView.findViewById(R.id.assignment_name);
            dateTV = (TextView) itemView.findViewById(R.id.assignment_date);
            timeTV = (TextView) itemView.findViewById(R.id.assignment_time);
            descTV = (TextView) itemView.findViewById(R.id.assignment_desc);

        }
/*
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if(v.getId() == R.id.person_photo){
                Log.i("selection", cardposition]);
            }
        }
        */

        @Override
        public void setViewData(Assignment assignment) {
            nameTV.setText(assignment.getName());
            dateTV.setText(assignment.getDeadlineDate());
            timeTV.setText(assignment.getDeadlineTime().toString());
            descTV.setText(assignment.getDescription());
        }

    }

}

