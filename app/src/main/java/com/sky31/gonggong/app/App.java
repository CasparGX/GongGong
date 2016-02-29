package com.sky31.gonggong.app;

import android.app.Application;

import com.sky31.gonggong.model.UserModel;

/**
 * Created by root on 16-2-24.
 */
public class App extends Application {
    private App() {}
    private static final App app = new App();

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
