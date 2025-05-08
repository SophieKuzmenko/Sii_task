package com.github.skuzmenko.Sii_task.event.service;

import com.github.skuzmenko.Sii_task.currency.Currency;
import com.github.skuzmenko.Sii_task.event.dto.CreateEventDTO;
import com.github.skuzmenko.Sii_task.event.dto.FundraisingEventDTO;
import com.github.skuzmenko.Sii_task.event.model.FundraisingEvent;
import com.github.skuzmenko.Sii_task.event.repository.FundraisingEventRepository;
import com.github.skuzmenko.Sii_task.exception.AbsentRecordException;
import com.github.skuzmenko.Sii_task.exception.RecordAlreadyPresentException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Boolean eventPresent(String name)
    {
        return eventRepository.findByName(name).isPresent();
    }

    public FundraisingEvent getEventByName(String name)
    {
        Optional<FundraisingEvent> eventOptional = eventRepository.findByName(name);
        if (eventOptional.isEmpty())
            throw new AbsentRecordException(String.format("Event with name '%s' was not found",name));
        return eventOptional.get();
    }

    public void saveEvent(FundraisingEvent event)
    {
        eventRepository.save(event);
    }

    public List<String> getAllReports() {
        List<FundraisingEvent> events = eventRepository.findAll();
        List<String> res = new ArrayList<>();
        events.forEach(event->res.add(event.getReport()));
        return res;
    }

}
