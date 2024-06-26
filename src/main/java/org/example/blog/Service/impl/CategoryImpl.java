package org.example.blog.Service.impl;

import org.example.blog.Services.CategoryService;
import org.example.blog.entities.Category;
import org.example.blog.exceptions.ResourceNotFoundException;
import org.example.blog.payloads.CategoryDto;
import org.example.blog.payloads.UserDto;
import org.example.blog.repositories.CategoryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto){
        Category category= this.modelMapper.map(categoryDto, Category.class);
        Category addedCategory = this.categoryRepo.save(category);
        return this.modelMapper.map(addedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId){

        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        category.setCategoryTittle(categoryDto.getCategoryTittle());
        Category updatedCategory=this.categoryRepo.save(category);
        return this.modelMapper.map(updatedCategory,CategoryDto.class);


    }

    @Override
    public void deleteCategory(Integer categoryId){
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));
        this.categoryRepo.delete(category);

    }

    @Override
    public CategoryDto getcreateCategory(Integer categoryId){
    Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));
    return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategory(){
        List<Category> categories=this.categoryRepo.findAll();
        List<CategoryDto> categoryDtos=categories.stream().map(category->this.modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }




}
