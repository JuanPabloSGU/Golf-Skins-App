package com.example.golfskinsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;



public class start_round extends AppCompatActivity {

    private Group group;
    private Player players;
    private Course course;
    private String group_id;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_round);

        Intent prev_intent = getIntent();
        group_id = prev_intent.getStringExtra("group_id");

        timeout();
        timeout();

        populate_variables();

        System.out.println(group.getCourseInfo());
        System.out.println(group.getGroupInfo());

        System.out.println(players.getPlayerInfo());

        System.out.println(course.get_course_info());

        System.out.println(group_id.toString());

    }

    private void populate_variables() {
        DocumentReference player_ref = db.collection("Game").document("Players");
        DocumentReference course_ref = db.collection("Game").document("group");
        DocumentReference group_ref = db.collection("Game").document("group");

        Task<DocumentSnapshot> future_player = player_ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot d_player = task.getResult();

                if(d_player.exists()){
                    players = d_player.toObject(Player.class);
                }
            }
        });

        Task<DocumentSnapshot> future_course = course_ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot d_course = task.getResult();

                if (d_course.exists()) {
                    course = d_course.toObject(Course.class);
                }
            }
        });

        Task<DocumentSnapshot> future_group = group_ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot d_group = task.getResult();

                if(d_group.exists()) {
                    group = d_group.toObject(Group.class);
                }
            }
        });
    }

    private void timeout() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}