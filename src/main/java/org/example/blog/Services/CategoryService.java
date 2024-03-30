package org.example.blog.Services;

import org.example.blog.payloads.CategoryDto;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;



public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

    void   deleteCategory(Integer categoryId);
    CategoryDto getcreateCategory(Integer categoryId);

    List<CategoryDto> getCategory();





}
