package com.maharshi.todoapp_sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText newItem, itemId, itemName;
    private Button add,update,delete;
    private TextView textView;
    private ToDoListAdapter toDoListAdapter;
    private ToDoRecyclerAdapter recyclerAdapter;
    private List<ToDo> todos;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newItem = findViewById(R.id.toDoItemNew);
        itemId = findViewById(R.id.editTextItemID);
        itemName = findViewById(R.id.editTextItem);
        add = findViewById(R.id.add);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        recyclerView = findViewById(R.id.recyclerView);

        //Some Layout Manager
        linearLayoutManager = new LinearLayoutManager(this);
//        gridLayoutManager = new GridLayoutManager(this,2);
//        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int i) {
//                return i%3==0?2:1;
//            }
//        });
//        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,
//                StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager );

        toDoListAdapter = ToDoListAdapter.getToDoListAdapterInstance(this);
        todos = toDoListAdapter.getAllToDo();
        textView = findViewById(R.id.cardID);
        add.setOnClickListener(this);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setNewList();
    }

    private void setNewList(){
        todos = toDoListAdapter.getAllToDo();
        recyclerAdapter = new ToDoRecyclerAdapter(this,todos);
        recyclerView.setAdapter(recyclerAdapter);
        //textView.setText(getToDoString());

    }
//    private String getToDoString(){
//        todos = toDoListAdapter.getAllToDo();
//        if(todos.size() > 0 && todos != null){
//            StringBuilder sb = new StringBuilder("");
//            for(ToDo toDo:todos){
//                sb.append(toDo.getId()+", "+toDo.getToDo()+"\n");
//            }
//            return sb.toString();
//        }
//        else {
//            return "No TO DOs";
//        }
//    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch (i){
            case R.id.add:
                toDoListAdapter.insert(newItem.getText().toString());
                setNewList();
                recyclerView.scrollToPosition(todos.size()-1);
                break;
            case R.id.delete:
                toDoListAdapter.delete(Integer.parseInt(itemId.getText().toString()));
                setNewList();
                break;
            case R.id.update:
                int id = Integer.parseInt(itemId.getText().toString());
                String toDo = itemName.getText().toString();
                toDoListAdapter.update(id,toDo);
                setNewList();
                break;

        }

    }
}
