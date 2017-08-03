package pl.pollub.model.task.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.pollub.model.project.Project;
import pl.pollub.model.team.Team;

@Data
@AllArgsConstructor
public class TaskDTO {

    private final String content;

    private final Project project;

    private final Team team;

}
