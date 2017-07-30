package pl.pollub.coWorker.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.pollub.coWorker.data.model.CoWorker;
import pl.pollub.coWorker.web.model.NewCoWorker;
import pl.pollub.coWorker.web.model.UpdateCoWorker;
import pl.pollub.coWorker.data.repository.CoWorkerRepository;

import java.util.Set;


@Controller
@RequestMapping("/coWorker")
public class CoWorkerController {

    private final CoWorkerRepository coWorkerRepository;

    @Autowired
    public CoWorkerController(CoWorkerRepository coWorkerRepository) {
        this.coWorkerRepository = coWorkerRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    CoWorker addCoWorker(@RequestBody NewCoWorker newCoWorker) {
        return coWorkerRepository.add(newCoWorker);
    }

    @RequestMapping(value="addMany",method = RequestMethod.POST)
    public @ResponseBody
    Set<CoWorker> addCoWorkers(@RequestBody Set<NewCoWorker> newCoWorkers) {return coWorkerRepository.add(newCoWorkers);}

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public @ResponseBody
    CoWorker getCoWorker(@PathVariable int id) { return coWorkerRepository.getCoWorker(id);}

    @RequestMapping(value="getMany",method = RequestMethod.GET)
    public @ResponseBody Set<CoWorker> getCoWorker(@RequestBody Set<Integer> ids) {
        return coWorkerRepository.getCoWorkers(ids);
    }

    @RequestMapping(value="getAll",method = RequestMethod.GET)
    public @ResponseBody Set<CoWorker> getAllCoWorkers() {
        return coWorkerRepository.getAllCoWorkers();
    }

    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public @ResponseBody CoWorker deleteCoWorker(@PathVariable int id){return coWorkerRepository.delete(id);}

    @RequestMapping(value = "deleteMany", method = RequestMethod.DELETE)
    public @ResponseBody Set<CoWorker> deleteCoWorkers(@RequestBody Set<Integer> ids){return coWorkerRepository.delete(ids);}

    @RequestMapping(value = "deleteMany", method = RequestMethod.DELETE)
    public @ResponseBody void deleteAllCoWorkers(){
        coWorkerRepository.deleteAll();}

    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody CoWorker updateCoWorker(@RequestBody UpdateCoWorker updateCoWorker){return coWorkerRepository.update(updateCoWorker);}

    @RequestMapping(value = "updateMany", method = RequestMethod.PUT)
    public @ResponseBody Set<CoWorker> updateCoWorkers(@RequestBody Set<UpdateCoWorker> updateCoWorker){return coWorkerRepository.update(updateCoWorker);}

    @RequestMapping(value= "addTask/{taskId}/toCoWorker/{coWorkerId}")
    public @ResponseBody
    CoWorker addCoWorkerToTask(@PathVariable int coWorkerId, @PathVariable int taskId){
        return coWorkerRepository.addTaskToCoWorker(coWorkerId,taskId);
    }

    @RequestMapping(value= "addManyTasks/{coWorkerId}")
    public @ResponseBody CoWorker addCoWorkersToTask(@PathVariable int coWorkerId, @RequestBody Set<Integer> tasksIds){
        return coWorkerRepository.addCoWorkersToTask(coWorkerId,tasksIds);
    }

    @RequestMapping(value= "deleteTask/{taskId}/fromCoWorker/{coWorkerId}")
    public @ResponseBody
    CoWorker deleteCoWorkerFromTask(@PathVariable int coWorkerId, @PathVariable int taskId){
        return coWorkerRepository.deleteCoWorkerFromTask(coWorkerId,taskId);
    }

    @RequestMapping(value= "addManyTasks/{coWorkerId}")
    public @ResponseBody CoWorker deleteCoWorkersFromTask(@PathVariable int coWorkerId, @RequestBody Set<Integer> tasksIds){
        return coWorkerRepository.deleteCoWorkersFromTask(coWorkerId,tasksIds);
    }
}
