package com.brianmorales.gymlogger10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.brianmorales.gymlogger10.models.Exercises;
import com.brianmorales.gymlogger10.utility.ExerciseList;

import java.util.ArrayList;

public class ExercisesActivity extends AppCompatActivity implements ExercisesAdapter.OnTextClickListener {

    RecyclerView rvExercises;
    TextView tvNameOfExercises;
    ArrayList<Exercises> exercises;
    String nameOfExercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercises);

        rvExercises = findViewById(R.id.rvExcercises);
        tvNameOfExercises = findViewById(R.id.tvNameOfExc);

        exercises = new ArrayList<>();
        ExerciseList.abs.setExerciseList(ExerciseList.absList);
        ExerciseList.back.setExerciseList(ExerciseList.backList);
        ExerciseList.biceps.setExerciseList(ExerciseList.bicepsList);
        ExerciseList.chest.setExerciseList(ExerciseList.chestList);
        ExerciseList.legs.setExerciseList(ExerciseList.legsList);
        ExerciseList.shoulders.setExerciseList(ExerciseList.shouldersList);
        ExerciseList.triceps.setExerciseList(ExerciseList.tricepsList);

        exercises.add(ExerciseList.abs);
        exercises.add(ExerciseList.back);
        exercises.add(ExerciseList.biceps);
        exercises.add(ExerciseList.chest);
        exercises.add(ExerciseList.legs);
        exercises.add(ExerciseList.shoulders);
        exercises.add(ExerciseList.triceps);

        ExercisesAdapter adapter= new ExercisesAdapter(ExercisesActivity.this, exercises, nameOfExercise);


        rvExercises.setHasFixedSize(true);
        rvExercises.setAdapter(adapter);
        rvExercises.setLayoutManager(new LinearLayoutManager(this));

    }
    private void startGymLogIntent() {
        Intent intent = new Intent(this, GymLogActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("Title", nameOfExercise);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    @Override
    public void onTextClick(Intent intent) {
        nameOfExercise = intent.getStringExtra("Title");
        Log.e("TitleOfExercise", intent.getStringExtra("Title"));
        startGymLogIntent();
    }
}