package com.sky31.gonggong.model;

import android.content.Context;

import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.util.ACache;

/**
 * Created by root on 16-2-28.
 */
public class UserModel {

    private static final UserModel userModel = new UserModel() {
    };
    private static String sid = null;
    private static String password = null;
    private static String library_password = null;
    private static String ecard_password = null;

    private UserModel() {
    }

    public static String getEcard_password() {
        return ecard_password;
    }

    public static void setEcard_password(String ecard_password) {
        UserModel.ecard_password = ecard_password;
    }

    public static String getLibrary_password() {
        return library_password;
    }

    public static void setLibraryPassword(String library_password) {
        UserModel.library_password = library_password;
    }

    public static UserModel getUserModel() {
        return userModel;
    }

    public static String getSid() {
        return sid;
    }

    public static void setSid(String sid) {
        UserModel.sid = sid;
    }

    public static String getPassword() {

        return password;
    }

    public static void setPassword(String password) {
        UserModel.password = password;
    }

    public static void setCache(Context context) {
        ACache aCache = ACache.get(context);
        aCache.put(Constants.Key.SID, getSid());
        aCache.put(Constants.Key.PASSWORD, getPassword());
        if (getLibrary_password() != null) {
            aCache.put(Constants.Key.LIBRARY_PASSWORD, getLibrary_password());
        }
        if (getEcard_password() != null) {
            aCache.put(Constants.Key.ECARD_PASSWORD, getEcard_password());
        }
    }

    public static void setCacheNone(Context context) {
        ACache aCache = ACache.get(context);
        aCache.remove(Constants.Key.SID);
        aCache.remove(Constants.Key.PASSWORD);
        aCache.remove(Constants.Key.LIBRARY_PASSWORD);
        aCache.remove(Constants.Key.ECARD_PASSWORD);
    }
}
