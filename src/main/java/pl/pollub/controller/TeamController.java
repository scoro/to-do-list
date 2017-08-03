package pl.pollub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pollub.model.team.DTO.TeamDTO;
import pl.pollub.model.team.Team;
import pl.pollub.service.TeamService;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Team> createProject(@RequestBody TeamDTO teamDTO) {
        Team createdTeam = teamService.createTeam(teamDTO);
        return new ResponseEntity<>(createdTeam, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<Team>> getAllTeams() {
        List<Team> allTeams = teamService.getAllTeams();
        return new ResponseEntity<>(allTeams, HttpStatus.CREATED);
    }
}