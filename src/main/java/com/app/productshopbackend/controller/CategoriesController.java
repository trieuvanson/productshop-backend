package com.app.productshopbackend.controller;



import com.app.productshopbackend.exception.ResourceNotFoundException;
import com.app.productshopbackend.model.Categories;
import com.app.productshopbackend.repo.CategoriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/categories")
public class CategoriesController {
    @Autowired
    CategoriesRepo categoriesRepo;

    @GetMapping("/")
    private List<Categories> getCategories()  {
        return categoriesRepo.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Categories> getCategory(@PathVariable Long id)    {
        Categories Category = categoriesRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Category not exist " + id));
        return ResponseEntity.ok(Category);
    }

    @PostMapping("/")
    public Categories PostCategory(@RequestBody Categories Category) {
        Category.setCreate_at(new Date());
        Category.setUpdate_at(new Date());
        return categoriesRepo.save(Category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categories> PutCategory(@RequestBody Categories Category, @PathVariable Long id)  {
        Categories newCategory = categoriesRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category not exist " + id));
        newCategory.setBanner(Category.getBanner());
        newCategory.setName(Category.getName());
        newCategory.setParent_id(Category.getParent_id());
        newCategory.setSlug(newCategory.getSlug());
        newCategory.setUpdate_at(new Date());
        Categories updateCategory = categoriesRepo.save(newCategory);
        return ResponseEntity.ok(updateCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCategory(@PathVariable Long id)    {
        Categories Category = categoriesRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category not exist " + id));
        categoriesRepo.delete(Category);
        Map<String, Boolean> reponse = new HashMap<>();
        reponse.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(reponse);
    }
}
