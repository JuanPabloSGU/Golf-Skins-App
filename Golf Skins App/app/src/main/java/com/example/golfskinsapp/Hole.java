package com.example.golfskinsapp;

public class Hole {
    private int par;
    private int stroke_index;

    public Hole(int par, int stroke_index) {
        this.par = par;
        this.stroke_index = stroke_index;
    }

    public String getHoleInfo() {
        return "Par : " + par + ", Stroke Index : " + stroke_index;
    }
}
