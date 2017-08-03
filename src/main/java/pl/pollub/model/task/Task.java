package pl.pollub.model.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.pollub.model.project.Project;
import pl.pollub.model.task.DTO.TaskDTO;
import pl.pollub.model.team.Team;
import pl.pollub.model.user.User;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    private Team team;

    @ManyToOne
    private Project project;

    public Task (TaskDTO taskDTO){
        this.content = taskDTO.getContent();
    }
}
