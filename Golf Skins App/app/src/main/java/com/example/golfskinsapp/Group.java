package com.example.golfskinsapp;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.security.SecureRandom;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Group {
    private ArrayList<Player> players;
    private Course course;
    private String unique_id;

    public Group(ArrayList<Player> players, Course course) {
        this.players = players;
        this.course = course;

        unique_id = generate_unique_id();
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

    public String getUnique_id() {
        return unique_id;
    }

    private String generate_unique_id() {

        byte[] id;
        String result = null;

        try{
            SecureRandom prkey = SecureRandom.getInstance("SHA1PRNG");
            id = new byte[4];
            prkey.nextBytes(id);

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

        return result.toString();
    }

}
