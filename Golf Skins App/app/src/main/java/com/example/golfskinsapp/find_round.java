package com.example.golfskinsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class find_round extends AppCompatActivity {

    private String target_group_id;
    private boolean id_bool = false;

    TextView find_heading, find_heading_2;
    EditText group_input;
    Button group_button;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_round);

        find_heading = findViewById(R.id.find_heading);
        find_heading_2 = findViewById(R.id.find_heading_2);
        group_input = findViewById(R.id.group_id_input);
        group_button = findViewById(R.id.group_id_button);

        group_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    target_group_id = group_input.getText().toString().trim();
                    if(!(valid_input())) { return; }

                    if(find_group()) {
                        //Intent
                        System.out.println("Is this working");
                        Intent intent = new Intent(getApplicationContext(), start_round.class);
                        intent.putExtra("group_id", target_group_id);

                        find_round.this.startActivity(intent);
                    }else {
                        System.out.println("Did not work");
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private boolean valid_input() {

        if(target_group_id.isEmpty()){
            group_input.setError("A proper ID is required");
            group_input.requestFocus();
            return false;
        }

        if(target_group_id.length() < 0) {
            group_input.setError("A proper ID is required");
            group_input.requestFocus();
            return false;
        }

        return true;
    }

    private boolean find_group() {

        db.collection("Game")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Log.w("ID Complete", "Found Game ID");
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()) {
                                if(document.exists()) {
                                    String db_result = document.getString("game_id");
                                    if(db_result.equals(target_group_id)) {
                                        id_bool = true;
                                    }
                                }
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("ID Failed", "Wrong ID or Unable to find group");
            }
        });

        return id_bool;
    }
}