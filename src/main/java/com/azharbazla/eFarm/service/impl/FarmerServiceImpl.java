package com.azharbazla.eFarm.service.impl;

import com.azharbazla.eFarm.entity.Farmer;
import com.azharbazla.eFarm.repository.FarmerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class FarmerServiceImpl implements com.azharbazla.eFarm.service.FarmerService {
    private final FarmerRepository farmerRepository;

    @Override
    public Farmer create(Farmer farmer) {
        try {
            return farmerRepository.save(farmer);
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email or Telephone Already Used");
        }
    }

    @Override
    public Farmer getById(String id) {
        return farmerRepository.findByIdFarmer(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Farmer Not Found"));
    }

    @Override
    public Page<Farmer> getAll(Integer page, Integer size) {
        return farmerRepository.findAllfarmer(PageRequest.of(page, size));
    }

    @Override
    public Farmer update(Farmer farmer) {
        Farmer currentFarmer = farmerRepository.findByIdFarmer(farmer.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Farmer Not Found"));

        if (currentFarmer != null) {
            return farmerRepository.save(farmer);
        }
        return null;
    }

    @Override
    public void deleteById(String id) {
        farmerRepository.delete(getById(id));
    }
}
