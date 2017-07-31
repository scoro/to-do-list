package pl.pollub.coWorker.data.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.pollub.task.data.model.Task;

import java.util.Set;


@Data
@EqualsAndHashCode(exclude = "tasks")
@ToString(exclude = "tasks")
public class CoWorker {

    private final int id;

    private String name;

    private Set<Task> tasks;

    public CoWorker(int id, String name, Set<Task> tasks){
        this.id=id;
        this.name=name;
        this.tasks=tasks;
        tasks.forEach(task -> task.getCoWorkers().add(this));
    }

    public void setTasks(Set<Task> tasks){
        this.tasks=tasks;
        tasks.forEach(task -> task.addCoWorker(this));
    }

    public void addTask(Task task){
        tasks.add(task);
        task.getCoWorkers().add(this);
    }

    public void addTasks(Set<Task> tasks){
        this.tasks.addAll(tasks);
        tasks.forEach(task -> task.getCoWorkers().add(this));
    }

    public void deleteTask(Task task){
        task.getCoWorkers().remove(this);
        tasks.remove(task);
    }

    public void deleteTasks(Set<Task> tasks){
        tasks.forEach(task -> task.getCoWorkers().remove(this));
        this.tasks.removeAll(tasks);
    }

    public void deleteAllTasks(){
        tasks.forEach(task -> task.getCoWorkers().remove(this));
        tasks.clear();
    }
}
