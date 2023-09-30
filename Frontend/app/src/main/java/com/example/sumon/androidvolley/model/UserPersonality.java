package com.example.sumon.androidvolley.model;

import java.util.ArrayList;

public class UserPersonality {
    private int id;
    private ArrayList<String> hobbiesList = new ArrayList<>();
    private ArrayList<String> interestsList = new ArrayList<>();



    public UserPersonality(){
    }

    public int getId(){
        return id;
    }

//    public void setId(int id){
//        this.id = id;
//    }

    public Object[] getHobbies() {
        return hobbiesList.toArray();
    }

    public void setHobbies(String hobbies){
        this.hobbiesList.add(hobbies);
    }

    public Object[] getInterests(){
        return interestsList.toArray();
    }

    public void setInterests(String interests) {
        this.interestsList.add(interests);
    }
}
