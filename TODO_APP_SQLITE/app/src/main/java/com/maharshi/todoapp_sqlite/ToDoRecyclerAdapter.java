package com.maharshi.todoapp_sqlite;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ToDoRecyclerAdapter extends RecyclerView.Adapter<ToDoRecyclerAdapter.ToDoViewHolder> {

    private Context context;
    private List<ToDo> toDoList;

    public ToDoRecyclerAdapter(Context context, List<ToDo> toDoList) {
        this.context = context;
        this.toDoList = toDoList;
    }

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_view,viewGroup,false);
        final ToDoViewHolder toDoViewHolder = new ToDoViewHolder(view);
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position = toDoViewHolder.getAdapterPosition();
                Toast.makeText(context,"Item At Position "+position+ "deleted.",
                        Toast.LENGTH_SHORT).show();
                toDoList.remove(position);
                //TODO:PLEASE PERFORM DATABASE OPERATION
                notifyDataSetChanged();
                return true;
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = toDoViewHolder.getAdapterPosition();
                Toast.makeText(context,"CLICKED: "+position,
                        Toast.LENGTH_SHORT).show();
            }
        });
        return toDoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder toDoViewHolder, int i) {
        ToDo t = toDoList.get(i);
        toDoViewHolder.name.setText(t.getToDo());
        toDoViewHolder.id.setText(String.valueOf(t.getId()));

    }

    @Override
    public int getItemCount() {
        return toDoList.size();
    }

    class ToDoViewHolder extends RecyclerView.ViewHolder{
        TextView name,id;
        public ToDoViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cardName);
            id = itemView.findViewById(R.id.cardID);
        }
    }
}
