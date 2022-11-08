package ru.sotn.catalogservice.controller;

import lombok.AllArgsConstructor;

import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.sotn.catalogservice.domain.Clothe;
import ru.sotn.catalogservice.service.ClotheService;

@RestController
@AllArgsConstructor
public class ClotheController {
    private final ClotheService clotheService;
//    @GetMapping("clothe/test")
//    public ResponseEntity<String> test(){
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        val oauth = securityContext.getAuthentication();
//
//        return new ResponseEntity<>("test", HttpStatus.OK);
//    }

    @PostMapping("clothe/create")
    public ResponseEntity<Clothe> createClothe(@RequestBody Clothe clothe){
        return new ResponseEntity<>(clotheService.createClothe(clothe), HttpStatus.CREATED);
    }

    @PutMapping("clothe/update")
    public ResponseEntity<Clothe> updateClothe(@RequestBody Clothe clothe){
        return new ResponseEntity<>(clotheService.updateClothe(clothe), HttpStatus.OK);
    }
}
