package pl.pollub.task.web.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.pollub.task.web.model.UpdateTask;
import pl.pollub.task.web.model.NewTask;
import pl.pollub.task.data.model.Task;
import pl.pollub.task.data.repository.TaskRepository;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    Task addTask(@RequestBody NewTask newTask) {
        return taskRepository.add(newTask);
    }

    @RequestMapping(value="add",method = RequestMethod.POST)
    public @ResponseBody
    Set<Task> addTasks(@RequestBody Set<NewTask> newTasks) {return taskRepository.add(newTasks);}

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public @ResponseBody Task getTask(@PathVariable int id) { return taskRepository.getTask(id);}

    @RequestMapping(value="get",method = RequestMethod.GET)
    public @ResponseBody Set<Task> getTasks(@RequestBody Set<Integer> ids) {
        return taskRepository.getTasks(ids);
    }

    @RequestMapping(value="getAll",method = RequestMethod.GET)
    public @ResponseBody Set<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public @ResponseBody Task deleteTask(@PathVariable int id){return taskRepository.delete(id);}

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public @ResponseBody Set<Task> deleteTasks(@RequestBody Set<Integer> ids){return taskRepository.delete(ids);}

    @RequestMapping(value = "deleteAll", method = RequestMethod.DELETE)
    public @ResponseBody void deleteAllTasks(){
        taskRepository.deleteAll();}

    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody Task updateTaskContent(@RequestBody UpdateTask updateTask){return taskRepository.update(updateTask);}

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public @ResponseBody Set<Task> updateTasksContent(@RequestBody Set<UpdateTask> updateTasks){return taskRepository.update(updateTasks);}

    @RequestMapping(value = "switchDone/{id}",method = RequestMethod.PUT)
    public @ResponseBody Task switchTaskDone(@PathVariable int id){return taskRepository.switchTaskDone(id);}

    @RequestMapping(value = "switchDoneTasks",method = RequestMethod.PUT)
    public @ResponseBody Set<Task> switchTasksDone(@RequestBody Set<Integer> ids){return taskRepository.switchTaskDone(ids);}

    @RequestMapping(value= "addCoWorker/{coWorkerId}/toTask/{taskId}", method = RequestMethod.PUT)
    public @ResponseBody Task addCoWorkerToTask(@PathVariable int coWorkerId, @PathVariable int taskId){
        return taskRepository.addCoWorkerToTask(coWorkerId,taskId);
    }

    @RequestMapping(value= "addCoWorkers/{taskId}", method = RequestMethod.PUT)
    public @ResponseBody Task addCoWorkersToTask(@PathVariable int taskId, @RequestBody Set<Integer> coWorkersIds){
        return taskRepository.addCoWorkersToTask(taskId,coWorkersIds);
    }

    @RequestMapping(value= "deleteCoWorkers/{taskId}", method = RequestMethod.PUT)
    public @ResponseBody Task deleteCoWorkersFromTask(@PathVariable int taskId, @RequestBody Set<Integer> coWorkersIds){
        return taskRepository.deleteCoWorkersFromTask(taskId,coWorkersIds);
    }

    @RequestMapping(value= "deleteCoWorker/{coWorkerId}/fromTask/{taskId}", method = RequestMethod.PUT)
    public @ResponseBody Task deleteCoWorkerFromTask(@PathVariable int coWorkerId, @PathVariable int taskId){
        return taskRepository.deleteCoWorkerFromTask(coWorkerId,taskId);
    }
}