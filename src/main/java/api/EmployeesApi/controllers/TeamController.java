package api.EmployeesApi.controllers;

import api.EmployeesApi.models.ResponseObject;
import api.EmployeesApi.models.Team;
import api.EmployeesApi.repositories.TeamsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/teams")
public class TeamController {
    //private static final Logger logger = LoggerFactory.getLogger(TeamController.class);
    // DI = Dependency Injection
    @Autowired
    private TeamsRepository teamsRepository;

    // Get all teams
    @GetMapping("/all")
    List<Team> getAllTeams() { return teamsRepository.findAll(); }

    // Insert new teams
    @PostMapping(value = "/new", consumes = "application/json", produces = "application/json")
    ResponseEntity<ResponseObject> insertTeam(@RequestBody Team newTeam) {
        // CheckName
        List<Team> foundTeams = teamsRepository.findByTeamName(newTeam.getTeamName().trim());
        if (foundTeams.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("fail", "Team name has already taken", "")
            );
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Add new team successfully", teamsRepository.save(newTeam))
            );
        }
    }
}
