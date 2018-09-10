package com.example.moham.roomsqlite.data.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.moham.roomsqlite.data.model.Todo;

@Database(exportSchema = false , version = 3,entities = Todo.class)
public abstract class TodoDb extends RoomDatabase {
    public abstract TodoDao getTodoDao();
    private static TodoDao dao;
    public static TodoDao getDao(Context context){
        if (dao==null){
            dao= Room.databaseBuilder(context,TodoDb.class,"todo").addMigrations(new Migration(2,3) {
                @Override
                public void migrate(@NonNull SupportSQLiteDatabase database) {
                    database.execSQL("ALTER TABLE todo ADD COLUMN types TEXT");
                }
            }).build().getTodoDao();
        }
        return dao;
    }

}
