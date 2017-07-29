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

    @RequestMapping(value="addMany",method = RequestMethod.POST)
    public @ResponseBody List<Task> addTasks(@RequestBody List<NewTask> newTasks) {return taskList.add(newTasks);}

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public @ResponseBody Task getTask(@PathVariable int id) { return taskList.getTask(id);}

    @RequestMapping(value="getAll",method = RequestMethod.GET)
    public @ResponseBody List<Task> getTasks(@RequestBody List<Integer> ids) {
        return taskList.getTasks(ids);
    }

    @RequestMapping(value="getAll",method = RequestMethod.GET)
    public @ResponseBody List<Task> getAllTasks() {
        return taskList.getAllTasks();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public @ResponseBody Task deleteTask(@RequestBody int id){return taskList.delete(id);}

    @RequestMapping(value = "deleteMany", method = RequestMethod.DELETE)
    public @ResponseBody List<Task> deleteTasks(@RequestBody List<Integer> ids){return taskList.delete(ids);}

    @RequestMapping(value = "deleteMany", method = RequestMethod.DELETE)
    public @ResponseBody void deleteAllTasks(){taskList.deleteAll();}

    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody Task updateTask(@RequestBody UpdateTask updateTask){return taskList.update(updateTask);}

    @RequestMapping(value = "updateMany", method = RequestMethod.PUT)
    public @ResponseBody List<Task> updateTasks(@RequestBody List<UpdateTask> updateTasks){return taskList.update(updateTasks);}

    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody Task switchTaskDone(@RequestBody int id){return taskList.switchTaskDone(id);}

    @RequestMapping(value = "switchDoneMany",method = RequestMethod.PUT)
    public @ResponseBody List<Task> switchTasksDone(@RequestBody List<Integer> ids){return taskList.switchTaskDone(ids);}
}