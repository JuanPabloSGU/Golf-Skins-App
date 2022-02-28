package com.example.golfskinsapp;

public class Player {
    private String name;
    private float handicap;

    public Player (String name, float handicap) {
        this.name = name;
        this.handicap = handicap;
    }

    public String getPlayerInfo() {
        String result = name + "with a handicap of " + handicap;
        return result;
    }
}
