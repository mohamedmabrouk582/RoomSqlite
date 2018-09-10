package com.example.moham.roomsqlite.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.widget.ArrayAdapter;

import com.example.moham.roomsqlite.App.MyApp;
import com.example.moham.roomsqlite.R;
import com.example.moham.roomsqlite.data.db.TodoDao;
import com.example.moham.roomsqlite.data.model.Todo;
import com.example.moham.roomsqlite.databinding.AddTodoViewBinding;
import com.example.moham.roomsqlite.di.components.AddTodoComponent;
import com.example.moham.roomsqlite.di.components.DaggerAddTodoComponent;
import com.example.moham.roomsqlite.di.modules.AddTodoModule;
import com.example.moham.roomsqlite.viewModel.addTodo.AddTodoViewModel;
import com.example.moham.roomsqlite.views.AddTodoView;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class AddTodoFragment extends BaseDailogFragment implements AddTodoView{
    private AddTodoViewBinding viewBinding;
    private AddTodoViewModel viewModel;
    @Inject
    public TodoDao todoDao;
    @Inject
    public ViewModelProvider.Factory factory;
    public interface AddListener {
        void todo(Todo  todo);
    }

    private static AddListener listener;
    @Override
    public int setContentView() {
        return R.layout.add_todo_view;
    }

    public static AddTodoFragment getFragment(AddListener addListener){
        listener=addListener;
        return new AddTodoFragment();
    }

    @Override
    public void iniViews() {
     viewBinding= DataBindingUtil.bind(view);
     AddTodoComponent component= DaggerAddTodoComponent.builder()
             .appComponent(MyApp.get(getActivity()).getAppComponent())
             .addTodoModule(new AddTodoModule(getActivity().getApplication()))
             .build();
     component.inject(this);
     viewModel= ViewModelProviders.of(this,factory).get(AddTodoViewModel.class);
     viewModel.attachView(this);
     viewBinding.setAdd(viewModel);
    }

    @Override
    public void add(Todo todo) {
        listener.todo(todo);
        viewModel.addTodo(todo);
    }

    @Override
    public void close() {
        dismiss();
    }
}
