package com.example.demo.repository;

import com.example.demo.entity.ElectionObservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectionObservationRepository extends JpaRepository <ElectionObservations,Long>  {

}
