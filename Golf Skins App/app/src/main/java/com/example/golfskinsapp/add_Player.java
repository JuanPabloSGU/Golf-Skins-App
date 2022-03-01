package com.example.golfskinsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class add_Player extends AppCompatActivity {

    TextView add_header;
    EditText player_name, player_handicap;
    Button submit_player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        add_header = findViewById(R.id.add_header);
        player_name = findViewById(R.id.player_name);
        player_handicap = findViewById(R.id.player_handicap);
        submit_player = findViewById(R.id.submit_player);

        submit_player.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //System.out.println(player_name.getText().toString() + ", " + player_handicap.getText().toString());
                try{
                    if(!(valid_user() && valid_handicap())) { return; }

                    String information = player_name.getText().toString() + ", " + player_handicap.getText().toString();

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("Player", information);

                    setResult(RESULT_OK, returnIntent);
                    finish();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private boolean valid_user() {
        String name = player_name.getText().toString().trim();

        if(name.isEmpty()){
            player_name.setError("A proper name is required");
            player_name.requestFocus();
            return false;
        }

        return true;
    }

    private boolean valid_handicap() {
        String handicap = player_handicap.getText().toString().trim();

        if(handicap.isEmpty()){
            player_handicap.setError("A proper handicap is required");
            player_handicap.requestFocus();
            return false;
        }

        try {
            Float.parseFloat(handicap);
        }catch(Exception e){
            e.printStackTrace();
        }

        return true;
    }
}