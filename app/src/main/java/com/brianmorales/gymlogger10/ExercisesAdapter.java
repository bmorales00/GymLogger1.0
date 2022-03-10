package com.brianmorales.gymlogger10;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.brianmorales.gymlogger10.models.Exercises;

import java.util.ArrayList;
import java.util.List;


public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ViewHolder>{

    public interface OnTextClickListener {
        void onTextClick(Intent intent);
    }

    private String nameOfExercise;
    Context context;
    private OnTextClickListener onTextClicker;
    List<Exercises> exercises;

    public ExercisesAdapter(Context context, List<Exercises> exercises, String nameOfExercise) {
        this.exercises = exercises;
        this.nameOfExercise = nameOfExercise;
        this.context = context;
        this.onTextClicker = (OnTextClickListener) context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.excercises_list_items, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, @SuppressLint("RecyclerView") int i) {
        Exercises exercise = exercises.get(i);
        viewHolder.tvNameOfExercises.setText(exercise.getExerciseTitle());
        viewHolder.tvNameOfExercises.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {

                if(!exercises.get(i).isEmpty()){
                    ArrayList<Exercises> exerciseSubList = new ArrayList<>();
                    for(String t : exercises.get(i).getExerciseList()){
                        exerciseSubList.add(new Exercises(t));
                    }
                    setExercises(exerciseSubList);
                }
                else{
                    //Pass value from adapter to activity//
                    nameOfExercise = exercises.get(i).getExerciseTitle();
                    Intent intent = new Intent();
                    intent.putExtra("Title", nameOfExercise);
                    onTextClicker.onTextClick(intent);
//                    Log.d(tag, "Testing");
                    }
                }
        });

    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }


    @SuppressLint("NotifyDataSetChanged")
    public void setExercises(List<Exercises> exercises) {
        this.exercises = exercises;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNameOfExercises;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameOfExercises = itemView.findViewById(R.id.tvNameOfExc);
        }
    }
}
