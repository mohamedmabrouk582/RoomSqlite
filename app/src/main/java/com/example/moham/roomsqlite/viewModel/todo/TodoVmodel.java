package com.example.moham.roomsqlite.viewModel.todo;

import android.view.View;

import com.example.moham.roomsqlite.viewModel.base.BaseVmodel;
import com.example.moham.roomsqlite.views.TodoView;

public interface TodoVmodel<v extends TodoView> extends BaseVmodel<v> {
    void addAction(View view);
}
