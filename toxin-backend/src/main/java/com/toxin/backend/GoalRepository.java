package com.toxin.backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GoalRepository extends JpaRepository<Goal, UUID> {

    Goal findTop1ByOrderByCreatedDesc();
}
