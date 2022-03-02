package com.example.golfskinsapp;

import java.util.ArrayList;

public class Course {

    private ArrayList<Hole> course;

    public Course(String course) {
        this.course = new ArrayList<Hole>(17);

        switch (course){
            case "north" :
                set_up_north_course();
                break;
            case "south" :
                set_up_south_course();
                break;
        }
    }

    public String get_course_info() {
        String result = "";

        int index = 0;
        for(Hole current_hole : this.course) {
            index += 1;
            result += "Current Hole : " + index + " That is a : " + current_hole.getHoleInfo() + "\n";
        }

        return result;
    }

    public String get_hole_info(int index) {
        return course.get(index).getHoleInfo();
    }

    private void set_up_north_course() {
        this.course.add(0, new Hole(4,7));
        this.course.add(1, new Hole(5,13));
        this.course.add(2, new Hole(3,9));
        this.course.add(3, new Hole(4,3));
        this.course.add(4, new Hole(4,1));
        this.course.add(5, new Hole(4,5));
        this.course.add(6, new Hole(3,11));
        this.course.add(7, new Hole(5,15));
        this.course.add(8, new Hole(4,17));

        this.course.add(9, new Hole(4,4));
        this.course.add(10, new Hole(4,12));
        this.course.add(11, new Hole(3,14));
        this.course.add(12, new Hole(5,16));
        this.course.add(13, new Hole(3,6));
        this.course.add(14, new Hole(5,18));
        this.course.add(15, new Hole(4,2));
        this.course.add(16, new Hole(4,10));
        this.course.add(17, new Hole(4,8));
    }

    private void set_up_south_course() {
        this.course.add(0, new Hole(5,11));
        this.course.add(1, new Hole(4,7));
        this.course.add(2, new Hole(3,13));
        this.course.add(3, new Hole(4,5));
        this.course.add(4, new Hole(4,9));
        this.course.add(5, new Hole(5,17));
        this.course.add(6, new Hole(3,15));
        this.course.add(7, new Hole(5,1));
        this.course.add(8, new Hole(4,3));

        this.course.add(9, new Hole(4,4));
        this.course.add(10, new Hole(5,18));
        this.course.add(11, new Hole(3,12));
        this.course.add(12, new Hole(4,8));
        this.course.add(13, new Hole(4,2));
        this.course.add(14, new Hole(5,16));
        this.course.add(15, new Hole(3,10));
        this.course.add(16, new Hole(4,14));
        this.course.add(17, new Hole(4,6));
    }
}
