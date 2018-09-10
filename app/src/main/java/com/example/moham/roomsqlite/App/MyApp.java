package com.example.moham.roomsqlite.App;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.FragmentActivity;

import com.example.moham.roomsqlite.di.components.AppComponent;
import com.example.moham.roomsqlite.di.components.DaggerAppComponent;
import com.example.moham.roomsqlite.di.modules.AppModule;
import com.example.moham.roomsqlite.di.modules.RoomModule;

public class MyApp extends Application {
    private AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
      appComponent= DaggerAppComponent.builder()
              .roomModule(new RoomModule(this))
              .appModule(new AppModule(this))
              .build();
      appComponent.inject(this);

    }

    public static MyApp get(Activity activity){
        return (MyApp) activity.getApplication();
    }

    public static MyApp get(FragmentActivity activity){
        return (MyApp) activity.getApplication();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
