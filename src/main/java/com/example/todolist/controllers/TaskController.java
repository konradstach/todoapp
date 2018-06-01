package com.example.todolist.controllers;

import com.example.todolist.model.Task;
import com.example.todolist.model.User;
import com.example.todolist.repository.TaskRepository;
import com.example.todolist.repository.UserRepository;
import com.example.todolist.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping(path = "/todoapp/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Service service;

    @GetMapping(value = "")
    public String getTaskList(Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("numberOfTasks", service.getNumberOfTasks());
        model.addAttribute("tasksByDate", service.createListOfTasksSortedByDate(userRepository.findUserIdByUsername(request.getRemoteUser())));
        model.addAttribute("tasksByTitle", service.createListOfTasksSortedByTitle(userRepository.findUserIdByUsername(request.getRemoteUser())));
        return "task_list";
    }

    @GetMapping(value = "/{taskId}")
    public String getEditTaskPage(@PathVariable int taskId, Model model, HttpServletRequest request) {
        Task task = taskRepository.findTaskByTaskId(taskId);
        model.addAttribute("task", task);
        model.addAttribute("user", request.getRemoteUser());
        return "edit_task";
    }

    @PostMapping(value = "/{taskId}")
    public String editTask(@ModelAttribute @Valid Task task,
                           Errors errors,
                           Model model,
                           @PathVariable("taskId") int taskId,
                           @RequestParam("title") String title,
                           @RequestParam("description") String description,
                           @RequestParam("date") String date) {

        if (errors.hasErrors()) {
            model.addAttribute("validationError");
            return "redirect:/todoapp/tasks/{taskId}";
        }

        task = taskRepository.findTaskByTaskId(taskId);
        task.setTitle(title);
        task.setDescription(description);
        task.setDateFromString(date);
        taskRepository.save(task);
        return "redirect:/todoapp/tasks";
    }

    @GetMapping(value = "/new")
    public String getAddTaskPage(Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("task", new Task());
        return "add_task";
    }

    @PostMapping(value = "/new")
    public String createTask(
            @ModelAttribute @Valid Task task,
            Errors errors,
            Model model,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            @RequestParam("username") String username) {

        if (errors.hasErrors()) {
            model.addAttribute("validationError");
            return "redirect:/todoapp/tasks/new";
        }

        User user = userRepository.findOne(userRepository.findUserIdByUsername(username));
        task = new Task(user, title, description, date, false);
        taskRepository.save(task);
        return "redirect:/todoapp/tasks";
    }

    @PostMapping(value = "/completed")
    public ModelAndView setCompleted(
            @RequestParam("taskId") int taskId,
            @RequestParam("isCompleted") boolean isCompleted) {

        Task task = taskRepository.findTaskByTaskId(taskId);
        task.setCompleted(isCompleted);
        taskRepository.save(task);
        return new ModelAndView("redirect:/todoapp/tasks");
    }

    @PostMapping(value = "/{taskId}/delete")
    public String editTask(@PathVariable("taskId") int taskId) {
        taskRepository.deleteByTaskId(taskId);
        return "redirect:/todoapp/tasks";
    }

}
