package pl.pollub.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class TaskList {

    private final List<Task> tasks = new ArrayList<>();

    private final AtomicInteger counter = new AtomicInteger();

    private int generateId() {
        return counter.incrementAndGet();
    }

    public Task add(NewTask newTask){
        Task created = new Task(generateId(), newTask.getContent(), false);
        tasks.add(created);
        return created;
    }

    public List<Task> add(List<NewTask> newTasks) {
        return newTasks.stream()
                .map(newTask -> new Task(generateId(), newTask.getContent(), false))
                .collect(Collectors.toList());
    }

    public Task getTask(int id) {
        return tasks.stream().filter(task -> task.getId()==id).findFirst().get();
    }

    public List<Task> getTasks(List<Integer> ids) {
        return tasks.stream().filter(task -> ids.contains(task.getId())).collect(Collectors.toList());
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task delete(int id){
        Task taskToDelete=tasks.stream().filter(task -> task.getId()==id).findFirst().get();
        tasks.remove(taskToDelete);
        return taskToDelete;
    }

    public List<Task> delete(List<Integer> ids) {
        List<Task> tasksToDelete=tasks.stream().filter(task ->ids.contains(task.getId())).collect(Collectors.toList());
        tasks.removeAll(tasksToDelete);
        return tasksToDelete;
    }

    public void deleteAll() {
        tasks.removeAll(tasks);
    }

    public Task update(UpdateTask updateTask) {
        Task taskToUpdate=tasks.stream().filter(task -> task.getId()==updateTask.getId()).findFirst().get();
        taskToUpdate.setContent(updateTask.getContent());
        return taskToUpdate;
    }

    public List<Task> update(List<UpdateTask> updateTasks) {
        List<Integer> updateTasksIds=updateTasks.stream().map(UpdateTask::getId).collect(Collectors.toList());
        return tasks.stream().map(task -> {
            if(updateTasksIds.contains(task.getId()))
                task.setContent(updateTasks.stream()
                        .filter(updateTask -> updateTask.getId()==task.getId()).findFirst().get().getContent());
            return task;
        }).collect(Collectors.toList());
    }

    public Task switchTaskDone(int id) {
        Task taskToSwitchDoneField=tasks.stream().filter(task -> task.getId()==id).findFirst().get();
        taskToSwitchDoneField.setDone(!taskToSwitchDoneField.isDone());
        return taskToSwitchDoneField;
    }

    public List<Task> switchTaskDone(List<Integer> ids) {
        return tasks.stream().filter(task -> ids.contains(task.getId()))
                .map(task -> {task.setDone(!task.isDone());
                return task;
                }).collect(Collectors.toList());
    }
}
