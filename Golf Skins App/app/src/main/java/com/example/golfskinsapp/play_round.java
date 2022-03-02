package com.example.golfskinsapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import java.util.ArrayList;

public class play_round extends AppCompatActivity {

    private int player_count = 0;
    private boolean chose_course = false;
    private ArrayList<Player> players = new ArrayList<Player>();

    TextView round_header;
    Button add_player, start_round, select_course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_round);

        round_header = findViewById(R.id.round_header);
        select_course = findViewById(R.id.select_course);
        add_player = findViewById(R.id.add_player);
        start_round = findViewById(R.id.start_round);

        update_button();

        add_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnSetAddPlayer(view);
            }
        });

        start_round.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnSetStartRound(view);
            }
        });

        select_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                OnSetSelectCourse(view);
            }
        });
    }

    public void OnSetAddPlayer(View view) {
        Intent intent = new Intent(getApplicationContext(), add_Player.class);
        addPlayerActivityResultLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> addPlayerActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    update_button();
                    player_count++;

                    if(result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();

                        String[] return_val = parseData(data.getExtras().getString("Player"));

                        players.add(new Player(
                                return_val[0],Float.parseFloat(return_val[1])
                        ));

                        update_text_view(player_count, players.get(player_count - 1));
                    }
                }
            });

    public void OnSetSelectCourse(View view) {
        Intent intent = new Intent(getApplicationContext(), select_course.class);
        selectCourseActivityResultLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> selectCourseActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if(result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        chose_course = true;
                        // Get chosen Course data
                        String chosen_course = data.getExtras().getString("course_name");

                        // Use data to chose the course that the user has selected.
                        // Update the database. -> Later
                        Course course = new Course(chosen_course);

                        System.out.println(course.get_course_info());
                    }
                }
            });

    public void OnSetStartRound(View view) {
        Intent intent = new Intent(getApplicationContext(), start_round.class);
        play_round.this.startActivity(intent);
    }

    private String[] parseData(String info) {
        return info.split(", ");
    }

    private void update_text_view(int num_players, Player player) {
        TextView current_player;
        switch (num_players) {
            case 1:
                current_player = findViewById(R.id.player_1);
                current_player.setText(player.getPlayerInfo());
                break;
            case 2:
                current_player = findViewById(R.id.player_2);
                current_player.setText(player.getPlayerInfo());
                break;
            case 3:
                current_player = findViewById(R.id.player_3);
                current_player.setText(player.getPlayerInfo());
                break;
            case 4:
                current_player = findViewById(R.id.player_4);
                current_player.setText(player.getPlayerInfo());
                break;
        }
    }

    private void update_button() {
        if(player_count >= 3){
            // Update so that the user can't add another player
            update_button_settings(add_player, Color.LTGRAY, Color.BLACK);
        }

        if(player_count != 3){
            update_button_settings(start_round, Color.LTGRAY, Color.BLACK);
        }else if (player_count == 3 && chose_course){
            // Ready to play
            update_button_settings(start_round, Color.RED, Color.BLACK);
        }
    }

    private void update_button_settings(Button button, int prime, int secondary) {
        button.setEnabled(true);
        button.setBackgroundColor(prime);
        button.setTextColor(secondary);
    }
}