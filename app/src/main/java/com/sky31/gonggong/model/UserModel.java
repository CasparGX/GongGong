package com.sky31.gonggong.model;

/**
 * Created by root on 16-2-28.
 */
public class UserModel {

    private UserModel() {}
    private static final UserModel userModel = new UserModel();
    public static UserModel getUserModel(){
        return userModel;
    }

    private String sid = null;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    private String password = null;

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
