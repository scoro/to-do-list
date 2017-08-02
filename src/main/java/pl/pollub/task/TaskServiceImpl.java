package pl.pollub.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service("taskService")
public class TaskServiceImpl implements TaskService{

    private TaskList taskList;

    @Autowired
    public TaskServiceImpl(TaskList taskList){
        this.taskList = taskList;
    }

    private final AtomicInteger counter = new AtomicInteger();

    @Override
    public Task saveTask(NewTask newTask) {
        return taskList.add(new Task(generateId(),newTask.getContent()));
    }

    @Override
    public Task getTaskById(int id) {
        return taskList.findOne(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskList.getAllTasks();
    }

    @Override
    public boolean deleteById(int id) {
        return taskList.deleteById(id);
    }

    private int generateId() {
        return counter.incrementAndGet();
    }
}
