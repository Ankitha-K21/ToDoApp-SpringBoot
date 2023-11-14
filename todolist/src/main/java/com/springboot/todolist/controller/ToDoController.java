package com.springboot.todolist.controller;

import com.springboot.todolist.model.ToDoItem;
import com.springboot.todolist.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
public class ToDoController {
    @Autowired
    private ToDoService todoservice;

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("todo",todoservice.getAllToDoItems());
        return modelAndView;
    }

    @GetMapping("/filterByDue")
    public ModelAndView sortTasksByDueDate(@RequestParam(name = "sort", defaultValue = "ASC") String sortOrder, Model model) {
        List<ToDoItem> tasks = todoservice.filterByDue(todoservice.getAllToDoItems());
        ModelAndView modelAndView = new ModelAndView("index");

        if (sortOrder.equals("DESC")) {
            Collections.reverse(tasks);
        }
        System.out.println(tasks);
        model.addAttribute("todo", tasks);

        return modelAndView;
    }

    @GetMapping("/filterByStatus")
    public ModelAndView sortTasksByStatus() {
        ModelAndView modelAndView = new ModelAndView("index");

        List<ToDoItem> completedTasks = todoservice.findAllByCompleted(true);
        List<ToDoItem> incompleteTasks = todoservice.findAllByCompleted(false);
        incompleteTasks.addAll(completedTasks);
        modelAndView.addObject("todo", incompleteTasks);

        return modelAndView;
    }
}
