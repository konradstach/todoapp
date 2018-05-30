package com.example.todolist.service;

import com.example.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
@org.springframework.stereotype.Service("service")
public class Service {

    @Autowired
    private TaskRepository taskRepository;

    public int getNumberOfTasks() {
        return taskRepository.findAll().size();
    }
}
