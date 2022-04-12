package com.oversight.oversight.Persistence.Entities;

public class AppUser {

    private String userName;
    private String password;
    private long ID;

    public AppUser(User user){
        this.userName = user.getUsername();
        this.password = user.getPassword();
        this.ID = user.getID();
    }

    public AppUser(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }
}
