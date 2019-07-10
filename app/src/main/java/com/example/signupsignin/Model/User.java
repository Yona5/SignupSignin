package com.example.signupsignin.Model;

public class User {
    private String fname;
    private String lname;
    private int id;
    private String email;
    private String avatar;

    public User(String fname, String lname, int id, String email, String avatar){
        this.fname = fname;
        this.lname = lname;
        this.id = id;
        this.email = email;
        this.avatar = avatar;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
