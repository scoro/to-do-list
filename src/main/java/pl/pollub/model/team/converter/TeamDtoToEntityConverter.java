package pl.pollub.model.team.converter;

import org.springframework.core.convert.converter.Converter;
import pl.pollub.model.project.DTO.ProjectDTO;
import pl.pollub.model.project.Project;
import pl.pollub.model.team.DTO.TeamDTO;
import pl.pollub.model.team.Team;

public class TeamDtoToEntityConverter implements Converter<TeamDTO,Team> {

    @Override
    public Team convert(TeamDTO teamDTO) {
        Team team = new Team();
        team.setCollaborators(teamDTO.getCollaborators());
        return team;
    }
}
