package com.example.golfskinsapp;

import com.google.firebase.firestore.PropertyName;

public class Hole {
    private int par;
    private int stroke_index;

    public Hole() {}

    public Hole(int par, int stroke_index) {
        this.par = par;
        this.stroke_index = stroke_index;
    }

    @PropertyName("par")
    public int getPar() {
        return par;
    }

    @PropertyName("stroke_index")
    public int getStroke_index() {
        return stroke_index;
    }

    public String getHoleInfo() {
        return "Par : " + par + ", Stroke Index : " + stroke_index;
    }

    public String getHoleInfoBack() {return "(" + par + " " + stroke_index + ")"; }

    @PropertyName("par")
    public void setPar(int par) {
        this.par = par;
    }

    @PropertyName("stroke_index")
    public void setStroke_index(int stroke_index) {
        this.stroke_index = stroke_index;
    }

}
