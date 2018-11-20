package com.mvp.easton.mvp;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mvp.easton.mvp.util.FavoReposHelper;

public class App extends Application {
    private static App context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        FavoReposHelper.init(this);
    }

    public static App getContext() {
        return context;
    }

    public static Gson getGson() {
        GsonBuilder builder = new GsonBuilder();
        return builder.create();
    }
}
