package com.azharbazla.eFarm.service;

import com.azharbazla.eFarm.entity.Farmer;
import org.springframework.data.domain.Page;

public interface FarmerService {
    Farmer create(Farmer farmer);
    Farmer getById(String id);
    Page<Farmer> getAll(Integer page, Integer size);
    Farmer update(Farmer farmer);
    void deleteById(String id);
}
