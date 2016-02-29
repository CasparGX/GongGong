package com.sky31.gonggong.app;

import android.app.Application;

/**
 * Created by root on 16-2-24.
 */
public class App extends Application {
    public App() {}

    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static App getApp() {
        return app;
    }

    private int homeLayoutHeight;
    private int homeLayoutWidth;

    public int getHomeLayoutWidth() {
        return homeLayoutWidth;
    }

    public void setHomeLayoutWidth(int homeLayoutWidth) {
        this.homeLayoutWidth = homeLayoutWidth;
    }

    public int getHomeLayoutHeight() {
        return homeLayoutHeight;
    }

    public void setHomeLayoutHeight(int homeLayoutHeight) {
        this.homeLayoutHeight = homeLayoutHeight;
    }
}
