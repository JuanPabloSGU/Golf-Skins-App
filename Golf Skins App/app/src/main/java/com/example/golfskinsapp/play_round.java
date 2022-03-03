package com.example.golfskinsapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class play_round extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private int player_count = 0;
    private boolean chose_course = false;
    private ArrayList<Player> players = new ArrayList<Player>();
    private Course course;

    private Group group;
    private String group_id;

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
                        course = new Course(chosen_course);
                    }
                }
            });

    public void OnSetStartRound(View view) {
        Intent intent = new Intent(getApplicationContext(), start_round.class);
        add_to_db();

        // Pass intent with group_id to start_round to see the have a key that access the group
        intent.putExtra("group_id", group_id);

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
            update_button_settings(add_player, Color.LTGRAY, Color.BLACK, false);
        }

        if(player_count != 3){
            update_button_settings(start_round, Color.LTGRAY, Color.BLACK, false);
        }

        if (player_count == 3){
            // Ready to play
            update_button_settings(start_round, Color.RED, Color.BLACK, true);
        }
    }

    private void update_button_settings(Button button, int prime, int secondary, boolean visible) {
        button.setEnabled(visible);
        button.setBackgroundColor(prime);
        button.setTextColor(secondary);
    }

    private void add_to_db() {

        group = new Group(players, course);
        group_id = group.getUnique_id();

        System.out.println("Unique ID " + group_id);

        Map<String, Object> data = new HashMap<>();
        data.put("group", group);
        data.put("Players", players);
        data.put("course", course);
        data.put("game_id", group_id);

        db.collection("Game")
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Group Saved", "Group has been saved");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Group Saved", "Group has not been saved");
                    }
                });
    }
}