package com.app.productshopbackend.repo;


import com.app.productshopbackend.model.Slides;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlidesRepo extends JpaRepository<Slides, Long> {
}
