package com.app.productshopbackend.repo;


import com.app.productshopbackend.model.Menus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenusRepo extends JpaRepository<Menus, Long> {
}
