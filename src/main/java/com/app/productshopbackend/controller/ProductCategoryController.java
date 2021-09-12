package com.app.productshopbackend.controller;

import com.app.productshopbackend.exception.ResourceNotFoundException;
import com.app.productshopbackend.model.Brand;
import com.app.productshopbackend.repo.BrandProductRepo;
import com.app.productshopbackend.repo.CategoryProductRepo;
import com.app.productshopbackend.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/categoryproducts")
public class ProductCategoryController {
    @Autowired
    BrandProductRepo brandRepo;
    @Autowired
    CategoryProductRepo categoryProductRepo;
    @Autowired
    ProductRepo productRepo;

    @GetMapping("/")
    private Map<String, Object> getCategoryProducts()  {
        Map<String, Object> data = new HashMap<>();
        data.put("products", productRepo.findAll());
        data.put("categories", categoryProductRepo.findAll());
        data.put("brands", brandRepo.findAll());
        return data;
    }




    @GetMapping("/{id}")
    public ResponseEntity<Brand> getCategoryProduct(@PathVariable Integer id)   {
        Brand brand = brandRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Category not exist " + id));
        return ResponseEntity.ok(brand);
    }




    @PostMapping("/")
    public Brand PostCategory(@RequestBody Brand brand) {
        brand.setCreate_at(new Date());
        brand.setUpdate_at(new Date());
        return brandRepo.save(brand);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Brand> PutCategory(@RequestBody Brand brand, @PathVariable Integer id)  {
        Brand newbrand = brandRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category not exist " + id));
        newbrand.setUpdate_at(new Date());
        Brand updatebrand = brandRepo.save(newbrand);
        return ResponseEntity.ok(updatebrand);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCategory(@PathVariable Integer id)    {
        Brand brand = brandRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category not exist " + id));
        brandRepo.delete(brand);
        Map<String, Boolean> reponse = new HashMap<>();
        reponse.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(reponse);
    }
    
}
