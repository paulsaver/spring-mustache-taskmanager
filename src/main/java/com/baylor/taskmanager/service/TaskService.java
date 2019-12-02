package com.baylor.taskmanager.service;

import com.baylor.taskmanager.model.Task;

import java.util.List;

public interface TaskService {

    List<Task> getAllTasks();

    Task getTaskById(Long id);

    Task saveTask(Task task);

    void deleteTaskById(Long id);
}
