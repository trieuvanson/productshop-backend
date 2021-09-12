package com.app.productshopbackend.controller;


import com.app.productshopbackend.exception.ResourceNotFoundException;
import com.app.productshopbackend.model.Category;
import com.app.productshopbackend.model.Product;
import com.app.productshopbackend.repo.CategoryProductRepo;
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
    private List<Category> getAllCategoryProduct() {
        return categoryProductRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryProduct(@PathVariable Integer id) {
        Category CategoryProduct = categoryProductRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("CategoryProduct not exist " + id));
        return ResponseEntity.ok(CategoryProduct);
    }

    @PostMapping("/")
    public Category PostCategoryProduct(@RequestBody Category CategoryProduct) {
        CategoryProduct.setCreate_at(new Date());
        CategoryProduct.setUpdate_at(new Date());
        return categoryProductRepo.save(CategoryProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> PutCategoryProduct(@RequestBody Category CategoryProduct, @PathVariable Integer id) {
        Category newCategoryProduct = categoryProductRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("CategoryProduct not exist " + id));
        newCategoryProduct.setCate_name(CategoryProduct.getCate_name());
        newCategoryProduct.setCate_banner(CategoryProduct.getCate_banner());
        newCategoryProduct.setCate_slug(CategoryProduct.getCate_slug());
        newCategoryProduct.setUpdate_at(new Date());
        Category updateCategoryProduct = categoryProductRepo.save(newCategoryProduct);
        return ResponseEntity.ok(updateCategoryProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCategoryProduct(@PathVariable Integer id) {
        Category CategoryProduct = categoryProductRepo.findById(id).orElseThrow(
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

    @GetMapping("/cateproduct1")
    public ResponseEntity<String> getCateProduct1() throws Exception {
        List<Category> list = categoryProductRepo.findAll();
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode cateProduct = mapper.createObjectNode();
        String[] data = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Category cp = list.get(i);
            cateProduct.put("id", cp.getId());
            cateProduct.put("cate_name", cp.getCate_name());
            List<Product> list1 = categoryProductRepo.getCateProduct(cp.getId());
            for (int j = 0; j < list1.size() - 1; j++) {
                cateProduct.put("product", mapper.writeValueAsString(list1.get(i)));
            }
            data[i] = mapper.writeValueAsString(cateProduct);
        }
        return ResponseEntity.ok(Arrays.toString(data));
    }
}
