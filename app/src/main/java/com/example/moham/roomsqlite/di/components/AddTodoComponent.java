package com.example.moham.roomsqlite.di.components;

import com.example.moham.roomsqlite.di.modules.AddTodoModule;
import com.example.moham.roomsqlite.di.scopes.AddTodoScope;
import com.example.moham.roomsqlite.ui.AddTodoFragment;

import dagger.Component;

@AddTodoScope
@Component(dependencies = AppComponent.class,modules = AddTodoModule.class)
public interface AddTodoComponent {
    void inject(AddTodoFragment fragment);
}
