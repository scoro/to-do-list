package pl.pollub.coWorker.data.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.pollub.coWorker.data.model.CoWorker;
import pl.pollub.coWorker.web.model.NewCoWorker;
import pl.pollub.coWorker.web.model.UpdateCoWorker;
import pl.pollub.exceptions.EntityNotFoundException;
import pl.pollub.task.data.repository.TaskRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
public class CoWorkerRepository {

    private final TaskRepository taskRepository;

    private final Set<CoWorker> coWorkers = new HashSet<>();

    private final AtomicInteger counter = new AtomicInteger();

    @Autowired
    public CoWorkerRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private int generateId() {
        return counter.incrementAndGet();
    }

    public CoWorker add(NewCoWorker newCoWorker) {
        CoWorker coWorker=new CoWorker(generateId(),newCoWorker.getName(),taskRepository.getTasks(newCoWorker.getTasksIds()));
        coWorkers.add(coWorker);
        return coWorker;
    }

    public Set<CoWorker> add(Set<NewCoWorker> newCoWorkers) {
        return newCoWorkers.stream()
                .map(newCoWorker -> new CoWorker(generateId(), newCoWorker.getName(), taskRepository.getTasks(newCoWorker.getTasksIds())))
                .collect(Collectors.toSet());
    }


    public CoWorker getCoWorker(int id) {
        return coWorkers.stream().filter(coWorker -> coWorker.getId()==id)
                .findFirst()
                .orElseThrow(() ->{throw new EntityNotFoundException(CoWorker.class.getSimpleName(),id);});
    }


    public Set<CoWorker> getCoWorkers(Set<Integer> ids) {
        return coWorkers.stream().filter(task -> ids.contains(task.getId())).collect(Collectors.toSet());
    }

    public Set<CoWorker> getAllCoWorkers() {
        return coWorkers;
    }


    public CoWorker delete(int id) {
        CoWorker coWorkerToDelete=coWorkers.stream().filter(coWorker -> coWorker.getId()==id).findFirst()
                .orElseThrow(() ->{throw new EntityNotFoundException(CoWorker.class.getSimpleName(),id);});
        coWorkers.remove(coWorkerToDelete);
        return coWorkerToDelete;
    }

    public Set<CoWorker> delete(Set<Integer> ids) {
        Set<CoWorker> coWorkerToDelete=coWorkers.stream().filter(coWorker ->ids.contains(coWorker.getId())).collect(Collectors.toSet());
        coWorkers.removeAll(coWorkerToDelete);
        return coWorkerToDelete;
    }

    public void deleteAll() {
        coWorkers.clear();
    }

    public CoWorker update(UpdateCoWorker updateCoWorker) {
        CoWorker coWorkerToUpdate=coWorkers.stream().filter(coWorker -> coWorker.getId()==updateCoWorker.getId()).findFirst()
                .orElseThrow(() ->{throw new EntityNotFoundException(CoWorker.class.getSimpleName(),updateCoWorker.getId());});
        coWorkerToUpdate.setName(updateCoWorker.getName());
        return coWorkerToUpdate;
    }

    public Set<CoWorker> update(Set<UpdateCoWorker> updateCoWorkers) {
        Set<Integer> updateCoWorkersIds=updateCoWorkers.stream().map(UpdateCoWorker::getId).collect(Collectors.toSet());
        return coWorkers.stream().map(coWorker -> {
            if(updateCoWorkersIds.contains(coWorker.getId())) {
                updateCoWorkers.stream()
                        .filter(updateCoWorker -> updateCoWorker.getId() == coWorker.getId()).findFirst()
                        .ifPresent(updateCoWorker -> coWorker.setName(updateCoWorker.getName()));
                return coWorker;
            }
            else{
                throw new EntityNotFoundException(CoWorker.class.getSimpleName(),coWorker.getId());
            }
        }).collect(Collectors.toSet());
    }

    public CoWorker addTaskToCoWorker(int coWorkerId, int taskId) {
        CoWorker coWorkerToAddTask=coWorkers.stream().filter(coWorker -> coWorker.getId()==coWorkerId).findFirst()
                .orElseThrow(() ->{throw new EntityNotFoundException(CoWorker.class.getSimpleName(),coWorkerId);});
        coWorkerToAddTask.addTask(taskRepository.getTask(taskId));
        return coWorkerToAddTask;
    }

    public CoWorker addCoWorkersToTask(int coWorkerId, Set<Integer> tasksIds) {
        CoWorker coWorkerToAddTasks=coWorkers.stream().filter(coWorker -> coWorker.getId()==coWorkerId).findFirst()
                .orElseThrow(() ->{throw new EntityNotFoundException(CoWorker.class.getSimpleName(),coWorkerId);});
        coWorkerToAddTasks.addTasks(taskRepository.getTasks(tasksIds));
        return coWorkerToAddTasks;
    }

    public CoWorker deleteCoWorkerFromTask(int coWorkerId, int taskId) {
        CoWorker coWorkerToDeleteTask=coWorkers.stream().filter(coWorker -> coWorker.getId()==coWorkerId).findFirst()
                .orElseThrow(() ->{throw new EntityNotFoundException(CoWorker.class.getSimpleName(),coWorkerId);});
        coWorkerToDeleteTask.deleteTask(taskRepository.getTask(taskId));
        return coWorkerToDeleteTask;
    }

    public CoWorker deleteCoWorkersFromTask(int coWorkerId, Set<Integer> tasksIds) {
        CoWorker coWorkerToDeleteTasks=coWorkers.stream().filter(coWorker -> coWorker.getId()==coWorkerId).findFirst()
                .orElseThrow(() ->{throw new EntityNotFoundException(CoWorker.class.getSimpleName(),coWorkerId);});
        coWorkerToDeleteTasks.deleteTasks(taskRepository.getTasks(tasksIds));
        return coWorkerToDeleteTasks;
    }
}
