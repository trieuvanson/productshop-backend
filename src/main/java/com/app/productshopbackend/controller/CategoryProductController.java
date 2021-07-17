package com.app.productshopbackend.controller;


import com.app.productshopbackend.exception.ResourceNotFoundException;
import com.app.productshopbackend.model.CategoryProduct;
import com.app.productshopbackend.repo.CategoryProductRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/category-product")
public class CategoryProductController {
    @Autowired
    CategoryProductRepo categoryProductRepo;

    @GetMapping("/")
    private List<CategoryProduct> getAllCategoryProduct() {
        return categoryProductRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryProduct> getCategoryProduct(@PathVariable Long id) {
        CategoryProduct CategoryProduct = categoryProductRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("CategoryProduct not exist " + id));
        return ResponseEntity.ok(CategoryProduct);
    }

    @PostMapping("/")
    public CategoryProduct PostCategoryProduct(@RequestBody CategoryProduct CategoryProduct) {
        CategoryProduct.setCreate_at(new Date());
        CategoryProduct.setUpdate_at(new Date());
        return categoryProductRepo.save(CategoryProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryProduct> PutCategoryProduct(@RequestBody CategoryProduct CategoryProduct, @PathVariable Long id) {
        CategoryProduct newCategoryProduct = categoryProductRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("CategoryProduct not exist " + id));
        newCategoryProduct.setCate_name(CategoryProduct.getCate_name());
        newCategoryProduct.setCate_banner(CategoryProduct.getCate_banner());
        newCategoryProduct.setCate_slug(CategoryProduct.getCate_slug());
        newCategoryProduct.setParent_id(CategoryProduct.getParent_id());
        newCategoryProduct.setUpdate_at(new Date());
        CategoryProduct updateCategoryProduct = categoryProductRepo.save(newCategoryProduct);
        return ResponseEntity.ok(updateCategoryProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCategoryProduct(@PathVariable Long id) {
        CategoryProduct CategoryProduct = categoryProductRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("CategoryProduct not exist " + id));
        categoryProductRepo.delete(CategoryProduct);
        Map<String, Boolean> reponse = new HashMap<>();
        reponse.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(reponse);
    }

    @GetMapping("/cateproduct")
    public ResponseEntity<String> getCateProduct() throws Exception {
        List<String[]> list = categoryProductRepo.getCateProduct();
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode cateProduct = mapper.createObjectNode();
        String[] data = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            String[] s = list.get(i);
            cateProduct.put("id", s[0]);
            cateProduct.put("cateName", s[1]);
            cateProduct.put("productName", s[2]);
            data[i] = mapper.writeValueAsString(cateProduct);
        }
        return ResponseEntity.ok(Arrays.toString(data));
    }
}
