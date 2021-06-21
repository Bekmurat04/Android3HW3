package com.example.mockerapi;

import android.app.Application;

import com.example.mockerapi.data.network.MockerService;

public class App extends Application {

    public static MockerService service;

    @Override
    public void onCreate() {
        super.onCreate();
        service = new MockerService();
    }
}
