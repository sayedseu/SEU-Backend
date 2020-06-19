package com.sayed.seu.backend.controller;

import com.sayed.seu.backend.exception.ResourceNotFoundException;
import com.sayed.seu.backend.model.Semester;
import com.sayed.seu.backend.model.Team;
import com.sayed.seu.backend.service.TeamManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/team-management")
public class TeamManagementController {

    private final TeamManagementService service;

    public TeamManagementController(TeamManagementService service) {
        this.service = service;
    }

    @PostMapping(value = "/new")
    public ResponseEntity<Team> insert(@RequestBody Team team) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.insert(team));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "/teams")
    public ResponseEntity<List<Team>> retrieve() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.retrieve());
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value = "/teams/semester")
    public ResponseEntity<List<Team>> retrieveBySemester(@RequestBody Semester semester) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.retrieve(semester));
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping(value = "/team/update")
    public ResponseEntity<Team> update(@RequestBody Team team) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.update(team));
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
