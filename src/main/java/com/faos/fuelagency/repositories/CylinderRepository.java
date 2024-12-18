package com.faos.fuelagency.repositories;

import com.faos.fuelagency.entities.Cylinder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CylinderRepository extends JpaRepository<Cylinder, Integer> {
    
}