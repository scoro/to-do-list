package pl.pollub.task.data.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.pollub.coWorker.data.repository.CoWorkerRepository;
import pl.pollub.exceptions.EntityNotFoundException;
import pl.pollub.task.web.model.NewTask;
import pl.pollub.task.data.model.Task;
import pl.pollub.task.web.model.UpdateTask;

@Component
public class TaskRepository {

    private final CoWorkerRepository coWorkerRepository;

    private final List<Task> tasks = new ArrayList<>();

    private final AtomicInteger counter = new AtomicInteger();

    @Autowired
    public TaskRepository(CoWorkerRepository coWorkerRepository) {
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

    public List<Task> add(List<NewTask> newTasks) {
        return newTasks.stream()
                .map(newTask -> new Task(generateId(), newTask.getContent(), false, coWorkerRepository.getCoWorkers(newTask.getCoWorkersIds())))
                .collect(Collectors.toList());
    }

    public Task getTask(int id) {
        return tasks.stream().filter(task -> task.getId()==id).findFirst()
                .orElseThrow(() -> {throw new EntityNotFoundException(Task.class.getSimpleName(),id);});
    }

    public List<Task> getTasks(List<Integer> ids) {
        return tasks.stream().filter(task -> ids.contains(task.getId())).collect(Collectors.toList());
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task delete(int id){
        Task taskToDelete=tasks.stream().filter(task -> task.getId()==id).findFirst()
                .orElseThrow(() -> {throw new EntityNotFoundException(Task.class.getSimpleName(),id);});
        tasks.remove(taskToDelete);
        return taskToDelete;
    }

    public List<Task> delete(List<Integer> ids) {
        List<Task> tasksToDelete=tasks.stream().filter(task ->ids.contains(task.getId())).collect(Collectors.toList());
        tasks.removeAll(tasksToDelete);
        return tasksToDelete;
    }

    public void deleteAll() {
        tasks.clear();
    }

    public Task update(UpdateTask updateTask) {
        Task taskToUpdate=tasks.stream().filter(task -> task.getId()==updateTask.getId()).findFirst()
                .orElseThrow(() -> {throw new EntityNotFoundException(Task.class.getSimpleName(),updateTask.getId());});
        taskToUpdate.setContent(updateTask.getContent());
        return taskToUpdate;
    }

    public List<Task> update(List<UpdateTask> updateTasks) {
        List<Integer> updateTasksIds=updateTasks.stream().map(UpdateTask::getId).collect(Collectors.toList());
        return tasks.stream().map(task -> {
            if(updateTasksIds.contains(task.getId()))
                task.setContent(updateTasks.stream()
                        .filter(updateTask -> updateTask.getId()==task.getId()).findFirst()
                        .orElseThrow(() -> {throw new EntityNotFoundException(Task.class.getSimpleName(),task.getId());}).getContent());
            return task;
        }).collect(Collectors.toList());
    }

    public Task switchTaskDone(int id) {
        Task taskToSwitchDoneField=tasks.stream().filter(task -> task.getId()==id).findFirst()
                .orElseThrow(() -> {throw new EntityNotFoundException(Task.class.getSimpleName(),id);});
        taskToSwitchDoneField.setDone(!taskToSwitchDoneField.isDone());
        return taskToSwitchDoneField;
    }

    public List<Task> switchTaskDone(List<Integer> ids) {
        return tasks.stream().filter(task -> ids.contains(task.getId()))
                .map(task -> {task.setDone(!task.isDone());
                return task;
                }).collect(Collectors.toList());
    }

    public Task addCoWorkerToTask(int coWorkerId, int taskId) {
        Task taskToAddCoWorker=tasks.stream().filter(task -> task.getId()==taskId).findFirst()
                .orElseThrow(() -> {throw new EntityNotFoundException(Task.class.getSimpleName(),taskId);});
        taskToAddCoWorker.getCoWorkers().add(coWorkerRepository.getCoWorker(coWorkerId));
        return taskToAddCoWorker;
    }

    public Task addCoWorkersToTask(int taskId, List<Integer> coWorkersIds) {
        Task taskToAddCoWorkers=tasks.stream().filter(task -> task.getId()==taskId).findFirst()
                .orElseThrow(() -> {throw new EntityNotFoundException(Task.class.getSimpleName(),taskId);});
        taskToAddCoWorkers.getCoWorkers().addAll(coWorkerRepository.getCoWorkers(coWorkersIds));
        return taskToAddCoWorkers;
    }

    public Task deleteCoWorkerFromTask(int coWorkerId, int taskId) {
        Task taskToDeleteCoWorker=tasks.stream().filter(task -> task.getId()==taskId).findFirst()
                .orElseThrow(() -> {throw new EntityNotFoundException(Task.class.getSimpleName(),taskId);});
        taskToDeleteCoWorker.getCoWorkers().remove(coWorkerRepository.getCoWorker(coWorkerId));
        return taskToDeleteCoWorker;
    }

    public Task deleteCoWorkersFromTask(int taskId, List<Integer> coWorkersIds) {
        Task taskToDeleteCoWorkers=tasks.stream().filter(task -> task.getId()==taskId).findFirst()
                .orElseThrow(() -> {throw new EntityNotFoundException(Task.class.getSimpleName(),taskId);});
        taskToDeleteCoWorkers.getCoWorkers().removeAll(coWorkerRepository.getCoWorkers(coWorkersIds));
        return taskToDeleteCoWorkers;
    }
}
