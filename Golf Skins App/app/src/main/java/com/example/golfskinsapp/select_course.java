package com.example.golfskinsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class select_course extends AppCompatActivity {

    TextView course_header;
    Button north_course, south_course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_course);

        course_header = findViewById(R.id.course_header);
        north_course = findViewById(R.id.north_course);
        south_course = findViewById(R.id.south_course);

        north_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent return_intent = new Intent();

                return_intent.putExtra("course_name", "north");

                setResult(RESULT_OK, return_intent);
                finish();
            }
        });

        south_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent return_intent = new Intent();

                return_intent.putExtra("course_name", "south");

                setResult(RESULT_OK, return_intent);
                finish();
            }
        });
    }
}