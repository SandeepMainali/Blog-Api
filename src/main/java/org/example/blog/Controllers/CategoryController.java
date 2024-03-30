package org.example.blog.Controllers;

import jakarta.validation.Valid;
import org.example.blog.Services.CategoryService;
import org.example.blog.payloads.ApiResponse;
import org.example.blog.payloads.CategoryDto;
import org.example.blog.payloads.UserDto;
import org.example.blog.repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createCategoryDto =this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createCategoryDto, HttpStatus.CREATED);

    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> UpdatedCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
        CategoryDto UpdatedCategory =this.categoryService.updateCategory(categoryDto,categoryId);
        return new ResponseEntity<CategoryDto>(UpdatedCategory, HttpStatus.OK);

    }

    @DeleteMapping ("/{categoryId}")

    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
        this.categoryService.deleteCategory(categoryId);
        return  new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully",true),HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategory(){
        return ResponseEntity.ok(this.categoryService.getCategory());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable Integer categoryId){

        return ResponseEntity.ok(this.categoryService.getcreateCategory(categoryId));
    }



}
