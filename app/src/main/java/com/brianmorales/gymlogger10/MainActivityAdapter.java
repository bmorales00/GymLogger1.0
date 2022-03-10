package com.brianmorales.gymlogger10;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.brianmorales.gymlogger10.models.GymLog;

import java.util.ArrayList;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.ViewHolder> {

    ArrayList<GymLog> loggerList;
    Context context;

    @SuppressLint("NotifyDataSetChanged")
    public MainActivityAdapter(Activity context, ArrayList<GymLog> loggerList) {
        this.loggerList = loggerList;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_logging_card, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.tvMainActivityTitle.setText(loggerList.get(i).getWorkoutTitle());
        if(loggerList.get(i).getSets().isEmpty()){
            viewHolder.tvMainActivityInfo.setText(loggerList.get(i).getSetsDescription());
        }
        else{
            viewHolder.tvMainActivityInfo.setText(loggerList.get(i).getSetsToString());
        }

    }

    @Override
    public int getItemCount() {
        return loggerList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

            TextView tvMainActivityTitle;
            TextView tvMainActivityInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMainActivityTitle = itemView.findViewById(R.id.tvMainActivityTitle);
            tvMainActivityInfo = itemView.findViewById(R.id.tvMainActivityInfo);
        }
    }
}
