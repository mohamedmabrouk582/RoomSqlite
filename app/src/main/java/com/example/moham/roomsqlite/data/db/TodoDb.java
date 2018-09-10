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
}
