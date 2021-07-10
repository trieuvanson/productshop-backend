package com.app.productshopbackend.repo;


import com.app.productshopbackend.model.Options;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionsRepo extends JpaRepository<Options, Long> {
}
