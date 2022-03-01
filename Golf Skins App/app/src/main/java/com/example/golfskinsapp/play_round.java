package com.example.golfskinsapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import java.util.ArrayList;

public class play_round extends AppCompatActivity {

    private int player_count = 0;
    private ArrayList<Player> players = new ArrayList<Player>();

    TextView round_header;
    Button add_player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_round);

        round_header = findViewById(R.id.round_header);
        add_player = findViewById(R.id.add_player);

        add_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnSetAddPlayer(view);
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

                    if(player_count >= 4){
                        return;
                    }

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
}