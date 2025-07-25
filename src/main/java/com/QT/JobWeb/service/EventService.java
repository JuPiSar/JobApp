package com.QT.JobWeb.service;

import com.QT.JobWeb.dto.EventDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EventService {
    void createEvent(Long clubId, EventDTO eventDTO);

    List<EventDTO> findAllEvents();

    EventDTO findEvent(Long eventId);

    EventDTO findEventById(Long id);

    void updateEvent(@Valid EventDTO event);

    void deleteEvent(Long id);
}
