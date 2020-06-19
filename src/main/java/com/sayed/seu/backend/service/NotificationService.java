package com.sayed.seu.backend.service;

import com.sayed.seu.backend.exception.ResourceNotFoundException;
import com.sayed.seu.backend.model.Notifications;
import com.sayed.seu.backend.model.Team;
import com.sayed.seu.backend.repository.NotificationsRepository;
import com.sayed.seu.backend.repository.TeamManagementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    private final NotificationsRepository notificationsRepository;
    private final TeamManagementRepository managementRepository;

    public NotificationService(NotificationsRepository notificationsRepository, TeamManagementRepository managementRepository) {
        this.notificationsRepository = notificationsRepository;
        this.managementRepository = managementRepository;
    }

    public Notifications insert(Notifications notifications) throws ResourceNotFoundException {
        System.out.println(notifications.getTeam().getId());
        Optional<Team> optionalTeam = managementRepository.findById(notifications.getTeam().getId());
        if (optionalTeam.isPresent()) {
            return notificationsRepository.save(notifications);
        } else {
            throw new ResourceNotFoundException("");
        }
    }

    public List<Notifications> retrieve(String facultyName) throws ResourceNotFoundException {
        List<Notifications> notificationsList = notificationsRepository.findAll()
                .stream()
                .filter(notifications -> notifications.getFacultyName().equals(facultyName))
                .collect(Collectors.toList());
        if (notificationsList.isEmpty()) {
            throw new ResourceNotFoundException("");
        } else {
            return notificationsList;
        }
    }

    public boolean delete(long id) throws Exception {
        try {
            notificationsRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception("");
        }
    }
}
