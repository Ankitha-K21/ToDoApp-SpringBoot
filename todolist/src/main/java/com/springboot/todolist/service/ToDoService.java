package com.springboot.todolist.service;

import com.springboot.todolist.model.ToDoItem;
import com.springboot.todolist.repo.ToDoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoService {
    @Autowired
    ToDoRepo todorepo;

    public List<ToDoItem> getAllToDoItems(){
        return new ArrayList<>(todorepo.findAll());
    }

    public List<ToDoItem> filterByDue(List<ToDoItem> tasks){
        tasks.sort((task1, task2) -> task1.getDueDate().compareTo(task2.getDueDate()));
        return tasks;

    }

    public List<ToDoItem> findAllByCompleted(boolean status) {
        List<ToDoItem> tasks = new ArrayList<>();
        List<ToDoItem> allTasks = todorepo.findAll();

        for (ToDoItem task : allTasks) {
            if (task.isStatus() == status) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    public ToDoItem getToDoItemById(Long id){
        return todorepo.findById(id).get();
    }

    public boolean saveOrUpdate(ToDoItem todo){
        ToDoItem updatedObj = todorepo.save(todo);
        return getToDoItemById(updatedObj.getId()) != null;
    }

    public void deleteToDoItem(Long id){
        todorepo.deleteById(id);
    }
}
