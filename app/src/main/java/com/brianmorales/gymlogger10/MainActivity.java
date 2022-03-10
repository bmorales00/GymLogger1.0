package com.brianmorales.gymlogger10;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.brianmorales.gymlogger10.database.SQLiteManager;
import android.widget.Toast;
import com.brianmorales.gymlogger10.database.GymLogDatabase;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Button btPrev;
    Button btNext;
    TextView tvDate;
    Button btNewWorkout;

    @SuppressLint("StaticFieldLeak")
    public static TextView tvGymLogEmpty;

    @SuppressLint("StaticFieldLeak")
    public static RecyclerView rvSaveCard;





    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDate = findViewById(R.id.tvDate);
        tvDate.setText("Today");

        rvSaveCard = findViewById(R.id.rvSaveCard);

        btNewWorkout = findViewById(R.id.btNewWorkout);

        tvGymLogEmpty = findViewById(R.id.tvGymLogEmpty);
        StartAdapter();
        NewWorkOut();

       loadFromDBToMemory();
       setGymLogEmptyText();

    }
    private void NewWorkOut() {
        btNewWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ExercisesActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadFromDBToMemory() {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.populateLoggerList();
    }


    @SuppressLint("NotifyDataSetChanged")
    private void StartAdapter() {
        MainActivityAdapter adapter = new MainActivityAdapter(this, GymLogDatabase.getLoggerList());
        adapter.notifyDataSetChanged();


        rvSaveCard.setLayoutManager(new LinearLayoutManager(this));
        rvSaveCard.setHasFixedSize(true);
        rvSaveCard.setAdapter(adapter);

    }

    private void setGymLogEmptyText() {
        if(GymLogDatabase.getListSize() != 0){
            MainActivity.tvGymLogEmpty.setAlpha(0.0f);
            MainActivity.tvGymLogEmpty.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);


        return super.onOptionsItemSelected(item);
    }
}