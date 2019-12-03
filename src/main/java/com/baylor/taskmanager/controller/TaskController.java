package com.baylor.taskmanager.controller;

import com.baylor.taskmanager.model.Task;
import com.baylor.taskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/")
    public String getAllTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "allTasks";
    }

    @GetMapping("/task/{id}")
    public String getTask(@PathVariable Long id, Model model) {
        model.addAttribute("task", taskService.getTaskById(id));
        model.addAttribute("allowDelete", false);
        return "task";
    }

    @GetMapping("/task/{id}/delete")
    public String showDeleteTask(@PathVariable Long id, Model model) {
        model.addAttribute("task", taskService.getTaskById(id));
        model.addAttribute("allowDelete", true);
        return "task";
    }

    @PostMapping("/task/{id}/delete")
    public String deleteTaskById(@PathVariable Long id, Model model) {
        taskService.deleteTaskById(id);
        return "redirect:/";
    }

    @GetMapping("/task/add")
    public String showAddTask(Model model) {
        Task task = new Task();
        model.addAttribute("add", true);
        model.addAttribute("task", task);
        return "editTask";
    }

    @PostMapping("/task/add")
    public String addTask(Model model, @ModelAttribute("task") Task task) {
        Task newTask = taskService.saveTask(task);
        return "redirect:/task/" + newTask.getId();
    }
}
