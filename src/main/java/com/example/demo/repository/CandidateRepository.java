package com.example.demo.repository;

import com.example.demo.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    List<Candidate> findByListId(Long listId);
}
