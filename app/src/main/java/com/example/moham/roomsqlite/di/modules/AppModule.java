package com.example.moham.roomsqlite.di.modules;

import com.example.moham.roomsqlite.App.MyApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private MyApp myApp;

    public AppModule(MyApp myApp) {
        this.myApp = myApp;
    }

    @Singleton
    @Provides
    public MyApp getMyApp() {
        return myApp;
    }
}
