package com.QT.JobWeb.mapper;

import com.QT.JobWeb.dto.EventDTO;
import com.QT.JobWeb.models.Event;

public class EventMapper {

    public static Event maptoEvent(EventDTO eventDTO) {
        Event event = Event.builder()
                .id(eventDTO.getId())
                .name(eventDTO.getName())
                .startTime(eventDTO.getStartTime())
                .endTime(eventDTO.getEndTime())
                .PhotoUrl(eventDTO.getPhotoUrl())
                .type(eventDTO.getType())
                .createdOn(eventDTO.getCreatedOn())
                .updatedOn(eventDTO.getUpdatedOn())
                .club(eventDTO.getClub())
                .build();

        return event;
    }

    public static EventDTO maptoEventDto(Event event) {
        EventDTO eventDTO = EventDTO.builder()
                .id(event.getId())
                .name(event.getName())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .PhotoUrl(event.getPhotoUrl())
                .type(event.getType())
                .createdOn(event.getCreatedOn())
                .updatedOn(event.getUpdatedOn())
                .club(event.getClub())
                .build();
        return eventDTO;
    }


}
