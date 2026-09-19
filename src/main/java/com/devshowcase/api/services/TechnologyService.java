package com.devshowcase.api.services;

import com.devshowcase.api.dtos.CreateTechnologyDTO;
import com.devshowcase.api.dtos.TechnologyResponseDTO;
import com.devshowcase.api.models.Technology;
import com.devshowcase.api.repositories.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TechnologyService {

    @Autowired
    private TechnologyRepository technologyRepository;

    public TechnologyResponseDTO save(CreateTechnologyDTO dto) {
        Technology tech = new Technology();
        tech.setName(dto.name());
        
        Technology savedTech = technologyRepository.save(tech);
        return new TechnologyResponseDTO(savedTech);
    }

    public List<TechnologyResponseDTO> findAll() {
        return technologyRepository.findAll()
                .stream()
                .map(TechnologyResponseDTO::new)
                .collect(Collectors.toList());
    }
}