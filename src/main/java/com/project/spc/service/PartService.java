package com.project.spc.service;

import com.project.spc.DTOs.PartDTO;
import com.project.spc.models.Part;
import com.project.spc.repository.PartRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartService {

    @Autowired
    PartRepository partRepository;

    public Part createPart(PartDTO partDTO) {
        if(partRepository.findById(partDTO.getPartNumber()).isPresent()) {
            throw new RuntimeException("El numero de parte ya existe");
        }

        Part part = new Part();
        part.setPartNumber(partDTO.getPartNumber());
        part.setDescription(partDTO.getDescription());

        return partRepository.save(part);
    }

    public List<PartDTO> getAllPartNumbers() {
        List<Part> allPartNumbers = partRepository.findAll();

        return allPartNumbers.stream().map((Part part) -> {
            PartDTO partDTO = new PartDTO();
            partDTO.setPartNumber(part.getPartNumber());
            partDTO.setDescription(part.getDescription());

            return partDTO;

        }).toList();
    }
}
