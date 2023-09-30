package com.example.sumon.androidvolley.model;
/**
 * @author kaiheng
 */
public class Match {
    private String groupName;
    private String email;
    private String phoneNumber;
    private String name;
    private String aboutMe;
    private String personality;
    private int personality_id;
    private String gender;
    private String userName;
    private String password;
    private int event_id;

    public Match(){
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setPersonalityId(int pId){
        this.personality_id = pId;
    }

    public int getPersonality_id(){
        return personality_id;
    }

    public void setEventId(int eId){
        this.event_id = eId;
    }

    public int getEventId(){
        return event_id;
    }

    public void setUsername(String uName){
        this.userName = uName;
    }

    public String getUsername(){
        return userName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getPersonality() {
        return personality;
    }

    public void setPersonality(String personality) {
        this.personality = personality;
    }
    public void setPassword(String pass){
        this.password = pass;
    }
    public String getPassword(){
        return password;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }



    public String getGroupName(){
        return groupName;
    }

    public String printable(){
        return "Congratulations!!! \n New Matched group: "+ getGroupName();
    }
}
