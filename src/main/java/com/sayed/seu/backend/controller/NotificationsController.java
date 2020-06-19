package com.sayed.seu.backend.controller;

import com.sayed.seu.backend.exception.ResourceNotFoundException;
import com.sayed.seu.backend.model.Notifications;
import com.sayed.seu.backend.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/notification")
public class NotificationsController {

    private final NotificationService service;

    public NotificationsController(NotificationService service) {
        this.service = service;
    }

    @PostMapping(value = "/new")
    public ResponseEntity<Notifications> insert(@RequestBody Notifications notifications) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.insert(notifications));
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<Notifications>> retrieve(@RequestParam String facultyName) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.retrieve(facultyName));
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Boolean> delete(@RequestParam long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
