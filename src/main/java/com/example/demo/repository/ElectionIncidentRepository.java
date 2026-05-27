package com.example.demo.repository;


import com.example.demo.entity.ElectionIncidents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectionIncidentRepository extends JpaRepository <ElectionIncidents ,Long>  {

}
