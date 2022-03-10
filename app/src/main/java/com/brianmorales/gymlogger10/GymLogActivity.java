package com.brianmorales.gymlogger10;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.brianmorales.gymlogger10.database.SQLiteManager;
import com.brianmorales.gymlogger10.models.GymLog;
import com.brianmorales.gymlogger10.database.GymLogDatabase;


public class GymLogActivity extends AppCompatActivity {

    private static class Count{
        static final double WEIGHT = 0.0;
        static final int REPS = 0;
        static final double WEIGHTINCREMENT = 5.0;
        static final int REPINCREMENT = 1;

    }

    EditText etWorkoutTitle;

    ImageButton ibWeightMinus;
    EditText etWeightCount;
    ImageButton ibWeightPlus;

    ImageButton ibRepMinus;
    EditText etRepCount;
    ImageButton ibRepPlus;

    Button btSave;
    Button btClear;

    RecyclerView rvLoggerCard;

    Bundle bundle;
    String workOutTitle;

    SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(GymLogActivity.this);



    GymLog logger;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_information);
        bundle = getIntent().getExtras();
        workOutTitle = bundle.getString("Title");

        etWorkoutTitle = findViewById(R.id.etWorkoutTitle);
        etWorkoutTitle.setText(workOutTitle);

        logger = new GymLog(workOutTitle);


        ibWeightMinus = findViewById(R.id.ibWeightMinus);
        etWeightCount = findViewById(R.id.etWeightCount);
        ibWeightPlus = findViewById(R.id.ibWeightPlus);

        ibRepMinus = findViewById(R.id.ibRepMinus);
        etRepCount = findViewById(R.id.etRepCount);
        ibRepPlus = findViewById(R.id.ibRepPlus);

        btSave = findViewById(R.id.btSave);
        btClear = findViewById(R.id.btClear);

        rvLoggerCard = findViewById(R.id.rvLoggerCard);
        etWorkoutTitle.setEnabled(false);



        MinusWeight();
        PlusWeight();

        MinusRep();
        PlusRep();

        Clear();
        Save();

    }



    private void MinusWeight() {
        ibWeightMinus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(etWeightCount.getText().length() == 0){
                    etWeightCount.setText(Double.toString(Count.WEIGHT));
                }
                else if(Double.parseDouble(etWeightCount.getText().toString()) > Count.WEIGHT &&
                        Double.parseDouble(etWeightCount.getText().toString()) >= Count.WEIGHTINCREMENT){
                    double newWeight = Double.parseDouble(etWeightCount.getText().toString());
                    newWeight -= Count.WEIGHTINCREMENT;
                    etWeightCount.setText(Double.toString(newWeight));
                }
                else if(Double.parseDouble(etWeightCount.getText().toString()) > Count.WEIGHT &&
                        Double.parseDouble(etWeightCount.getText().toString()) < Count.WEIGHTINCREMENT){
                    double newWeight = Double.parseDouble(etWeightCount.getText().toString());
                    newWeight -= newWeight;
                    etWeightCount.setText(Double.toString(newWeight));
                }
            }
        });

    }

    private void PlusWeight() {
        ibWeightPlus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(etWeightCount.getText().length() == 0 ){
                    etWeightCount.setText(Double.toString(Count.WEIGHTINCREMENT));
                }
                else{
                    double newWeight = Double.parseDouble(etWeightCount.getText().toString());
                    newWeight += Count.WEIGHTINCREMENT;
                    etWeightCount.setText(Double.toString(newWeight));
                }
            }
        });
    }

    private void MinusRep() {
        ibRepMinus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(etRepCount.getText().length() == 0){
                    etRepCount.setText(Integer.toString(Count.REPS));
                }
                else if(Integer.parseInt(etRepCount.getText().toString()) > 0){
                    int newReps = Integer.parseInt(etRepCount.getText().toString());
                    newReps -=Count.REPINCREMENT;
                    etRepCount.setText(Integer.toString(newReps));
                }
            }
        });

    }
    private void PlusRep() {
        ibRepPlus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(etRepCount.getText().length() == 0){
                    etRepCount.setText(Integer.toString(Count.REPINCREMENT));
                }
                else{
                    int newReps = Integer.parseInt(etRepCount.getText().toString());
                    newReps +=Count.REPINCREMENT;
                    etRepCount.setText(Integer.toString(newReps));
                }
            }
        });

    }
    private void Clear() {
        btClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etRepCount.getText().length() > 0 || etWeightCount.getText().length() > 0){
                    etRepCount.setText("");
                    etWeightCount.setText("");
                }
            }
        });
    }

    private void Save() {
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etWeightCount.getText().length() == 0 && etRepCount.getText().length() == 0 ){
                    ShowSnackbar();
                }
                else{
                    logger.addSets(Double.parseDouble(etWeightCount.getText().toString()),
                            Integer.parseInt(etRepCount.getText().toString()));
                }
                UpdateGymLogAdapter();
                UpdateGymLogDB();
            }
        });
    }

    private void UpdateGymLogDB() {
        if(!GymLogDatabase.containsGymLog(logger)){
            this.logger.setID(GymLogDatabase.getLoggerList().size());
            //this.logger.setSetsDescription(logger.getSetsToString());
            GymLogDatabase.addGymLog(logger);
        }
        GymLogDatabase.updateLoggerDB(logger);
    }

    private void ShowSnackbar() {
        ///THIS NEEDS TO BE A SNACKBAR BUT FOR NOW WILL BE A TOAST
        Toast.makeText(this, "Cannot continue without entering weight and rep count", Toast.LENGTH_LONG).show();
        //Snackbar.make()
    }

    @SuppressLint("NotifyDataSetChanged")
    private void UpdateGymLogAdapter() {
        GymLogAdapter adapter = new GymLogAdapter(this, logger);
        adapter.notifyDataSetChanged();

        rvLoggerCard.setHasFixedSize(true);
        rvLoggerCard.setAdapter(adapter);
        rvLoggerCard.setLayoutManager(new LinearLayoutManager(this));

    }
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode == KeyEvent.KEYCODE_BACK)){
            //SQL EDIT
            sqLiteManager.addLoggerToDB(logger);
            this.finish();
            setGymLogEmptyText();
            MainActivity.rvSaveCard.getAdapter().notifyDataSetChanged();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setGymLogEmptyText() {
        if(GymLogDatabase.getListSize() != 0){
            MainActivity.tvGymLogEmpty.setAlpha(0.0f);
            MainActivity.tvGymLogEmpty.setVisibility(View.INVISIBLE);
        }
    }

}
