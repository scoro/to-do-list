package pl.pollub.service;

import pl.pollub.model.team.DTO.TeamDTO;
import pl.pollub.model.team.Team;

import java.util.List;

public interface TeamService {
    Team createTeam(TeamDTO teamDTO);

    List<Team> getAllTeams();
}
