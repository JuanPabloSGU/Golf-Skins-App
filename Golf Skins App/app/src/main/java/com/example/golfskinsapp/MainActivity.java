package com.example.golfskinsapp;

import androidx.appcompat.app.AppCompatActivity;
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
    }
}