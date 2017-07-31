package pl.pollub.task.data.repository;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.pollub.coWorker.data.repository.CoWorkerRepository;
import pl.pollub.exceptions.ObjectNotFoundException;
import pl.pollub.task.web.model.NewTask;
import pl.pollub.task.data.model.Task;
import pl.pollub.task.web.model.UpdateTask;

@Component
public class TaskRepository {

    private CoWorkerRepository coWorkerRepository;

    private final Set<Task> tasks = new HashSet<>();

    private final AtomicInteger counter = new AtomicInteger();

    @Autowired
    public void setCoWorkerRepository(CoWorkerRepository coWorkerRepository) {
        this.coWorkerRepository = coWorkerRepository;
    }

    private int generateId() {
        return counter.incrementAndGet();
    }

    public Task add(NewTask newTask){
        Task created = new Task(generateId(), newTask.getContent(), false, coWorkerRepository.getCoWorkers(newTask.getCoWorkersIds()));
        tasks.add(created);
        return created;
    }

    public Set<Task> add(Set<NewTask> newTasks) {
        return newTasks.stream()
                .map(newTask -> new Task(generateId(), newTask.getContent(), false, coWorkerRepository.getCoWorkers(newTask.getCoWorkersIds())))
                .collect(Collectors.toSet());
    }

    public Task getTask(int id) {
        return tasks.stream().filter(task -> task.getId()==id).findFirst()
                .orElseThrow(() -> new ObjectNotFoundException(Task.class.getSimpleName(),id));
    }

    public Set<Task> getTasks(Set<Integer> ids) {
        return tasks.stream().filter(task -> ids.contains(task.getId())).collect(Collectors.toSet());
    }

    public Set<Task> getAllTasks() {
        return tasks;
    }

    public Task delete(int id){
        Task taskToDelete=tasks.stream().filter(task -> task.getId()==id).findFirst()
                .orElseThrow(() -> new ObjectNotFoundException(Task.class.getSimpleName(),id));
        taskToDelete.deleteAllCoWorkers();
        tasks.remove(taskToDelete);
        return taskToDelete;
    }

    public Set<Task> delete(Set<Integer> ids) {
        Set<Task> tasksToDelete=tasks.stream().filter(task ->ids.contains(task.getId())).collect(Collectors.toSet());
        tasksToDelete.forEach(Task::deleteAllCoWorkers);
        tasks.removeAll(tasksToDelete);
        return tasksToDelete;
    }

    public void deleteAll() {
        tasks.forEach(Task::deleteAllCoWorkers);
        tasks.clear();
    }

    public Task update(UpdateTask updateTask) {
        Task taskToUpdate=tasks.stream().filter(task -> task.getId()==updateTask.getId()).findFirst()
                .orElseThrow(() -> new ObjectNotFoundException(Task.class.getSimpleName(),updateTask.getId()));
        taskToUpdate.setContent(updateTask.getContent());
        return taskToUpdate;
    }

    public Set<Task> update(Set<UpdateTask> updateTasks) {
        Set<Integer> updateTasksIds=updateTasks.stream().map(UpdateTask::getId).collect(Collectors.toSet());
        return tasks.stream().map(task -> {
            if(updateTasksIds.contains(task.getId()))
                task.setContent(updateTasks.stream()
                        .filter(updateTask -> updateTask.getId()==task.getId()).findFirst()
                        .orElseThrow(() -> new ObjectNotFoundException(Task.class.getSimpleName(),task.getId())).getContent());
            return task;
        }).collect(Collectors.toSet());
    }

    public Task switchTaskDone(int id) {
        Task taskToSwitchDoneField=tasks.stream().filter(task -> task.getId()==id).findFirst()
                .orElseThrow(() -> new ObjectNotFoundException(Task.class.getSimpleName(),id));
        taskToSwitchDoneField.setDone(!taskToSwitchDoneField.isDone());
        return taskToSwitchDoneField;
    }

    public Set<Task> switchTaskDone(Set<Integer> ids) {
        return tasks.stream().filter(task -> ids.contains(task.getId()))
                .map(task -> {task.setDone(!task.isDone());
                return task;
                }).collect(Collectors.toSet());
    }

    public Task addCoWorkerToTask(int coWorkerId, int taskId) {
        Task taskToAddCoWorker=tasks.stream().filter(task -> task.getId()==taskId).findFirst()
                .orElseThrow(() -> new ObjectNotFoundException(Task.class.getSimpleName(),taskId));
        taskToAddCoWorker.addCoWorker(coWorkerRepository.getCoWorker(coWorkerId));
        return taskToAddCoWorker;
    }

    public Task addCoWorkersToTask(int taskId, Set<Integer> coWorkersIds) {
        Task taskToAddCoWorkers=tasks.stream().filter(task -> task.getId()==taskId).findFirst()
                .orElseThrow(() -> new ObjectNotFoundException(Task.class.getSimpleName(),taskId));
        taskToAddCoWorkers.addCoWorkers(coWorkerRepository.getCoWorkers(coWorkersIds));
        return taskToAddCoWorkers;
    }

    public Task deleteCoWorkerFromTask(int coWorkerId, int taskId) {
        Task taskToDeleteCoWorker=tasks.stream().filter(task -> task.getId()==taskId).findFirst()
                .orElseThrow(() -> new ObjectNotFoundException(Task.class.getSimpleName(),taskId));
        taskToDeleteCoWorker.deleteCoWorker(coWorkerRepository.getCoWorker(coWorkerId));
        return taskToDeleteCoWorker;
    }

    public Task deleteCoWorkersFromTask(int taskId, Set<Integer> coWorkersIds) {
        Task taskToDeleteCoWorkers=tasks.stream().filter(task -> task.getId()==taskId).findFirst()
                .orElseThrow(() -> new ObjectNotFoundException(Task.class.getSimpleName(),taskId));
        taskToDeleteCoWorkers.deleteCoWorkers(coWorkerRepository.getCoWorkers(coWorkersIds));
        return taskToDeleteCoWorkers;
    }

    public Set<Task> getAllTaskOfCoWorker(int id){
        return coWorkerRepository.getCoWorker(id).getTasks();
    }
}
