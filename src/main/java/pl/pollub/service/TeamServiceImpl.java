package pl.pollub.service;

import org.springframework.stereotype.Service;
import pl.pollub.model.team.DTO.TeamDTO;
import pl.pollub.model.team.Team;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    @Override
    public Team createTeam(TeamDTO teamDTO) {
        return null;
    }

    @Override
    public List<Team> getAllTeams() {
        return null;
    }
}
