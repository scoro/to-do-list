package pl.pollub.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @RequestMapping(
            method = RequestMethod.POST)
    public ResponseEntity<Task> addTask(@RequestBody NewTask newTask) {
        return new ResponseEntity<Task>(taskService.saveTask(newTask),HttpStatus.OK);
    }

    @RequestMapping(
            value ="/{id}",
            method = RequestMethod.GET)
    public ResponseEntity<Task> getTask(@PathVariable("id") int id) {
        Task task = taskService.getTaskById(id);
        return task != null ? new ResponseEntity<Task>(taskService.getTaskById(id), HttpStatus.OK) : new ResponseEntity<Task>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(
            method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getAllTasks() {
        return new ResponseEntity<List<Task>>(taskService.getAllTasks(),HttpStatus.OK);
    }

    @RequestMapping(
            value ="/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity deleteTask(@PathVariable("id") int id){
        return taskService.deleteById(id) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}