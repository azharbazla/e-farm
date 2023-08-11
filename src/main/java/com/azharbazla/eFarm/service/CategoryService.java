package com.azharbazla.eFarm.service;

import com.azharbazla.eFarm.entity.Category;

public interface CategoryService {
    Category getOrSave(String category);
}
