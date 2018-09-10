package com.example.moham.roomsqlite.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.moham.roomsqlite.R;
import com.example.moham.roomsqlite.data.model.Todo;
import com.example.moham.roomsqlite.databinding.TodoItemViewBinding;
import com.example.moham.roomsqlite.BR;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.Holder> {
    int pos;
    private List<Todo> data;

    public interface TodoListener {
        void onClick(Todo item, int pos);
    }

    private TodoListener listener;

    public TodoAdapter() {
        data = new ArrayList<>();
    }

    public void setListener(TodoListener listener) {
        this.listener = listener;
    }

    public void setData(List<Todo> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void addItem(Todo item) {
        data.add(item);
        // notifyDataSetChanged();
        notifyItemInserted(data.size() - 1);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TodoItemViewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.todo_item_view, parent, false);
        return new Holder(binding);
    }

    public int currentPos() {
        return pos;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        pos = position;
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TodoItemViewBinding itemViewBinding;

        public Holder(TodoItemViewBinding itemViewBinding) {
            super(itemViewBinding.getRoot());
            this.itemViewBinding = itemViewBinding;

        }

        public void bind(Todo product) {
            itemViewBinding.setVariable(BR.todo, product);
            itemViewBinding.executePendingBindings();
            itemViewBinding.getRoot().setOnClickListener((v) ->
                    listener.onClick(product, getAdapterPosition())
            );
        }
    }
}