package com.example.moham.roomsqlite.di.modules;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;

import com.example.moham.roomsqlite.data.db.TodoDao;
import com.example.moham.roomsqlite.di.scopes.TodoScope;
import com.example.moham.roomsqlite.viewModel.todo.TodoViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class TodoModule {
    private Application application;

    public TodoModule(Application application) {
        this.application = application;
    }

    @TodoScope
    @Provides
    public Application getApplication() {
        return application;
    }
    @TodoScope
    @Provides
    public ViewModelProvider.Factory getFactory(TodoDao todoDao){
        return new TodoViewModel.TodoViewModelFactory(application,todoDao);
    }
}
