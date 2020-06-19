package com.sayed.seu.backend.service;

import com.sayed.seu.backend.exception.ResourceNotFoundException;
import com.sayed.seu.backend.model.Faculty;
import com.sayed.seu.backend.model.Semester;
import com.sayed.seu.backend.model.Team;
import com.sayed.seu.backend.repository.FacultyRepository;
import com.sayed.seu.backend.repository.SemesterRepository;
import com.sayed.seu.backend.repository.TeamManagementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamManagementService {

    private final TeamManagementRepository repository;
    private final FacultyRepository facultyRepository;
    private final SemesterRepository semesterRepository;

    public TeamManagementService(TeamManagementRepository repository,
                                 FacultyRepository facultyRepository,
                                 SemesterRepository semesterRepository) {
        this.repository = repository;
        this.facultyRepository = facultyRepository;
        this.semesterRepository = semesterRepository;
    }

    public Team insert(Team team) {
        Faculty faculty = insertFaculty(team.getFaculty());
        System.out.println(faculty);
        Semester semester = insertSemester(team.getSemester());
        System.out.println(semester);
        team.setFaculty(faculty);
        team.setSemester(semester);
        return repository.save(team);
    }

    public List<Team> retrieve() throws ResourceNotFoundException {
        List<Team> teamList = repository.findAll();
        if (teamList.isEmpty()) throw new ResourceNotFoundException("empty list");
        else return teamList;
    }

    public List<Team> retrieve(Semester semester) throws ResourceNotFoundException {
        List<Team> teamList = repository.findAll()
                .stream()
                .filter(i -> i.getSemester().getName().equals(semester.getName())
                        && i.getSemester().getYear().equals(semester.getYear()))
                .collect(Collectors.toList());
        if (teamList.isEmpty()) throw new ResourceNotFoundException("empty list");
        else return teamList;
    }

    private Faculty insertFaculty(Faculty faculty) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(faculty.getId());
        return optionalFaculty.orElseGet(() -> facultyRepository.save(faculty));
    }

    private Semester insertSemester(Semester semester) {
        Optional<Semester> optionalSemester = semesterRepository.findAll()
                .stream()
                .filter(i -> i.getName().equals(semester.getName()) && i.getYear().equals(semester.getYear()))
                .findFirst();
        return optionalSemester.orElseGet(() -> semesterRepository.save(semester));
    }

    public Team update(Team team) throws ResourceNotFoundException {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(team.getFaculty().getId());
        Optional<Semester> optionalSemester = semesterRepository.findById(team.getSemester().getId());
        Optional<Team> optionalTeam = repository.findById(team.getId());
        if (optionalFaculty.isPresent() && optionalSemester.isPresent() && optionalTeam.isPresent()) {
            Semester updatingSemester = optionalSemester.get();
            updatingSemester.setName(team.getSemester().getName());
            updatingSemester.setYear(team.getSemester().getYear());
            Semester updatedSemester = semesterRepository.save(updatingSemester);
            team.setSemester(updatedSemester);
            return repository.save(team);
        } else {
            throw new ResourceNotFoundException("");
        }
    }
}
