package com.github.skuzmenko.Sii_task.event.service;

import com.github.skuzmenko.Sii_task.event.dto.CreateEventDTO;
import com.github.skuzmenko.Sii_task.event.dto.FundraisingEventDTO;
import com.github.skuzmenko.Sii_task.event.model.FundraisingEvent;
import com.github.skuzmenko.Sii_task.event.repository.FundraisingEventRepository;
import com.github.skuzmenko.Sii_task.exception.CustomIllegalArgException;
import com.github.skuzmenko.Sii_task.exception.RecordAlreadyPresentException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundraisingEventService {
    private final FundraisingEventRepository eventRepository;
    private final List<String> currencies = List.of("PLN", "USD", "EUR");

    public FundraisingEventService(FundraisingEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public FundraisingEventDTO addEvent(CreateEventDTO createEventDTO)
    {
        String currency = createEventDTO.getCurrency();
        if ((currency.length()!=3)|| !currencies.contains(currency))
            throw new CustomIllegalArgException("Currency should be one of the following:'PLN' , 'USD' or 'EUR");
        // verifying an event with such name doesn't already exist
        String name = createEventDTO.getName();
        if (eventRepository.findByName(name).isPresent())
            throw new RecordAlreadyPresentException(String.format("Fundraising event with name '%s' is already present",name));
        FundraisingEvent event = new FundraisingEvent(name, currency);
        eventRepository.save(event);
        return event.toDTO();
    }
}
