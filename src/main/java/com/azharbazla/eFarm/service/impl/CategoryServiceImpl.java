package com.azharbazla.eFarm.service.impl;

import com.azharbazla.eFarm.entity.Category;
import com.azharbazla.eFarm.repository.CategoryRepository;
import com.azharbazla.eFarm.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public Category getOrSave(String category) {
        return categoryRepository.findByName(category).orElseGet(
                () -> categoryRepository.saveAndFlush(Category.builder().name(category).build()));
    }
}