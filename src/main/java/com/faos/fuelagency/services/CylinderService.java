package com.faos.fuelagency.services;

import com.faos.fuelagency.entities.Cylinder;
import com.faos.fuelagency.repositories.CylinderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CylinderService {

    @Autowired
    private final CylinderRepository cylinderRepository;

    public CylinderService(CylinderRepository cylinderRepository) {
        this.cylinderRepository = cylinderRepository;
    }

    public Cylinder saveCylinder(Cylinder cylinder) {
        return cylinderRepository.save(cylinder);
    }

    public Optional<Cylinder> getCylinderById(Integer cylinderId) {
        return cylinderRepository.findById(cylinderId);
    }

    public List<Cylinder> getAllCylinders(){
        return cylinderRepository.findAll();
    }

    public void deleteCylinderById(Integer cylinderId) {
        cylinderRepository.deleteById(cylinderId);
    }
}
