package pl.pollub.task.web.controller;

import java.util.List;

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

    @RequestMapping(value="addMany",method = RequestMethod.POST)
    public @ResponseBody List<Task> addTasks(@RequestBody List<NewTask> newTasks) {return taskRepository.add(newTasks);}

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public @ResponseBody Task getTask(@PathVariable int id) { return taskRepository.getTask(id);}

    @RequestMapping(value="getMany",method = RequestMethod.GET)
    public @ResponseBody List<Task> getTasks(@RequestBody List<Integer> ids) {
        return taskRepository.getTasks(ids);
    }

    @RequestMapping(value="getAll",method = RequestMethod.GET)
    public @ResponseBody List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public @ResponseBody Task deleteTask(@PathVariable int id){return taskRepository.delete(id);}

    @RequestMapping(value = "deleteMany", method = RequestMethod.DELETE)
    public @ResponseBody List<Task> deleteTasks(@RequestBody List<Integer> ids){return taskRepository.delete(ids);}

    @RequestMapping(value = "deleteAll", method = RequestMethod.DELETE)
    public @ResponseBody void deleteAllTasks(){
        taskRepository.deleteAll();}

    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody Task updateTaskContent(@RequestBody UpdateTask updateTask){return taskRepository.update(updateTask);}

    @RequestMapping(value = "updateMany", method = RequestMethod.PUT)
    public @ResponseBody List<Task> updateTasksContent(@RequestBody List<UpdateTask> updateTasks){return taskRepository.update(updateTasks);}

    @RequestMapping(value = "switchDone/{id}",method = RequestMethod.PUT)
    public @ResponseBody Task switchTaskDone(@PathVariable int id){return taskRepository.switchTaskDone(id);}

    @RequestMapping(value = "switchDoneMany",method = RequestMethod.PUT)
    public @ResponseBody List<Task> switchTasksDone(@RequestBody List<Integer> ids){return taskRepository.switchTaskDone(ids);}

    @RequestMapping(value= "addCoWorker/{coWorkerId}/toTask/{taskId}")
    public @ResponseBody Task addCoWorkerToTask(@PathVariable int coWorkerId, @PathVariable int taskId){
        return taskRepository.addCoWorkerToTask(coWorkerId,taskId);
    }

    @RequestMapping(value= "addManyCoWorker/{taskId}")
    public @ResponseBody Task addCoWorkersToTask(@PathVariable int taskId, @RequestBody List<Integer> coWorkersIds){
        return taskRepository.addCoWorkersToTask(taskId,coWorkersIds);
    }

    @RequestMapping(value= "deleteCoWorker/{coWorkerId}/fromTask/{taskId}")
    public @ResponseBody Task deleteCoWorkerFromTask(@PathVariable int coWorkerId, @PathVariable int taskId){
        return taskRepository.deleteCoWorkerFromTask(coWorkerId,taskId);
    }

    @RequestMapping(value= "addManyCoWorker/{taskId}")
    public @ResponseBody Task deleteCoWorkersFromTask(@PathVariable int taskId, @RequestBody List<Integer> coWorkersIds){
        return taskRepository.deleteCoWorkersFromTask(taskId,coWorkersIds);
    }
}