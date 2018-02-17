package com.a2450.raiderbots.a2018_scouting;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
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
    private Toolbar toolbar;
    Boolean autoBaseLine;
    Boolean autoCubeDeposit;
    int blocksScale;
    Button scaleButton;
    int blocksBlue;
    int blocksRed;
    Button teleopBlueButton;
    Button teleopRedButton;
    Boolean canGetCubesFromPortal;
    Boolean canGetCubesFromPile;
    Boolean climbingSuccess;
    String malfunctionType;
    long climbingTime;
    int robotsHeld;
    EditText miscNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_input);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        //get intent that launched activity, and extract the relevant string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        teamNumber = message;
        getSupportActionBar().setTitle("Team " + message);
        /* This is temporarily being removed.
        NumberPicker ClimbingFauxBarNumberPicker = findViewById(R.id.ClimbingFauxBarNumberPicker);
        ClimbingFauxBarNumberPicker.setMinValue(0);
        ClimbingFauxBarNumberPicker.setMaxValue(2);
        ClimbingFauxBarNumberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        */
        timerText = findViewById(R.id.TimerTime);
        handler = new Handler();
        isTimerRunning = false;
        myContext = this;
        autoBaseLine = null;
        autoCubeDeposit = null;
        blocksScale = 0;
        scaleButton = findViewById(R.id.ScaleButton);
        blocksBlue = 0;
        blocksRed = 0;
        teleopBlueButton = findViewById(R.id.BlueSwitchButton);
        teleopRedButton = findViewById(R.id.RedSwitchButton);
        canGetCubesFromPile = null;
        canGetCubesFromPortal = null;
        malfunctionType = null;
        robotsHeld = 0;
        miscNotes = findViewById(R.id.MiscNotes);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Export) {
            Date currentTime = Calendar.getInstance().getTime();
            String filename = teamNumber + " - " + currentTime;
            writeToFile(filename);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
    public void toggleStopwatch(View view) {
        if (!isTimerRunning) {
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
    public void onCheckBoxClick(View view) {
        Boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.AutoBaseLine:
                autoBaseLine = checked;
                break;
            case R.id.AutoCubeDeposit:
                autoCubeDeposit = checked;
                break;
            case R.id.PortalCheckbox:
                canGetCubesFromPortal = checked;
                break;
            case R.id.PileCheckbox:
                canGetCubesFromPile = checked;
                break;

        }
    }
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.SuccessfulClimb:
                climbingSuccess = true;
                break;
            case R.id.FailedClimb:
                climbingSuccess = false;
                break;
            case R.id.DidntClimb:
                climbingSuccess = null;
                break;
            case R.id.CompleteMalfunction:
                malfunctionType = "Complete malfunction";
                break;
            case R.id.PartialMalfunction:
                malfunctionType = "Partial malfunction";
                break;
            case R.id.NoMalfunction:
                malfunctionType = "No malfunction";
                break;
            case R.id.HoldsZeroRobots:
                robotsHeld = 0;
                break;
            case R.id.HoldsOneRobot:
                robotsHeld = 1;
                break;
            case R.id.HoldsTwoRobots:
                robotsHeld = 2;
                break;
        }
    }
    public void incrementBlocksScale(View view) {
        blocksScale ++;
        scaleButton.setText("Scale: " + blocksScale);
    }
    public void incrementBlocksBlue(View view) {
        blocksBlue ++;
        teleopBlueButton.setText("Blue Switch: " + blocksBlue);
    }
    public void incrementBlocksRed(View view) {
        blocksRed ++;
        teleopRedButton.setText("Red Switch: " + blocksRed);
    }

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            MillisecondTime = SystemClock.uptimeMillis() - startTime;
            long seconds = MillisecondTime / 1000;
            timerText.setText("" + seconds);
            climbingTime = seconds;
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
        String filename = teamNumber + " - " + currentTime;
        writeToFile(filename);
        finish();
    }

    public void writeToFile(String filename)
    {
        // generate data
        String data = teamNumber + "," + autoBaseLine + "," + autoCubeDeposit + "," + blocksScale + "," + blocksRed + "," + blocksBlue + "," + canGetCubesFromPortal + "," + canGetCubesFromPile + "," + robotsHeld + "," + climbingTime + "," + climbingSuccess + "," + malfunctionType + ",\"" + miscNotes.getText().toString() + "\"";
        //TODO: make this significantly less bad.
        //FIXME: I already know this won't work assuming the text contains a comma between two double quotes, but that can be fixed later.

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
