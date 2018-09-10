package com.example.moham.roomsqlite.data.db;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class DataConverter {
    private static Gson gson=new Gson();

    @TypeConverter
    public static String saveTypes(List<String> list){
        return gson.toJson(list);
    }

    @TypeConverter
    public static List<String> getTypes(String s){
        Type type = new TypeToken<List<String>>() {
        }.getType();
        return gson.fromJson(s,type);
    }
}
