package com.example.moham.roomsqlite.di.modules;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

import com.example.moham.roomsqlite.data.db.TodoDao;
import com.example.moham.roomsqlite.data.db.TodoDb;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {
    private TodoDb todoDb;

    public RoomModule(Application application) {
     todoDb= Room.databaseBuilder(application.getBaseContext(),TodoDb.class,"todo").addMigrations(new Migration(2,3) {
         @Override
         public void migrate(@NonNull SupportSQLiteDatabase database) {
             database.execSQL("ALTER TABLE todo ADD COLUMN types TEXT");
         }
     }).build();
    }

    @Singleton
    @Provides
    public TodoDb getTodoDb() {
        return todoDb;
    }

    @Singleton
    @Provides
    public TodoDao getDao(TodoDb todoDb){
        return todoDb.getTodoDao();
    }

}
