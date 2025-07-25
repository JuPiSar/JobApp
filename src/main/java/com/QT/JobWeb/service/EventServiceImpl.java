package com.QT.JobWeb.service;

import com.QT.JobWeb.dto.EventDTO;
import com.QT.JobWeb.models.Club;
import com.QT.JobWeb.models.Event;
import com.QT.JobWeb.repo.ClubRepo;
import com.QT.JobWeb.repo.EventRepo;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.QT.JobWeb.mapper.EventMapper.maptoEvent;
import static com.QT.JobWeb.mapper.EventMapper.maptoEventDto;

@Service
public class EventServiceImpl implements EventService{

    private EventRepo eventRepo;
    private ClubRepo clubRepo;

    public EventServiceImpl(EventRepo eventRepo, ClubRepo clubRepo) {
        this.eventRepo = eventRepo;
        this.clubRepo = clubRepo;
    }

    @Override
    public void createEvent(Long clubId, EventDTO eventDTO) {
        Club club = clubRepo.findById(clubId).get();
        Event event = maptoEvent(eventDTO);
        event.setClub(club);
        eventRepo.save(event);
    }

    @Override
    public List<EventDTO> findAllEvents() {
        List<Event> events = eventRepo.findAll();
        return events.stream().map((event) -> maptoEventDto(event)).collect(Collectors.toList());

    }

    @Override
    public EventDTO findEvent(Long eventId) {
        Event event = eventRepo.findById(eventId).get();
        return maptoEventDto(event);
    }

    @Override
    public EventDTO findEventById(Long id) {
        Event event = eventRepo.findById(id).get();
        return maptoEventDto(event);
    }

    @Override
    public void updateEvent(EventDTO eventDto) {
        Event event = maptoEvent(eventDto);
        eventRepo.save(event);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepo.deleteById(id);
    }


}
