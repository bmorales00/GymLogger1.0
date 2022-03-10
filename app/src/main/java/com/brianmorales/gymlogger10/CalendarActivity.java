package com.brianmorales.gymlogger10;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {

    public CalendarView cvCalendar;
    private String tvDate;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_view);

        cvCalendar = findViewById(R.id.cvCalender);


        cvCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                //date = (i1 +1) + "/" + i2 + "/" +i;
                Calendar calendar = Calendar.getInstance();
                calendar.set(i,i1,i2);
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                switch(dayOfWeek){
                    case Calendar.MONDAY:
                        tvDate=("Monday");
                        break;
                    case Calendar.TUESDAY:
                        tvDate=("Tuesday");
                        break;
                    case Calendar.WEDNESDAY:
                        tvDate=("Wednesday");
                        break;
                    case Calendar.THURSDAY:
                        tvDate=("Thursday");
                        break;
                    case Calendar.FRIDAY:
                        tvDate=("Friday");
                        break;
                    case Calendar.SATURDAY:
                        tvDate=("Saturday");
                        break;
                    case Calendar.SUNDAY:
                        tvDate=("Sunday");
                        break;
                }
                finish();
            }

        });

        cvCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


}
