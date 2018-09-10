package com.example.moham.roomsqlite.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.example.moham.roomsqlite.App.MyApp;
import com.example.moham.roomsqlite.R;
import com.example.moham.roomsqlite.data.db.TodoDao;
import com.example.moham.roomsqlite.data.model.Todo;
import com.example.moham.roomsqlite.databinding.TodoLayoutBinding;
import com.example.moham.roomsqlite.di.components.DaggerTodoComponent;
import com.example.moham.roomsqlite.di.components.TodoComponent;
import com.example.moham.roomsqlite.di.modules.TodoModule;
import com.example.moham.roomsqlite.ui.adapter.TodoAdapter;
import com.example.moham.roomsqlite.viewModel.todo.TodoViewModel;
import com.example.moham.roomsqlite.views.TodoView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements TodoAdapter.TodoListener ,TodoView, AddTodoFragment.AddListener {
    private TodoAdapter adapter;
    private TodoViewModel viewModel;
    private TodoLayoutBinding layoutBinding;
    @Inject public TodoDao todoDao;
   @Inject public ViewModelProvider.Factory factory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_layout);
        TodoComponent component= DaggerTodoComponent.builder()
                .appComponent(MyApp.get(this).getAppComponent())
                .todoModule(new TodoModule(getApplication()))
                .build();
        component.inject(this);
        layoutBinding= DataBindingUtil.setContentView(this,R.layout.todo_layout);
        adapter=new TodoAdapter();
        adapter.setListener(this);
        viewModel= ViewModelProviders.of(this,factory).get(TodoViewModel.class);
        viewModel.attachView(this);
        layoutBinding.todoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        layoutBinding.todoRecyclerView.setAdapter(adapter);
        layoutBinding.setTodoVm(viewModel);
        viewModel.getAllData();

    }

    @Override
    public void onClick(Todo item, int pos) {
        try {
            viewModel.delete(item);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadTodos(LiveData<List<Todo>> todoList) {
           todoList.observe(this, e ->{
               Log.d("loadTodos",e.toString());
              adapter.setData(e);
           });
    }

    @Override
    public void add() {
        FragmentManager manager=getSupportFragmentManager();
        AddTodoFragment fragment=AddTodoFragment.getFragment(this);
        fragment.show(manager,"AddTodoFragment");
    }

    @Override
    public void todo(Todo todo) {
       // adapter.addItem(todo);
        Log.d("Todo",todo.toString());
    }
}
