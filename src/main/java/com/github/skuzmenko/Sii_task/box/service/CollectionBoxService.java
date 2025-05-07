package com.github.skuzmenko.Sii_task.box.service;

import com.github.skuzmenko.Sii_task.box.dto.*;
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
        FundraisingEvent event = eventOptional.get();
        CollectionBox box = new CollectionBox(event);
        event.getBoxes().add(box);
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

    public void deleteBox(Long boxId){
        CollectionBox box = getBox(boxId);
        // updating the box list for the event
        FundraisingEvent event = box.getEvent();
        event.getBoxes().remove(box);
        eventService.saveEvent(event);
        // deleting the box from the database:
        boxRepository.delete(box);
    }

    public CollectionBoxDTO donateToBox(Long boxId, DonationDTO donationDTO)
    {
        CollectionBox box = getBox(boxId);
        String currency = donationDTO.getCurrency();
        eventService.verifyCurrency(currency);
        Double amount = donationDTO.getAmount();
        switch(currency)
        {
            case "PLN":
                box.setPlnAmount(box.getPlnAmount() + amount);
                break;
            case "EUR":
                box.setEuroAmount(box.getEuroAmount() + amount);
                break;
            case "USD":
                box.setUsdAmount(box.getUsdAmount() + amount);
                break;

        }
        return boxRepository.save(box).toDTO();
    }

    public CollectionBox getBox(Long id)
    {
        Optional<CollectionBox> boxOptional = boxRepository.findById(id);
        if (boxOptional.isEmpty())
            throw new AbsentRecordException(String.format("Box with id '%s' was not found",id));
        return boxOptional.get();
    }
}
