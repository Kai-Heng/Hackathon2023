package com.example.sumon.androidvolley.model;

public class Login {
    private String email;
    private String name;
    private String userName;
    private String password;
    private String gender;
    private String phoneNumber;
    private int id;

    public Login(){}

    public String getUsername(){
        return userName;
    }

    public String getPassword(){
        return password;
    }

    public int getId(){
        return id;
    }

    public void setUsername(String username){
        this.userName = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String printable(){
        return "ID: " + getId() +
                "\nUsername: " + getUsername() +
                "\nPassword: " + getPassword() + "\n";
    }
}
