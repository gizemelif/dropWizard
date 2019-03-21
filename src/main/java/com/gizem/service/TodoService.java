package com.gizem.service;
import com.gizem.api.TodoItem;
import com.gizem.db.TodoItemDao;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TodoService {

    private TodoItemDao todoItemDao;

    public TodoService(TodoItemDao todoItemDao) {
        this.todoItemDao = todoItemDao;
    }

    public TodoItem add(TodoItem todoItem) {
        return todoItemDao.persist(todoItem);
    }

    public List<TodoItem> getAll() {
        return todoItemDao.findAll();
    }

    public Integer deleteById(int id) {
        return todoItemDao.deleteById(id);
    }
    public Integer update(int id,String newName){
         return todoItemDao.update(id,newName);
    }
    public Integer updateItem(int id, String newName){
        return todoItemDao.updateItem(id,newName);
    }
    public Integer updateProcess(int id,String newName){
        return todoItemDao.updateProcess(id,newName);
    }

}
