package com.example.todolist.service;

import com.example.todolist.comparators.TaskDateComparator;
import com.example.todolist.comparators.TaskTitleComparator;
import com.example.todolist.model.Task;
import com.example.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;

import java.util.Collections;
import java.util.List;

@PropertySource("classpath:application.properties")
@org.springframework.stereotype.Service("service")
public class Service {

    @Autowired
    private TaskRepository taskRepository;

    public int getNumberOfTasks() {
        return taskRepository.findAll().size();
    }


    public List<Task> createListOfTasksSortedByDate(int userId) {

        List<Task> listOfTasks = taskRepository.findTasksByUserId(userId);
        Collections.sort(listOfTasks, (new TaskDateComparator()));
        return listOfTasks;
    }

    public List<Task> createListOfTasksSortedByTitle(int userId) {

        List<Task> listOfTasks = taskRepository.findTasksByUserId(userId);
        Collections.sort(listOfTasks, (new TaskTitleComparator()));
        return listOfTasks;
    }
}
