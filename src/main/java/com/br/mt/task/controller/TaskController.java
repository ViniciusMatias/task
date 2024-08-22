package com.br.mt.task.controller;

import com.br.mt.task.model.Task;
import com.br.mt.task.model.TaskDTO;
import com.br.mt.task.services.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/tasks")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody TaskDTO task){
        Task taskreq = task.toTask();
        return new ResponseEntity<>(this.taskService.save(taskreq),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> findAll(){
        List<Task> tasks = this.taskService.findAll();
        return new ResponseEntity(tasks, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        taskService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody TaskDTO task){
        Task tasksave = taskService.update(id, task.toTask());
        return new ResponseEntity<>(tasksave,HttpStatus.OK);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Task> updateStatus(@PathVariable Long id, @RequestBody Boolean status){
        Task tasksave = taskService.updateStatusCompleted(id, status);
        return new ResponseEntity<>(tasksave,HttpStatus.OK);
    }
}
