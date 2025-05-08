package com.github.skuzmenko.Sii_task.event.service;

import com.github.skuzmenko.Sii_task.currency.Currency;
import com.github.skuzmenko.Sii_task.event.dto.CreateEventDTO;
import com.github.skuzmenko.Sii_task.event.dto.FundraisingEventDTO;
import com.github.skuzmenko.Sii_task.event.model.FundraisingEvent;
import com.github.skuzmenko.Sii_task.event.repository.FundraisingEventRepository;
import com.github.skuzmenko.Sii_task.exception.AbsentRecordException;
import com.github.skuzmenko.Sii_task.exception.CustomIllegalArgException;
import com.github.skuzmenko.Sii_task.exception.RecordAlreadyPresentException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FundraisingEventService {
    private final FundraisingEventRepository eventRepository;

    public FundraisingEventService(FundraisingEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public FundraisingEventDTO addEvent(CreateEventDTO createEventDTO)
    {
        String currency = createEventDTO.currency();
        Currency.verifyCurrency(currency);
        // verifying an event with such name doesn't already exist
        String name = createEventDTO.name();
        if (eventPresent(name))
            throw new RecordAlreadyPresentException(String.format("Fundraising event with name '%s' is already present",name));
        FundraisingEvent event = new FundraisingEvent(name, currency);
        eventRepository.save(event);
        return event.toDTO();
    }
    public Boolean eventPresent(Long id)
    {
        return eventRepository.findById(id).isPresent();
    }
    public Boolean eventPresent(String name)
    {
        return eventRepository.findByName(name).isPresent();
    }
    public FundraisingEvent getEvent(Long id)
    {
        Optional<FundraisingEvent> eventOptional = eventRepository.findById(id);
        if (eventOptional.isEmpty())
            throw new AbsentRecordException(String.format("Event with id '%s' not found",id));
        return eventOptional.get();
    }
    public void saveEvent(FundraisingEvent event)
    {
        eventRepository.save(event);
    }

}
