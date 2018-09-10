package com.example.moham.roomsqlite.views;

import android.arch.lifecycle.LiveData;

import com.example.moham.roomsqlite.data.model.Todo;

import java.util.List;

public interface TodoView extends BaseView {
  void loadTodos(LiveData<List<Todo>> todoList);
  void add();
}
