package com.app.productshopbackend.controller;



import com.app.productshopbackend.exception.ResourceNotFoundException;
import com.app.productshopbackend.model.Slides;
import com.app.productshopbackend.repo.SlidesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/slides")
public class SlidesController {
    @Autowired
    SlidesRepo slidesRepo;

    @GetMapping("/")
    private List<Slides> getSlides()  {
        return slidesRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Slides> getSlide(@PathVariable Long id)    {
        Slides slide = slidesRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Slide not exist " + id));
        return ResponseEntity.ok(slide);
    }

    @PostMapping("/")
    public Slides PostSlide(@RequestBody Slides Slide) {
        Slide.setCreate_at(new Date());
        Slide.setUpdate_at(new Date());
        return slidesRepo.save(Slide);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Slides> PutSlide(@RequestBody Slides Slide, @PathVariable Long id)  {
        Slides newSlide = slidesRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Slide not exist " + id));
        newSlide.setLink(Slide.getLink());
        newSlide.setImage(Slide.getImage());
        newSlide.setPostion(Slide.getPostion());
        newSlide.setUpdate_at(new Date());
        Slides updateSlide = slidesRepo.save(newSlide);
        return ResponseEntity.ok(updateSlide);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteSlide(@PathVariable Long id)    {
        Slides Slide = slidesRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Slide not exist " + id));
        slidesRepo.delete(Slide);
        Map<String, Boolean> reponse = new HashMap<>();
        reponse.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(reponse);
    }
}
