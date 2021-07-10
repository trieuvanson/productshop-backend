package com.app.productshopbackend.repo;


import com.app.productshopbackend.model.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryProductRepo extends JpaRepository<CategoryProduct, Long> {
}
