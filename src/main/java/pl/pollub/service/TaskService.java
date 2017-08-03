package pl.pollub.service;

import pl.pollub.model.task.Task;
import pl.pollub.model.task.DTO.TaskDTO;

public interface TaskService {
    Task createTask(TaskDTO taskDTO);
}
