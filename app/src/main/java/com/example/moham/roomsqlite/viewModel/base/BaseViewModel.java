package com.example.moham.roomsqlite.viewModel.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.moham.roomsqlite.views.BaseView;

public class BaseViewModel<v extends BaseView> extends AndroidViewModel implements BaseVmodel<v> {
    private v view;
    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void attachView(v view) {
        this.view=view;
    }

    public v getView() {
        return view;
    }
}
