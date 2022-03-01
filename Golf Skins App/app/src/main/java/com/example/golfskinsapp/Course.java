package com.example.golfskinsapp;

import java.util.ArrayList;

public class Course {

    private ArrayList<Hole> north_course = new ArrayList<Hole>();
    private ArrayList<Hole> south_course = new ArrayList<Hole>();

    private void set_up_north_course() {
        north_course.set(0, new Hole(4,7));
        north_course.set(1, new Hole(5,13));
        north_course.set(2, new Hole(3,9));
        north_course.set(3, new Hole(4,3));
        north_course.set(4, new Hole(4,1));
        north_course.set(5, new Hole(4,5));
        north_course.set(6, new Hole(3,11));
        north_course.set(7, new Hole(5,15));
        north_course.set(8, new Hole(4,17));

        north_course.set(9, new Hole(4,4));
        north_course.set(10, new Hole(4,12));
        north_course.set(11, new Hole(3,14));
        north_course.set(12, new Hole(5,16));
        north_course.set(13, new Hole(3,6));
        north_course.set(14, new Hole(5,18));
        north_course.set(15, new Hole(4,2));
        north_course.set(16, new Hole(4,10));
        north_course.set(17, new Hole(4,8));
    }

    private void set_up_south_course() {
        south_course.set(0, new Hole(5,11));
        south_course.set(1, new Hole(4,7));
        south_course.set(2, new Hole(3,13));
        south_course.set(3, new Hole(4,5));
        south_course.set(4, new Hole(4,9));
        south_course.set(5, new Hole(5,17));
        south_course.set(6, new Hole(3,15));
        south_course.set(7, new Hole(5,1));
        south_course.set(8, new Hole(4,3));

        south_course.set(9, new Hole(4,4));
        south_course.set(10, new Hole(5,18));
        south_course.set(11, new Hole(3,12));
        south_course.set(12, new Hole(4,8));
        south_course.set(13, new Hole(4,2));
        south_course.set(14, new Hole(5,16));
        south_course.set(15, new Hole(3,10));
        south_course.set(16, new Hole(4,14));
        south_course.set(17, new Hole(4,6));
    }

}
