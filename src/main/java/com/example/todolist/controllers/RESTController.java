package com.example.todolist.controllers;

import com.example.todolist.model.Task;
import com.example.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/rest")
public class RESTController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping(value = "/tasks")
    public @ResponseBody
    List<Task> getAllTasks() {
        List<Task> listOfTasks = taskRepository.findAll();
        return listOfTasks;
    }

}
