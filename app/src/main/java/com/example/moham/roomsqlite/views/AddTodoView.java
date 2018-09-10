package com.example.moham.roomsqlite.views;

import com.example.moham.roomsqlite.data.model.Todo;

public interface AddTodoView extends BaseView {
  void add(Todo todo);
  void close();
}
