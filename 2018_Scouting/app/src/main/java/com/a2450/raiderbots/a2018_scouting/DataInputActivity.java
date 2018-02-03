package com.a2450.raiderbots.a2018_scouting;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataInputActivity extends AppCompatActivity {
    Handler handler;
    long startTime, MillisecondTime, TimeBuff, UpdateTime = 0L ;
    TextView timerText;
    boolean isTimerRunning;
    Context myContext;
    String teamNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_input);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //get intent that launched activity, and extract the relevant string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        teamNumber = message;
        getSupportActionBar().setTitle("Team " + message);
        NumberPicker ClimbingFauxBarNumberPicker = findViewById(R.id.ClimbingFauxBarNumberPicker);
        ClimbingFauxBarNumberPicker.setMinValue(0);
        ClimbingFauxBarNumberPicker.setMaxValue(2);
        ClimbingFauxBarNumberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        timerText = findViewById(R.id.TimerTime);
        handler = new Handler();
        isTimerRunning = false;
        myContext = this;
    }
    public void toggleStopwatch(View view) {
        if (isTimerRunning == false) {
            isTimerRunning = true;
            startTime = SystemClock.uptimeMillis();
            handler.postDelayed(runnable, 0);
        }
        else {
            handler.removeCallbacks(runnable);
            isTimerRunning = false;
        }
    }
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            MillisecondTime = SystemClock.uptimeMillis() - startTime;
            long seconds = MillisecondTime / 1000;
            timerText.setText("" + seconds);
            handler.postDelayed(this, 0);
        }
    };
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
    public void export(View view){
        String sFileName = "TestName";
        String sBody = "O hai mark";
        try {
            File root = new File(Environment.getExternalStorageDirectory(), "Notes");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();
            Toast.makeText(myContext, "Saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finish();
    }
    void createExternalStoragePublicText() {
        File path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS);
        File file = new File(path, "team" + teamNumber + ".txt");
    }
    /*
    TODO finish this based on https://developer.android.com/reference/android/os/Environment.html#getExternalStoragePublicDirectory(java.lang.String) and https://developer.android.com/training/data-storage/files.html
     */
}
