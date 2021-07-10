package com.app.productshopbackend.controller;



import com.app.productshopbackend.exception.ResourceNotFoundException;
import com.app.productshopbackend.model.Options;
import com.app.productshopbackend.repo.OptionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/options")
public class OptionsController {
    @Autowired
    OptionsRepo optionsRepo;

    @GetMapping("/")
    private List<Options> getOptions()  {
        return optionsRepo.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Options> getOption(@PathVariable Long id)    {
        Options Option = optionsRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Option not exist " + id));
        return ResponseEntity.ok(Option);
    }

    @PostMapping("/")
    public Options PostOption(@RequestBody Options Option) {
        return optionsRepo.save(Option);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Options> PutOption(@RequestBody Options Option, @PathVariable Long id)  {
        Options newOption = optionsRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Option not exist " + id));
        newOption.setOtp_key(Option.getOtp_key());
        newOption.setOtp_value(Option.getOtp_value());
        Options updateOption = optionsRepo.save(newOption);
        return ResponseEntity.ok(updateOption);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteOption(@PathVariable Long id)    {
        Options Option = optionsRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Option not exist " + id));
        optionsRepo.delete(Option);
        Map<String, Boolean> reponse = new HashMap<>();
        reponse.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(reponse);
    }
}
