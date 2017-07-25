package pl.pollub.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskList taskList;

    @Autowired
    public TaskController(TaskList taskList) {
        this.taskList = taskList;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Task addTask(@RequestBody NewTask newTask) {
        return taskList.add(newTask);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Task> getAllTasks() {
        return taskList.getAllTasks();
    }

    @RequestMapping(
            value ="/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity deleteTask(@PathVariable("id") int id){
        return taskList.deleteById(id) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_EXTENDED);
    }
}