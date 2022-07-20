package com.api.parkingcontrol.services;

import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.models.RoleModel;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import com.api.parkingcontrol.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;


    @Transactional
    public RoleModel save(RoleModel roleModel) {
        return repository.save(roleModel);
    }

    public Page<RoleModel> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<RoleModel> findById(UUID id) {
        return repository.findById(id);
    }

}
