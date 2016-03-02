package com.sky31.gonggong.model;

import android.content.Context;

import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.util.ACache;

/**
 * Created by root on 16-2-28.
 */
public class UserModel {

    private UserModel() {}
    private static final UserModel userModel = new UserModel() {};
    public static UserModel getUserModel(){
        return userModel;
    }

    private static String sid = null;

    public static String getSid() {
        return sid;
    }

    public static void setSid(String sid) {
        UserModel.sid = sid;
    }

    private static String password = null;

    public static String getPassword() {

        return password;
    }

    public static void setPassword(String password) {
        UserModel.password = password;
    }

    public static void setCache(Context context){
        ACache aCache = ACache.get(context);
        aCache.put(Constants.Key.SID,getSid());
        aCache.put(Constants.Key.PASSWORD,getPassword());
    }

    public static void setCacheNone(Context context){
        ACache aCache = ACache.get(context);
        aCache.remove(Constants.Key.SID);
        aCache.remove(Constants.Key.PASSWORD);
    }
}
