package com.github.skuzmenko.Sii_task.box.controller;

import com.github.skuzmenko.Sii_task.box.dto.CollectionBoxDTO;
import com.github.skuzmenko.Sii_task.box.dto.CreateBoxDTO;
import com.github.skuzmenko.Sii_task.box.dto.ListBoxDTO;
import com.github.skuzmenko.Sii_task.box.service.CollectionBoxService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/box")
public class CollectionBoxController {
    private final CollectionBoxService boxService;

    public CollectionBoxController(CollectionBoxService boxService) {
        this.boxService = boxService;
    }

    @PostMapping
    public ResponseEntity<CollectionBoxDTO> registerBox(@RequestBody CreateBoxDTO createBoxDTO){
        return ResponseEntity.ok(boxService.addBox(createBoxDTO));
    }

    @GetMapping
    public  ResponseEntity<ListBoxDTO> getAllBoxes(){
        return ResponseEntity.ok(boxService.listAllBoxes());
    }
}
