package com.app.productshopbackend.controller;



import com.app.productshopbackend.exception.ResourceNotFoundException;
import com.app.productshopbackend.model.Menus;
import com.app.productshopbackend.repo.MenusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/menus")
public class MenusController {
    @Autowired
    MenusRepo menusRepo;

    @GetMapping("/")
    private List<Menus> getAllMenus()  {
        return menusRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menus> getMenus(@PathVariable Long id)    {
        Menus Menu = menusRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Menu not exist " + id));
        return ResponseEntity.ok(Menu);
    }

    @PostMapping("/")
    public Menus PostMenus(@RequestBody Menus Menu) {
        Menu.setCreate_at(new Date());
        Menu.setUpdate_at(new Date());
        return menusRepo.save(Menu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Menus> PutMenus(@RequestBody Menus Menu, @PathVariable Long id)  {
        Menus newMenu = menusRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Menu not exist " + id));
        newMenu.setTitle(Menu.getTitle());
        newMenu.setLink(Menu.getLink());
        newMenu.setParent_id(Menu.getParent_id());
        newMenu.setPostion(Menu.getPostion());
        newMenu.setUpdate_at(new Date());
        Menus updateMenus = menusRepo.save(newMenu);
        return ResponseEntity.ok(updateMenus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteMenus(@PathVariable Long id)    {
        Menus Menu = menusRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Menu not exist " + id));
        menusRepo.delete(Menu);
        Map<String, Boolean> reponse = new HashMap<>();
        reponse.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(reponse);
    }
}
