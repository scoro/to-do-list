package pl.pollub.service;

import pl.pollub.model.project.DTO.ProjectDTO;
import pl.pollub.model.project.Project;
import pl.pollub.model.task.DTO.TaskDTO;

import java.util.List;

public interface ProjectService {
    Project createProject(ProjectDTO projectDTO);

    List<Project> getAllProjects();
}
