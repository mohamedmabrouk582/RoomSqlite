package com.example.moham.roomsqlite.di.components;

import com.example.moham.roomsqlite.App.MyApp;
import com.example.moham.roomsqlite.data.db.TodoDao;
import com.example.moham.roomsqlite.di.modules.AppModule;
import com.example.moham.roomsqlite.di.modules.RoomModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, RoomModule.class})
public interface AppComponent {
    TodoDao getDao();
    void inject(MyApp app);
}
