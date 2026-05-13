package com.example.demo.repository;

import com.example.demo.entity.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository                //Voy a trabajar con la entity Election y su ID es Long
public interface ElectionRepository extends JpaRepository<Election, Long> {

}
