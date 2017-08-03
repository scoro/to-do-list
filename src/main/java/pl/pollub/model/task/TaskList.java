package pl.pollub.model.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;
import pl.pollub.model.task.DTO.TaskDTO;

@Component
public class TaskList {

    private final List<Task> tasks = new ArrayList<>();

    private final AtomicInteger counter = new AtomicInteger();

    /*public Task add(TaskDTO taskDTO){
        Task created = new Task(generateId(), taskDTO.getContent());
        tasks.add(created);
        return created;
    }
*/
    private int generateId() {
        return counter.incrementAndGet();
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task add(TaskDTO taskDTO) {
        return null;
    }
}
