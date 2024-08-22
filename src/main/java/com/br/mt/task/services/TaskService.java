package com.br.mt.task.services;

import com.br.mt.task.model.Task;
import com.br.mt.task.repositories.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }
    public Task findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    public Task update(Long id , Task taskreq) {
        Task task_db = findById(id);
        BeanUtils.copyProperties(taskreq, task_db, "id");
        return taskRepository.save(task_db);
    }
    public Task updateStatusCompleted(Long id, boolean status) {
        Task task_db = findById(id);
        task_db.setCompleted(status);
        return taskRepository.save(task_db);
    }

}
