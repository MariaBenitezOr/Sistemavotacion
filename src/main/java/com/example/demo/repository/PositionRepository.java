package com.example.demo.repository;

import com.example.demo.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository                           //Spring/Hibernate crean la clase real internamente.
public interface PositionRepository extends JpaRepository<Position, Long> {

    List<Position> findByElectionId(Long electionId);
}
