package com.example.moham.roomsqlite.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.icu.text.Replaceable;

import com.example.moham.roomsqlite.data.model.Todo;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertTodo(Todo todo);

    @Insert
    void insertListTodo(List<Todo> todos);

    @Query("SELECT * from todo")
    LiveData<List<Todo>> fetchAllTodos();

    @Query("select * from todo where category =:category ")
    LiveData<List<Todo>> fetchTodoListByCategory(String category);

    @Query("select * from todo where id =:id")
    LiveData<Todo> fetchTodoById(int id);

    @Update
    int updateTodo(Todo todo);

    @Delete
    int deleteTodo(Todo todo);
}
