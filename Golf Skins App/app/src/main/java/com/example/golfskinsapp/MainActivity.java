package com.example.golfskinsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button play_round, select_course;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play_round = findViewById(R.id.play_round);
        select_course = findViewById(R.id.select_course);
        title = findViewById(R.id.title);

        play_round.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) { onSetPlayButton(v); }
        });

        select_course.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) { onSetSelectCourseButton(v); }
        });
    }

    /**
     * Sends user to the play_round page
     * @param view
     */
    public void onSetPlayButton(View view) {
        Intent intent = new Intent(getApplicationContext(), play_round.class);
        MainActivity.this.startActivity(intent);
    }

    /**
     * Sends user to the select_course page
     * @param view
     */
    public void onSetSelectCourseButton(View view) {
        Intent intent = new Intent(getApplicationContext(), select_course.class);
        MainActivity.this.startActivity(intent);
    }
}