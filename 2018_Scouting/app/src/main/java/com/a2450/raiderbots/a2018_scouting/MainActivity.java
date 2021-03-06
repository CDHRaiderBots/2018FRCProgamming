package com.a2450.raiderbots.a2018_scouting;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.a2450.raiderbots.a2018_scouting.MESSAGE";
    Context myContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myContext = this;

    }
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DataInputActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();

        if (message.isEmpty()) {
            Toast.makeText(myContext, "no", Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(myContext, "Message text is: " + message, Toast.LENGTH_SHORT).show();
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }

    }
}
