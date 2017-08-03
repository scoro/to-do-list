package pl.pollub.model.task.converter;

import org.springframework.core.convert.converter.Converter;
import pl.pollub.model.project.DTO.ProjectDTO;
import pl.pollub.model.project.Project;
import pl.pollub.model.task.DTO.TaskDTO;
import pl.pollub.model.task.Task;

public class TaskDtoToEntityConverter implements Converter<TaskDTO,Task> {

    @Override
    public Task convert(TaskDTO taskDTO) {
        Task task = new Task();
        task.setContent(taskDTO.getContent());
        task.setProject(taskDTO.getProject());
        task.setTeam(taskDTO.getTeam());
        return task;
    }
}
