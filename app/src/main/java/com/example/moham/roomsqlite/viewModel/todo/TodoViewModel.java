package com.example.moham.roomsqlite.viewModel.todo;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.moham.roomsqlite.data.db.TodoDao;
import com.example.moham.roomsqlite.data.db.TodoDb;
import com.example.moham.roomsqlite.data.model.Todo;
import com.example.moham.roomsqlite.viewModel.base.BaseViewModel;
import com.example.moham.roomsqlite.views.TodoView;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TodoViewModel<v extends TodoView> extends BaseViewModel<v> implements TodoVmodel<v> {
    private Context mContext;

    private ArrayAdapter<String> adapter;
    private List<String> list= Arrays.asList("Android","Ios","Kotlin","java");

    public TodoViewModel(@NonNull Application application) {
        super(application);
        mContext=application.getApplicationContext();
        adapter=new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,list);
    }

    public ArrayAdapter<String> getAdapter() {
        return adapter;
    }

    @BindingAdapter({"ItemSelectedListener","setCatAdapter"})
    public static void ItemSelectedListener(Spinner spinner,AdapterView.OnItemSelectedListener listener,ArrayAdapter<String> adapter){
        spinner.setOnItemSelectedListener(listener);
        spinner.setAdapter(adapter);
    }

    public AdapterView.OnItemSelectedListener listener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            catList(adapterView.getSelectedItem().toString());
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    public void catList(String s){
        getView().loadTodos(TodoDb.getDao(mContext).fetchTodoListByCategory(s));
    }

    @Override
    public void addAction(View view) {
      getView().add();
    }

    public void getAllData(){
         getView().loadTodos(TodoDb.getDao(mContext).fetchAllTodos());
    }

    public Integer delete(Todo todo) throws ExecutionException, InterruptedException {
        return new Delete().execute(todo).get();
    }

    private class Delete extends AsyncTask<Todo,Void,Integer>{

        @Override
        protected Integer doInBackground(Todo... todos) {
            return TodoDb.getDao(mContext).deleteTodo(todos[0]);
        }
    }

    public static class TodoViewModelFactory implements ViewModelProvider.Factory{
        private Application application;

        public TodoViewModelFactory(Application application) {
            this.application = application;
        }

        @NonNull
        @Override
        public  TodoViewModel create(@NonNull Class modelClass) {
            return new TodoViewModel(application);
        }
    }
}
