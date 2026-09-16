package com.devshowcase.api.controllers;

import com.devshowcase.api.dtos.CreateTechnologyDTO;
import com.devshowcase.api.models.Technology;
import com.devshowcase.api.repositories.TechnologyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/technologies")
public class TechnologyController {
    @Autowired 
    private TechnologyRepository techRepository;

    @PostMapping
    public ResponseEntity<Technology> create(@RequestBody @Valid CreateTechnologyDTO dto) {
        Technology tech = new Technology(dto.name());
        return ResponseEntity.status(HttpStatus.CREATED).body(techRepository.save(tech));
    }

    @GetMapping
    public ResponseEntity<List<Technology>> findAll() {
        return ResponseEntity.ok(techRepository.findAll());
    }
}