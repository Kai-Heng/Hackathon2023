package com.example.sumon.androidvolley.model;

public class MessageUser {
    private int id;
    private UserPersonalityArr personality;
    private String email;
    private String name;
    private String userName;
    private String password;
    private String gender;
    private String phoneNumber;

    public MessageUser(){}

    public int getId(){
        return id;
    }

//    public void setId(int id){
//        this.id = id;
//    }

    public UserPersonalityArr getPersonality(){
        return personality;
    }

    public void setPersonality(UserPersonalityArr personality){
        this.personality = personality;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getGender(){
        return gender;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
}
