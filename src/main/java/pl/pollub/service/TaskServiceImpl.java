package pl.pollub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pollub.model.task.Task;
import pl.pollub.model.task.DTO.TaskDTO;
import pl.pollub.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task createTask(TaskDTO taskDTO) {
        Task newTask = new Task(taskDTO);
        taskRepository.save(newTask);
        return newTask;
    }
}
