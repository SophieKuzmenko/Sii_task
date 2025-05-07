package com.github.skuzmenko.Sii_task.event.controller;

import com.github.skuzmenko.Sii_task.event.dto.CreateEventDTO;
import com.github.skuzmenko.Sii_task.event.dto.FundraisingEventDTO;
import com.github.skuzmenko.Sii_task.event.service.FundraisingEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/event")
public class FundraisingEventController {
    private final FundraisingEventService eventService;

    public FundraisingEventController(FundraisingEventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<FundraisingEventDTO> createEvent(@RequestBody CreateEventDTO createEventDTO){
        return ResponseEntity.ok(eventService.addEvent(createEventDTO));
    }
}
