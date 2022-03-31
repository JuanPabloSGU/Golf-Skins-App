package com.example.golfskinsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.metrics.Event;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class start_round extends AppCompatActivity {

    private String group_id;
    private String document_id;

    TextView title, player_1, player_2, player_3, player_4;
    EditText player_1_score, player_2_score, player_3_score, player_4_score;
    Button confirm_button;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_round);

        title = findViewById(R.id.title);
        player_1 = findViewById(R.id.player_1);
        player_2 = findViewById(R.id.player_2);
        player_3 = findViewById(R.id.player_3);
        player_4 = findViewById(R.id.player_4);

        player_1_score = findViewById(R.id.player_1_score);
        player_2_score = findViewById(R.id.player_2_score);
        player_3_score = findViewById(R.id.player_3_score);
        player_4_score = findViewById(R.id.player_4_score);

        confirm_button = findViewById(R.id.confirm_button);

        Intent prev_intent = getIntent();
        group_id = prev_intent.getStringExtra("group_id");

        dbRetrieve(new SRCallBack() {
            @Override
            public void onCallBack(String documentID) {
                document_id = documentID;
            }
        });

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { refreshPage(v); }
        });
    }

    public void refreshPage(View view) {
        Intent intent = new Intent(getApplicationContext(), start_round.class);
        intent.putExtra("group_id", group_id);

        System.out.println("Why isn't this working " + document_id);

        start_round.this.startActivity(intent);
    }

    private void dbRetrieve(SRCallBack myCallBack) {
        db.collection("SkinsGame")
                .whereEqualTo("game_id", group_id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("RESULTS FROM DB : ", document.getId() + " => " + document.getData());
                                String result = document.get("Group").toString();

                                updateTitle(result.split(",")[1]);
                                updateGrouping(result.split(",")[2]);

                                myCallBack.onCallBack(document.getId());
                            }
                        } else {
                            Log.d("RESULTS FROM DB : ", "Error getting documents : ", task.getException());
                        }
                    }
                });
    }


    private void updateTitle(String s) {

        String[] hole = parseHole(s);
        String current_hole = hole[0];
        String hole_par = hole[1];
        String hole_index = hole[2];

        String course = parseCourse(s);

        title.setText("Hole : " + current_hole + "\n Stroke Index : " + hole_index +
                ", Par : " +
                hole_par);
    }

    private String parseCourse(String s) {

        Pattern p = Pattern.compile("(?<=)\\w+");
        Matcher m = p.matcher(s);

        String[] course = new String[5];
        int pos = 0;

        while(m.find()) {
            course[pos] = m.group();
            pos += 1;
        }

        return course[course.length - 1];
    }

    private String[] parseHole(String s) {
        Pattern numbers = Pattern.compile("(?<=)\\d+");
        Matcher m_numbers = numbers.matcher(s);

        String[] hole = new String[3];

        int pos = 0;

        while(m_numbers.find()) {

            hole[pos] = m_numbers.group();

            pos += 1;
        }

        return hole;
    }

    private void updateGrouping(String s) {
        System.out.println(s);

        String[] players = parsePlayers(s);

        player_1.setText(players[0].split(" ")[0] + " ");
        player_2.setText(players[1].split(" ")[0] + " ");
        player_3.setText(players[2].split(" ")[0] + " ");
        player_4.setText(players[3].split(" ")[0] + " ");
    }

    private String[] parsePlayers(String s) {
        Pattern p = Pattern.compile("\\((.*?)\\)");
        Matcher m = p.matcher(s);

        int index = 0;

        String[] players = new String[4];

        while(m.find()) {
            players[index] = m.group(1);
            index += 1;
        }

        for(int i = 0; i < players.length; i++) {
            System.out.println(players[i]);
        }

        return players;
    }
}