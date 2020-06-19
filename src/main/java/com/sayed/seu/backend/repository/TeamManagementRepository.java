package com.sayed.seu.backend.repository;

import com.sayed.seu.backend.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamManagementRepository extends JpaRepository<Team, Long> {
}
