package com.example.golfskinsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button play_round, join_group;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        join_group = findViewById(R.id.join_group);
        play_round = findViewById(R.id.play_round);
        title = findViewById(R.id.title);

        play_round.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { onSetPlayButton(v); }
        });

        join_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { onSetJoinButton (v); }
        });
    }

    /**
     * Sends user to start the round page
     * @param view
     */
    public void onSetPlayButton(View view) {
        Intent intent = new Intent(getApplicationContext(), play_round.class);
        MainActivity.this.startActivity(intent);
    }

    /**
     * Sends user to find a round page
     * @param view
     */
    public void onSetJoinButton(View view) {
        Intent intent = new Intent(getApplicationContext(), find_round.class);
        MainActivity.this.startActivity(intent);
    }
}