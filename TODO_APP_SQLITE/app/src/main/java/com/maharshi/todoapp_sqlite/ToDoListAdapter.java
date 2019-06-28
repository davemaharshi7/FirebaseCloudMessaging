package com.maharshi.todoapp_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ToDoListAdapter {

    private static final String TAG= ToDoListAdapter.class.getSimpleName();

    private static final String DB_NAME="todolist.db";
    private static final int DB_VERSION=2;

    private static final String TABLE_TODO="table_todo";
    private static final String COLUMN_TODO_ID="task_id";
    private static final String COLUMN_TODO = "todo";

    private static final String COLUMN_PLACE="place";


    //create table table_todo(task_id integer primary key, todo text not null);
    //private static String CREATE_TABLE_TODO="CREATE TABLE "+TABLE_TODO+"("+COLUMN_TODO_ID+" INTEGER PRIMARY KEY, "+COLUMN_TODO+" TEXT NOT NULL)";
    private static String CREATE_TABLE_TODO="CREATE TABLE "+TABLE_TODO+"("+COLUMN_TODO_ID+" " +
            "INTEGER PRIMARY KEY, "+COLUMN_TODO+" TEXT NOT NULL,"+COLUMN_PLACE+" TEXT NOT NULL )";

    private Context context;
    private SQLiteDatabase sqLliteDatabase;
    private static ToDoListAdapter toDoListAdapterInstance;

    private ToDoListAdapter(Context context){
        this.context = context;
        sqLliteDatabase = new ToDoListHelper(context,DB_NAME,null,DB_VERSION).getWritableDatabase();
    }

    public static ToDoListAdapter getToDoListAdapterInstance(Context context){
        if(toDoListAdapterInstance == null){
            toDoListAdapterInstance = new ToDoListAdapter(context);
        }

        return toDoListAdapterInstance;
    }

    //CRUD METHODS
    public boolean insert(String toDoItem){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TODO,toDoItem);
        return sqLliteDatabase.insert(TABLE_TODO,null,contentValues) > 0;
    }
    public boolean delete(int taskId){
        return sqLliteDatabase.delete(TABLE_TODO,COLUMN_TODO_ID+" = ?",
                new String[]{String.valueOf(taskId)}) > 0;
    }
    public boolean update(int taskId, String toDoItem){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TODO,toDoItem);
        return sqLliteDatabase.update(TABLE_TODO,contentValues,COLUMN_TODO_ID+ " = ?",
                new String[]{String.valueOf(taskId)}) > 0;
    }
    public List<ToDo> getAllToDo(){
        List<ToDo> mList = new ArrayList<>();
        Cursor cursor = sqLliteDatabase.query(TABLE_TODO,new String[]{COLUMN_TODO_ID,COLUMN_TODO}
        ,null,null,null,null,null);

        if(cursor != null && cursor.getCount() > 0){
            while (cursor.moveToNext()){
                ToDo toDo = new ToDo(cursor.getLong(0),cursor.getString(1));
                mList.add(toDo);

            }
        }
        cursor.close();
        return mList;
    }

    private static class ToDoListHelper extends SQLiteOpenHelper{

        public ToDoListHelper(Context context, String DBname, SQLiteDatabase.CursorFactory factory
                , int version) {
            super(context, DBname, factory, version);
        }

        @Override
        public void onConfigure(SQLiteDatabase db) {
            super.onConfigure(db);
            db.setForeignKeyConstraintsEnabled(true);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_TODO);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
             switch (oldVersion){
                 case 1:
                    db.execSQL("ALTER TABLE "+TABLE_TODO+" ADD COLUMN "+COLUMN_PLACE + " TEXT " );
             }
        }
    }
}
