package com.github.skuzmenko.Sii_task.box.controller;

import com.github.skuzmenko.Sii_task.box.dto.CollectionBoxDTO;
import com.github.skuzmenko.Sii_task.box.dto.AssignBoxDTO;
import com.github.skuzmenko.Sii_task.box.dto.DonationDTO;
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
    public ResponseEntity<CollectionBoxDTO> registerBox(){
        return ResponseEntity.ok(boxService.addBox());
    }

    @GetMapping
    public  ResponseEntity<ListBoxDTO> getAllBoxes(){
        return ResponseEntity.ok(boxService.listAllBoxes());
    }

    @DeleteMapping("/{boxId}")
    public void unregisterBox(@PathVariable Long boxId){
        boxService.deleteBox(boxId);
    }

    @PutMapping("/donate/{boxId}")
    public ResponseEntity<CollectionBoxDTO> donateToBox(@PathVariable Long boxId,
                                                        @RequestBody DonationDTO donationDTO) {
        return ResponseEntity.ok(boxService.donateToBox(boxId, donationDTO));
    }

    @PutMapping("/assign/{boxId}")
    public ResponseEntity<CollectionBoxDTO> assignBox(@PathVariable Long boxId,
                                                      @RequestBody AssignBoxDTO assignBoxDTO) {
        return ResponseEntity.ok(boxService.assignBox(boxId, assignBoxDTO));
    }
}
