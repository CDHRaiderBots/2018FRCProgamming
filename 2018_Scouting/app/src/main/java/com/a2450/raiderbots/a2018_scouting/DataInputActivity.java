package com.a2450.raiderbots.a2018_scouting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.NumberPicker;

public class DataInputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_input);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //get intent that launched activity, and extract the relevant string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        getSupportActionBar().setTitle("Team " + message);
        NumberPicker ClimbingFauxBarNumberPicker = findViewById(R.id.ClimbingFauxBarNumberPicker);
        ClimbingFauxBarNumberPicker.setMinValue(0);
        ClimbingFauxBarNumberPicker.setMaxValue(2);
        ClimbingFauxBarNumberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
    }
    public void toggleStopwatch(View view) {
        
    }
}
