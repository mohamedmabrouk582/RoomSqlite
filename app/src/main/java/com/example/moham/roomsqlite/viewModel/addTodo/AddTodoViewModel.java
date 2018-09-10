package com.example.moham.roomsqlite.viewModel.addTodo;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.moham.roomsqlite.data.db.TodoDao;
import com.example.moham.roomsqlite.data.db.TodoDb;
import com.example.moham.roomsqlite.data.model.Todo;
import com.example.moham.roomsqlite.data.model.User;
import com.example.moham.roomsqlite.ui.AddTodoFragment;
import com.example.moham.roomsqlite.viewModel.base.BaseViewModel;
import com.example.moham.roomsqlite.viewModel.todo.TodoViewModel;
import com.example.moham.roomsqlite.views.AddTodoView;

import java.util.Arrays;
import java.util.List;

public class AddTodoViewModel<v extends AddTodoView> extends BaseViewModel<v> implements AddTodoVmodel<v> {
    private ObservableList<String> error=new ObservableArrayList<>();
    private String name,des,catogery;
    private Context context;
    private List<String> list= Arrays.asList("Android","Ios","Kotlin","java");
    private ArrayAdapter<String> adapter;
    public AddTodoViewModel(@NonNull Application application) {
        super(application);
        context=application.getBaseContext();

        setupAdapter();
        rest();
    }



    public ArrayAdapter<String> getAdapter() {
        return adapter;
    }

    public ObservableList<String> getError() {
        return error;
    }

    private void setupAdapter(){
        adapter=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,list);
    }



    public void addTodo(Todo todo){
        new Insert().execute(todo);
    }
    private class Insert extends AsyncTask<Todo,Void,Long>{

        @Override
        protected Long doInBackground(Todo... todos) {
            return TodoDb.getDao(context).insertTodo(todos[0]);
        }

        @Override
        protected void onPostExecute(Long aLong) {
           Log.d("InsertTodo",String.valueOf(aLong));
        }
    }
    private void rest(){
        error.add(0,null);
        error.add(1,null);
    }

    public void close(View view){
        getView().close();
    }

    @BindingAdapter({"OnItemSelectedListener","setAdapter"})
    public static void OnItemSelectedListener(Spinner spinner, AdapterView.OnItemSelectedListener listener,ArrayAdapter<String> adapter){
        spinner.setOnItemSelectedListener(listener);
        spinner.setAdapter(adapter);
    }

    @BindingAdapter({"todoWatcher","todoValid"})
    public static void watcher(EditText editText,TextWatcher watcher,String msg){
        editText.addTextChangedListener(watcher);
        editText.setError(msg);

    }

    @Override
    public void add(View view) {
        rest();
      if (name==null || TextUtils.isEmpty(name)){
          error.add(0,"Name Not Be Empty");
      }else if (des==null || TextUtils.isEmpty(des)){
          error.add(1,"description Not Be Empty");
      }else if (catogery==null || TextUtils.isEmpty(catogery)){
          Toast.makeText(context, "category not be empty", Toast.LENGTH_SHORT).show();
      }else {
          getView().add(new Todo(name,catogery,des,new User(name,Arrays.asList(catogery,name,des))));
      }
    }

    public TextWatcher nameWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
          name=charSequence.toString();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public TextWatcher desWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
          des=charSequence.toString();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };



   public AdapterView.OnItemSelectedListener listener=new AdapterView.OnItemSelectedListener() {
       @Override
       public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            catogery=adapterView.getSelectedItem().toString();
       }

       @Override
       public void onNothingSelected(AdapterView<?> adapterView) {

       }
   };

    public static class AddTodoViewModelFactory implements ViewModelProvider.Factory{
        private Application application;

        public AddTodoViewModelFactory(Application application) {
            this.application = application;
        }

        @NonNull
        @Override
        public AddTodoViewModel create(@NonNull Class modelClass) {
            return new AddTodoViewModel(application);
        }
    }

}
