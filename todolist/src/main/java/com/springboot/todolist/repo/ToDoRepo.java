package com.springboot.todolist.repo;

import com.springboot.todolist.model.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepo extends JpaRepository<ToDoItem,Long> {

}
