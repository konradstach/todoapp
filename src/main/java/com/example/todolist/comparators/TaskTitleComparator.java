package com.example.todolist.comparators;

import com.example.todolist.model.Task;

import java.util.Comparator;

public class TaskTitleComparator implements Comparator<Task> {

    @Override
    public int compare(Task t1, Task t2) {
        return t1.getTitle().compareTo(t2.getTitle());
    }
}