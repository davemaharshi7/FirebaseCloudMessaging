package com.maharshi.todoapp_sqlite;

public class ToDo {
    private long id;
    private String toDo;

    public ToDo() {
        super();
    }

    public ToDo(long id, String toDo) {
        this.id = id;
        this.toDo = toDo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToDo() {
        return toDo;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }
}
