package com.project.spc.controller;

import com.project.spc.DTOs.PartDTO;
import com.project.spc.models.Part;
import com.project.spc.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parts")
public class PartController {

    @Autowired
    PartService partService;

    @PostMapping
    public ResponseEntity<Part> createPart(@RequestBody  PartDTO partDTO) {
        System.out.println(partDTO);
        Part part = partService.createPart(partDTO);

        return ResponseEntity.ok(part);
    }
}
