package com.example.yendry.room1;

import android.app.Application;

import com.example.yendry.room1.di.ApplicationModule;
import com.example.yendry.room1.di.Component;
import com.example.yendry.room1.di.DaggerComponent;
import com.example.yendry.room1.di.RoomModule;

/**
 * Created by yendry on 5/11/18.
 */

public class App extends Application {
    private Component component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .roomModule(new RoomModule(this))
                .build();
    }

    public Component getComponent() {
        return component;
    }
}
