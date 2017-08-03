package pl.pollub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pollub.model.task.DTO.TaskDTO;
import pl.pollub.model.task.Task;
import pl.pollub.model.task.TaskList;
import pl.pollub.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskList taskList;

    @Autowired
    public TaskController(TaskList taskList) {
        this.taskList = taskList;
    }

    @Autowired
    private TaskService taskService;

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Task addTask(@RequestBody TaskDTO taskDTO) {
        return taskList.add(taskDTO);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO taskDTO) {
        Task createdTask = taskService.createTask(taskDTO);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Task> getAllTasks() {
        return taskList.getAllTasks();
    }

}