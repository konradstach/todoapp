package com.example.todolist;

import com.example.todolist.model.Task;
import com.example.todolist.model.User;
import com.example.todolist.repository.TaskRepository;
import com.example.todolist.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskValidationTests {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Test(expected = ConstraintViolationException.class)
    @Transactional
    @Rollback
    public void titleTooShortShouldThrowConstraintViolationException() throws Exception {

        User user = userRepository.findAll().get(0);
        Task task = new Task(user, "abc", "descrption", new Date(), true);
        taskRepository.save(task);
    }


    @Test(expected = ConstraintViolationException.class)
    @Transactional
    @Rollback
    public void  dateNullShouldThrowConstraintViolationException() throws Exception{

        User user = userRepository.findAll().get(0);
        Task task = new Task(user, "title", "descrption", null, true);
        taskRepository.save(task);
    }
}