package com.example.golfskinsapp;

import java.util.ArrayList;

public class Group {
    private ArrayList<Player> players;
    private Course course;

    public Group(ArrayList<Player> players, Course course) {
        this.players = players;
        this.course = course;
    }

    public String getCourseInfo() {
        return this.course.get_course_info();
    }

    public String getGroupInfo() {

        String result = "";
        for(Player player : players) {
            result += player.getPlayerInfo();
        }

        return result;
    }

}
