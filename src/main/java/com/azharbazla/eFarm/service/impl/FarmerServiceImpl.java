package com.azharbazla.eFarm.service.impl;

import com.azharbazla.eFarm.entity.Farmer;
import com.azharbazla.eFarm.repository.FarmerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

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
        return farmerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Farmer Not Found"));
    }

    @Override
    public Page<Farmer> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Farmer> farmers = farmerRepository.findAll(pageable);
        List<Farmer> farmersResponses = new ArrayList<>();
        for (Farmer farmer : farmers.getContent()) {
            farmersResponses.add(Farmer.builder()
                    .id(farmer.getId())
                    .name(farmer.getName())
                    .address(farmer.getAddress())
                    .email(farmer.getEmail())
                    .telephone(farmer.getTelephone())
                    .build());
        }
        return new PageImpl<>(farmersResponses, pageable, farmers.getTotalElements());
    }

    @Override
    public Farmer update(Farmer farmer) {
        Farmer currentFarmer = farmerRepository.findById(farmer.getId())
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
