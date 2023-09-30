package com.example.sumon.androidvolley.model;

public class UserPersonalityArr {
    private Object[] hobbies;
    private Object[] interests;
    private Object[] values;

    public UserPersonalityArr(){}

    public Object[] getHobbies(){
        return hobbies;
    }

    public void setHobbies(Object[] hobbies){
        this.hobbies = new Object[hobbies.length];
        this.hobbies = hobbies.clone();
//        this.hobbies = hobbies;
    }

    public Object[] getInterests(){
        return interests;
    }

    public void setInterests(Object[] interests){
        this.interests = new Object[interests.length];
        this.interests = interests.clone();
    }

}
