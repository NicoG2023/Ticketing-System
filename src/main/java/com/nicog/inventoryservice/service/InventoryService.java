package com.nicog.inventoryservice.service;

import com.nicog.inventoryservice.entity.Event;
import com.nicog.inventoryservice.repository.EventRepository;
import com.nicog.inventoryservice.repository.VenueRepository;
import org.springframework.stereotype.Service;
import com.nicog.inventoryservice.response.EventInventoryResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;

    public InventoryService(final EventRepository eventRepository, final VenueRepository venueRepository){
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
    }

    public List<EventInventoryResponse> getAllEvents(){
        final List<Event> events = eventRepository.findAll();

        return events.stream().map(event -> EventInventoryResponse.builder().event(event.getName())
                .capacity(event.getLeftCapacity())
                .venue(event.getVenue())
                .build()).collect(Collectors.toList());
    }
    
}
