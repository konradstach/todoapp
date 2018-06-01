package com.example.todolist;

import com.example.todolist.model.Task;
import com.example.todolist.model.User;
import com.example.todolist.repository.TaskRepository;
import com.example.todolist.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskCRUDTests {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    @Rollback
    public void saveTest() throws Exception {

        User user = userRepository.findAll().get(0);
        Task task = new Task(user, "task_title", "descrption", new Date(), true);
        taskRepository.save(task);

        //get recently added task
        Task recentlyAddedTask = taskRepository.findTop1ByOrderByTaskIdDesc();

        Assert.assertEquals(recentlyAddedTask, task);

    }

    @Test
    @Transactional
    @Rollback
    public void deleteTest() throws Exception {

        int recentlyAddedTaskId = taskRepository.findTop1ByOrderByTaskIdDesc().getTaskId();
        taskRepository.deleteByTaskId(recentlyAddedTaskId);
        Task deletedTask = taskRepository.findTaskByTaskId(recentlyAddedTaskId);


        Assert.assertNull("Object should not exist", deletedTask);
    }

    @Test
    @Transactional
    @Rollback
    public void updateTest() throws Exception {

        Task recentlyAddedTask = taskRepository.findTop1ByOrderByTaskIdDesc();
        int recentlyAddedTaskId = recentlyAddedTask.getTaskId();

        recentlyAddedTask.setTitle("updated title");
        recentlyAddedTask.setDescription("updated description");
        recentlyAddedTask.setDate(new Date(1018021774496l));
        recentlyAddedTask.setCompleted(true);
        taskRepository.save(recentlyAddedTask);

        Task updatedTask = taskRepository.findTaskByTaskId(recentlyAddedTaskId);

        Assert.assertEquals(recentlyAddedTask.getTitle(), updatedTask.getTitle());
        Assert.assertEquals(recentlyAddedTask.getDescription(), updatedTask.getDescription());
        Assert.assertEquals(recentlyAddedTask.getDate(), updatedTask.getDate());
        Assert.assertEquals(recentlyAddedTask.isCompleted(), updatedTask.isCompleted());

    }
}
