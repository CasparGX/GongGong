package cc.xaabb.gonggong.app;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

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
        LeakCanary.install(this);
    }

    public static App getApp() {
        return app;
    }


}
