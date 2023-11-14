package com.springboot.todolist.controller;

import com.springboot.todolist.model.ToDoItem;
import com.springboot.todolist.service.ToDoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ToDoFormController {
    @Autowired
    private ToDoService toDoService;

    @GetMapping("/create-todo")
    public String showForm(ToDoItem toDoItem, org.springframework.ui.Model model){
        model.addAttribute("todo", new ToDoItem());
        return "create-todo-item";
    }

    @PostMapping("/todo")
    public ModelAndView createTodo(@Valid ToDoItem toDoItem){
        ToDoItem item = new ToDoItem();
        item.setTitle(toDoItem.getTitle());
        item.setDescription(toDoItem.getDescription());
        item.setDueDate(toDoItem.getDueDate());
        item.setStatus(false);

        toDoService.saveOrUpdate(toDoItem);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/delete/{id}")
    public String deleteToDoItem(@PathVariable("id") Long id){
        ToDoItem toDoItem = toDoService.getToDoItemById(id);

        toDoService.deleteToDoItem(toDoItem.getId());
        return "redirect:/";
    }

    @GetMapping("/completed/{id}")
    public String updateToDoItem(@PathVariable("id") Long id){
        ToDoItem toDoItem = toDoService.getToDoItemById(id);
        toDoItem.setStatus(true);
        toDoService.saveOrUpdate(toDoItem);

        return "redirect:/";
    }
}
