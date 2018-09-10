package com.example.moham.roomsqlite.viewModel.addTodo;

import android.view.View;

import com.example.moham.roomsqlite.viewModel.base.BaseVmodel;
import com.example.moham.roomsqlite.views.AddTodoView;

public interface AddTodoVmodel<v extends AddTodoView> extends BaseVmodel<v> {
  void add(View view);

}
