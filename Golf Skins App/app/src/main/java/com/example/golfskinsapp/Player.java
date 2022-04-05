package com.example.golfskinsapp;

import com.google.firebase.firestore.PropertyName;

public class Player {
    private String name;
    private float handicap;

    public Player() {}

    public Player (String name, float handicap) {
        this.name = name;
        this.handicap = handicap;
    }

    public String getPlayerInfo() {
        String result = name + " with a handicap of " + handicap;
        return result;
    }

    public String getPlayerInfoBack() {
        return "(" + name + " " + handicap + ")";
    }

    @PropertyName("name")
    public String getName() {
        return this.name;
    }

    @PropertyName("handicap")
    public String getHandicap() {
        return String.valueOf(handicap);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHandicap(float handicap){
        this.handicap = handicap;
    }
}
