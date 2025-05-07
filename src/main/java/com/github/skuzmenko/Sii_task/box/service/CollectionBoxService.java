package com.github.skuzmenko.Sii_task.box.service;

import com.github.skuzmenko.Sii_task.box.dto.BoxInfoDTO;
import com.github.skuzmenko.Sii_task.box.dto.CollectionBoxDTO;
import com.github.skuzmenko.Sii_task.box.dto.CreateBoxDTO;
import com.github.skuzmenko.Sii_task.box.dto.ListBoxDTO;
import com.github.skuzmenko.Sii_task.box.model.CollectionBox;
import com.github.skuzmenko.Sii_task.box.repository.CollectionBoxRepository;
import com.github.skuzmenko.Sii_task.event.model.FundraisingEvent;
import com.github.skuzmenko.Sii_task.event.service.FundraisingEventService;
import com.github.skuzmenko.Sii_task.exception.AbsentRecordException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CollectionBoxService {
    private final CollectionBoxRepository boxRepository;
    private final FundraisingEventService eventService;

    public CollectionBoxService(CollectionBoxRepository boxRepository, FundraisingEventService eventService) {
        this.boxRepository = boxRepository;
        this.eventService = eventService;
    }


    public CollectionBoxDTO addBox(CreateBoxDTO createBoxDTO){
        Long eventId = createBoxDTO.getEventId();
        Optional<FundraisingEvent> eventOptional = eventService.getEvent(eventId);
        if (eventOptional.isEmpty())
            throw new AbsentRecordException(String.format("Event with id %s was not found",eventId));
        CollectionBox box = new CollectionBox(eventOptional.get());
        boxRepository.save(box);
        return box.toDTO();
    }

    public ListBoxDTO listAllBoxes()
    {
        List<CollectionBox> boxes = boxRepository.findAll();
        List<BoxInfoDTO> boxInfoList = new ArrayList<>();
        boxes.forEach(b->boxInfoList.add(b.toInfoDTO()));
        return new ListBoxDTO(boxInfoList);
    }
}
