package com.sayed.seu.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Faculty faculty;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Semester semester;

    private String courseName;
    private String term;

    @ElementCollection
    private List<Student> students = new ArrayList<>();

    private String topic;
}
