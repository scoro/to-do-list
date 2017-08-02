package pl.pollub.task;


import java.util.List;

public interface TaskService {

    Task saveTask(NewTask newTask);

    Task getTaskById(int id);

    List<Task> getAllTasks();

    boolean deleteById(int id);
}
