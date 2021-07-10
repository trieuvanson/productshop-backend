package com.app.productshopbackend.controller;



import com.app.productshopbackend.exception.ResourceNotFoundException;
import com.app.productshopbackend.model.SubmitContact;
import com.app.productshopbackend.repo.SubmitContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/submit-contact")
public class SubmitContactController {
    @Autowired
    SubmitContactRepo submitContactRepo;

    @GetMapping("/")
    private List<SubmitContact> getSubmitContact()  {
        return submitContactRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubmitContact> getSubmitContact(@PathVariable Long id)    {
        SubmitContact SubmitContact = submitContactRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("SubmitContact not exist " + id));
        return ResponseEntity.ok(SubmitContact);
    }

    @PostMapping("/")
    public SubmitContact PostSubmitContact(@RequestBody SubmitContact SubmitContact) {
        SubmitContact.setCreate_at(new Date());
        SubmitContact.setUpdate_at(new Date());
        return submitContactRepo.save(SubmitContact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubmitContact> PutSubmitContact(@RequestBody SubmitContact SubmitContact, @PathVariable Long id)  {
        SubmitContact newSubmitContact = submitContactRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("SubmitContact not exist " + id));
        newSubmitContact.setName(SubmitContact.getName());
        newSubmitContact.setEmail(SubmitContact.getEmail());
        newSubmitContact.setContent(SubmitContact.getContent());
        newSubmitContact.setStatus(SubmitContact.getStatus());
        newSubmitContact.setUpdate_at(new Date());
        SubmitContact updateSubmitContact = submitContactRepo.save(newSubmitContact);
        return ResponseEntity.ok(updateSubmitContact);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteSubmitContact(@PathVariable Long id)    {
        SubmitContact SubmitContact = submitContactRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("SubmitContact not exist " + id));
        submitContactRepo.delete(SubmitContact);
        Map<String, Boolean> reponse = new HashMap<>();
        reponse.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(reponse);
    }
}
