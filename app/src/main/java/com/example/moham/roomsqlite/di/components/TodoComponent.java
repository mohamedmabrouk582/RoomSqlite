package com.example.moham.roomsqlite.di.components;

import com.example.moham.roomsqlite.di.modules.TodoModule;
import com.example.moham.roomsqlite.di.scopes.TodoScope;
import com.example.moham.roomsqlite.ui.MainActivity;

import dagger.Component;

@TodoScope
@Component(dependencies = AppComponent.class,modules = TodoModule.class)
public interface TodoComponent {
    void inject(MainActivity activity);
}
