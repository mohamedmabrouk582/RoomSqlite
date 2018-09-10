package com.example.moham.roomsqlite.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.widget.ArrayAdapter;

import com.example.moham.roomsqlite.R;
import com.example.moham.roomsqlite.data.model.Todo;
import com.example.moham.roomsqlite.databinding.AddTodoViewBinding;
import com.example.moham.roomsqlite.viewModel.addTodo.AddTodoViewModel;
import com.example.moham.roomsqlite.views.AddTodoView;

import java.util.Arrays;
import java.util.List;

public class AddTodoFragment extends BaseDailogFragment implements AddTodoView{
    private AddTodoViewBinding viewBinding;
    private AddTodoViewModel viewModel;
    private ViewModelProvider.Factory factory;
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
     factory=new AddTodoViewModel.AddTodoViewModelFactory(getActivity().getApplication());
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
