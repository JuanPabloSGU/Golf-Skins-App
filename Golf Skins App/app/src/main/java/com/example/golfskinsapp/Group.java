package com.example.golfskinsapp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.PropertyName;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;

public class Group {
    private ArrayList<Player> players;
    private Course course;
    private String unique_id;
    private boolean id_bool;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public Group() { }

    public Group(ArrayList<Player> players, Course course) {
        this.players = players;
        this.course = course;

        id_bool = true;

        unique_id = generate_unique_id();
    }

    @PropertyName("groupInfo")
    public String getGroupInfo() {

        String result = "";
        for(Player player : players) {
            result += player.getPlayerInfoBack();
        }

        return result;
    }

    @PropertyName("unique_id")
    public String getUnique_id() {
        return unique_id;
    }

    public String getCurrentHole() {
        return this.course.get_current_hole().toString();
    }

    private String generate_unique_id() {

        byte[] id;
        String result = null;

        try{
            SecureRandom random_num = SecureRandom.getInstance("SHA1PRNG");
            id = new byte[2];
            random_num.nextBytes(id);

            result = hexEncode(id);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return result;
    }

    private String hexEncode(byte[] id){
        StringBuilder result = new StringBuilder();

        char[] digits = {'0', '1', '2', '3', '4','5','6','7','8','9','a','b','c','d','e','f'};

        for(int i = 0; i < id.length; i++){
            byte bit = id[i];
            result.append(digits[(bit&0xf0) >> 4]);
            result.append(digits[(bit&0x0f)]);
        }

        if(is_unique_id(result.toString()) == false){
            generate_unique_id();
        }

        return result.toString();
    }

    private boolean is_unique_id(String key) {

        db.collection("Game")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        Log.w("Unique Complete", "Unique Key Generated");

                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()) {
                                if(document.exists()) {
                                    String result = document.getString("game_id");
                                    if(result.equals(key)) {
                                        id_bool = false;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Unique Failed", "Failed to access unique ID");
            }
        });

        return id_bool;
    }



}
