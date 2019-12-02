package com.baylor.taskmanager.service;

import com.baylor.taskmanager.model.Task;
import com.baylor.taskmanager.repo.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;

    @PostConstruct
    public void someData(){
        taskRepo.save(new Task(null, "Name 1", "Desc 1", LocalDate.now(), LocalTime.now(), "Contact 1"));
        taskRepo.save(new Task(null, "Name 2", "Desc 2", LocalDate.now(), LocalTime.now(), "Contact 2"));
        taskRepo.save(new Task(null, "Name 3", "Desc 3", LocalDate.now(), LocalTime.now(), "Contact 3"));
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepo.findById(id).orElse(new Task());
    }

    @Override
    @Transactional
    public Task saveTask(Task task) {
        return taskRepo.save(task);
    }

    @Override
    @Transactional
    public void deleteTaskById(Long id) {
        taskRepo.deleteById(id);
    }
}