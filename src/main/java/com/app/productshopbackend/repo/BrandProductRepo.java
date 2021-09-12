package com.app.productshopbackend.repo;


import com.app.productshopbackend.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandProductRepo extends JpaRepository<Brand, Integer> {
}
