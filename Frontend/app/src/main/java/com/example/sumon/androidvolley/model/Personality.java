package com.example.sumon.androidvolley.model;
/**
 * @author kaiheng
 */
public class Personality {
    private String hobbies;
    private String interest;
    private String demographic;
    private String lifestyle;
    private String values;
    private String goals;
    private String name;
    private String gender;
    private String phoneNumber;
    private String email;
    private String userName;
    private String password;

    public Personality(){
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String uName){
        this.userName = uName;
    }

    public String getUsername(){
        return userName;
    }
    public void setPassword(String pass){
        this.password = pass;
    }
    public String getPassword(){
        return password;
    }

    public void setHobbies(String hobbies){
        this.hobbies = hobbies;
    }

    public String getHobbies(){
        return hobbies;
    }

    public void setInterest(String interest){
        this.interest = interest;
    }

    public String getInterest(){
        return interest;
    }

    public void setDemographic(String demographic){
        this.demographic = demographic;
    }

    public String getDemographic(){
        return demographic;
    }

    public void setLifestyle(String lifestyle){
        this.lifestyle = lifestyle;
    }

    public String getLifestyle(){
        return lifestyle;
    }

    public void setValues(String values){
        this.values = values;
    }

    public String getValues(){
        return values;
    }

    public void setGoals(String goals){
        this.goals = goals;
    }

    public String getGoals(){
        return goals;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public String getGender(){
        return gender;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber= phoneNumber;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String printable(){
        return "\n Hobbies: "+ getHobbies()
                + "\n Interests: " + getInterest()
                + "\n Demographic: " + getDemographic()
                + "\n Lifestyle: " + getLifestyle()
                + "\n Values: " + getValues()
                + "\n Goals: " + getGoals();
    }
}
