package pl.pollub.task;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskList {

    private final List<Task> tasks = new ArrayList<>();

    Task add(Task task){
        tasks.add(task);
        return task;
    }

    Task findOne(int id){
        if(tasks.stream().anyMatch(e -> e.getId()==id))
            return tasks.stream().filter(e -> e.getId() == id).findFirst().get();
        return null;
    }

    List<Task> getAllTasks() {
        return tasks;
    }

    boolean deleteById(int givenId){
        return tasks.removeIf((task) -> task.getId()==givenId);
    }
}
