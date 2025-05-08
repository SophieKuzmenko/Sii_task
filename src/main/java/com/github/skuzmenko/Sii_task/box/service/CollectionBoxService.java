package com.github.skuzmenko.Sii_task.box.service;

import com.github.skuzmenko.Sii_task.box.dto.*;
import com.github.skuzmenko.Sii_task.box.model.CollectionBox;
import com.github.skuzmenko.Sii_task.box.repository.CollectionBoxRepository;
import com.github.skuzmenko.Sii_task.currency.Currency;
import com.github.skuzmenko.Sii_task.event.model.FundraisingEvent;
import com.github.skuzmenko.Sii_task.event.service.FundraisingEventService;
import com.github.skuzmenko.Sii_task.exception.AbsentRecordException;
import com.github.skuzmenko.Sii_task.exception.CustomIllegalArgException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
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


    public CollectionBoxDTO addBox(){
        return  boxRepository.save(new CollectionBox()).toDTO();
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
        // if the box was previously assigned to an event - remove from the boxes list
        FundraisingEvent event = box.getEvent();
        if (event!=null) {
            event.getBoxes().remove(box);
            eventService.saveEvent(event);
        }
        // deleting the box from the database
        boxRepository.delete(box);
    }

    public CollectionBoxDTO donateToBox(Long boxId, DonationDTO donationDTO)
    {
        CollectionBox box = getBox(boxId);
        String currency = donationDTO.currency();
        Currency.verifyCurrency(currency);
        BigDecimal amount = donationDTO.amount();
        if (amount.compareTo(BigDecimal.ZERO)<=0)
            throw new CustomIllegalArgException("Amount of the donation should be greater than 0");
        switch(currency)
        {
            case "PLN":
                box.setPlnAmount(box.getPlnAmount().add(amount, Currency.context));
                break;
            case "EUR":
                box.setEuroAmount(box.getEuroAmount().add(amount, Currency.context));
                break;
            case "USD":
                box.setUsdAmount(box.getUsdAmount().add(amount, Currency.context));
                break;

        }
        return boxRepository.save(box).toDTO();
    }

    public CollectionBoxDTO assignBox(Long boxId, AssignBoxDTO assignBoxDTO)
    {
        CollectionBox box = getBox(boxId);
        // verifying box has no funds inside
        if (!box.isEmpty())
            throw new CustomIllegalArgException(String.format("Box with id '%s' is not empty",boxId));
        // retrieving the event box has been assigned to before
        FundraisingEvent oldOwner = box.getEvent();
        if (oldOwner !=null)
        {
            // trying to assign the box to the same event, no changes needed
            if (oldOwner.getId().equals(assignBoxDTO.eventId()))
                return box.toDTO();
            // removing from the assigned boxes and saving the modified event
            oldOwner.getBoxes().remove(box);
            eventService.saveEvent(oldOwner);
        }
        //retrieving the new owner event
        FundraisingEvent newOwner = eventService.getEvent(assignBoxDTO.eventId());
        box.setEvent(newOwner);
        newOwner.getBoxes().add(box);
        // saving the changes to the event
        eventService.saveEvent(newOwner);
        // saving the changes to the box
        return boxRepository.save(box).toDTO();
    }

    public CollectionBoxDTO transferFunds(Long boxId) {
        CollectionBox box = getBox(boxId);
        FundraisingEvent event = box.getEvent();
        if (event == null)
            throw new CustomIllegalArgException(String.format("Box with id '%s' is not assigned to an event",boxId));
        BigDecimal funds = Currency.getSumInCurrency(event.getCurrency(),
                box.getPlnAmount(),
                box.getEuroAmount(),
                box.getUsdAmount());
        event.setAccount(event.getAccount().add(funds));
        eventService.saveEvent(event);
        // emptying out the box
        box.setEmptyAmounts();
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
