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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.Date;

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
            Log.v("stopwatch","Stopwatch started");
        }
        else {
            handler.removeCallbacks(runnable);
            isTimerRunning = false;
            Log.v("stopwatch","Stopwatch stopped");
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
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
    public void export(View view){
        Date currentTime = Calendar.getInstance().getTime();
        String filename = teamNumber + currentTime;
        writeToFile(filename, "text");
        finish();
    }

    public void writeToFile(String filename, String data)
    {
        // Get the directory for the user's public pictures directory.
        final File path =
                Environment.getExternalStoragePublicDirectory
                        (
                                //Environment.DIRECTORY_PICTURES
                                Environment.DIRECTORY_DOCUMENTS + "/Scouting/"
                        );

        // Make sure the path directory exists.
        if(!path.exists())
        {
            // Make it, if it doesn't exit
            path.mkdirs();
        }

        final File file = new File(path, filename + ".txt");

        // Save your stream, don't forget to flush() it before closing it.

        try
        {
            file.createNewFile();
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(data);

            myOutWriter.close();

            fOut.flush();
            fOut.close();
        }
        catch (IOException e)
        {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
