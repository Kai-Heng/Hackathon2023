package com.example.sumon.androidvolley.model;

public class Group {
    private int id;
    private int size;
    private String groupName;
    public Group(){}

    public int getId(){
        return id;
    }

    public int getSize(){
        return size;
    }

    public String getGroupName(){
        return groupName;
    }

    public void setSize(int size){
        this.size = size;
    }

    public void setGroupName(String groupName){
        this.groupName = groupName;
    }
}

