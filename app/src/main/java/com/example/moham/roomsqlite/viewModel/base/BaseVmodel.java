package com.example.moham.roomsqlite.viewModel.base;

import com.example.moham.roomsqlite.views.BaseView;

public interface BaseVmodel<v extends BaseView> {
    void attachView(v view);
}
