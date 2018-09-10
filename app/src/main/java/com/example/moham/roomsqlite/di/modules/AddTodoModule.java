package com.example.moham.roomsqlite.di.modules;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;

import com.example.moham.roomsqlite.data.db.TodoDao;
import com.example.moham.roomsqlite.di.scopes.AddTodoScope;
import com.example.moham.roomsqlite.viewModel.addTodo.AddTodoViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class AddTodoModule {
    private Application application;

    public AddTodoModule(Application application) {
        this.application = application;
    }

    @AddTodoScope
    @Provides
    public Application getApplication() {
        return application;
    }

    @AddTodoScope
    @Provides
    public ViewModelProvider.Factory getFactory(TodoDao todoDao){
        return new AddTodoViewModel.AddTodoViewModelFactory(application,todoDao);
    }
}
