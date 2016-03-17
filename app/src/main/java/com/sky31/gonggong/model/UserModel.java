package com.sky31.gonggong.model;

import android.content.Context;

import com.sky31.gonggong.app.App;
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
    private static ACache aCache;

    private UserModel() {
        aCache = ACache.get(App.getApp().getApplicationContext());
    }

    public static ACache getaCache() {
        return aCache;
    }

    public static String getEcard_password() {
        return aCache.getAsString(Constants.Key.ECARD_PASSWORD);
    }

    public static void setEcard_password(String ecard_password) {
        aCache.put(Constants.Key.ECARD_PASSWORD, ecard_password);
    }

    public static String getLibrary_password() {
        return aCache.getAsString(Constants.Key.LIBRARY_PASSWORD);
    }

    public static void setLibraryPassword(String library_password) {
        aCache.put(Constants.Key.LIBRARY_PASSWORD, library_password);
    }

    public static String getSid() {
        return aCache.getAsString(Constants.Key.SID);
    }

    public static void setSid(String sid) {
        aCache.put(Constants.Key.SID, sid);
    }

    public static String getPassword() {
        return aCache.getAsString(Constants.Key.PASSWORD);
    }

    public static void setPassword(String password) {
        aCache.put(Constants.Key.PASSWORD, password);
    }

    public static void setCache(Context context) {
        ACache aCache = ACache.get(context);
        if (getLibrary_password() != null) {
        }
        if (getEcard_password() != null) {
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
