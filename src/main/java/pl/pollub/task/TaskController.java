package pl.pollub.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskList taskList;

    @Autowired
    public TaskController(TaskList taskList) {
        this.taskList = taskList;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Task addTask(@RequestBody NewTask newTask) {
        return taskList.add(newTask);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Task> getAllTasks() {
        return taskList.getAllTasks();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public @ResponseBody boolean deleteTask(int id){
        return taskList.deleteById(id);
    }
}