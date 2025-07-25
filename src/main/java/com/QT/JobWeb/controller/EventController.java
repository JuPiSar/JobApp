package com.QT.JobWeb.controller;


import com.QT.JobWeb.dto.ClubDTO;
import com.QT.JobWeb.dto.EventDTO;
import com.QT.JobWeb.models.Event;
import com.QT.JobWeb.models.UserEntity;
import com.QT.JobWeb.securityConfig.SecurityUtil;
import com.QT.JobWeb.service.ClubService;
import com.QT.JobWeb.service.EventService;
import com.QT.JobWeb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventController {

    private final ClubService clubService;
    private EventService eventService;
    private UserService userService;

    public EventController(ClubService clubService, EventService eventService, UserService userService) {
        this.clubService = clubService;
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping("/events")
    public String eventList(Model model) {
        UserEntity user = new UserEntity();
        String username = SecurityUtil.getSessionUser();
        if(username != null){
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        List<EventDTO> events = eventService.findAllEvents();
        model.addAttribute("events", events);
        return "events-list";
    }

    @GetMapping("/events/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") Long clubId, Model model) {

        Event event = new Event();
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", event);
        return "event-create";
    }

    @PostMapping("/events/{clubId}/new")
    public String saveEvent(@PathVariable("clubId") Long clubId, @ModelAttribute("event")EventDTO eventDTO){
        eventService.createEvent(clubId, eventDTO);
        return new StringBuilder()
                .append("redirect:/clubs/")
                .append(clubId)
                .toString();
    }

    @GetMapping("/events/{eventId}")
    public String viewEvent(@PathVariable("eventId") Long eventId, Model model){
        UserEntity user = new UserEntity();
        String username = SecurityUtil.getSessionUser();
        if(username != null){
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        EventDTO eventDTO = eventService.findEvent(eventId);
        model.addAttribute("event", eventDTO);
        return "event-detail";
    }


    //edit
    @GetMapping("/events/{eventId}/edit")
    public String editClubForm(@PathVariable("eventId") Long Id, Model model){
        EventDTO event = eventService.findEventById(Id);
        model.addAttribute("event", event);
        return "event-edit";
    }

    @PostMapping("/events/{eventId}/edit")
    public String updateClubForm(@PathVariable("eventId") Long Id
            ,@Valid @ModelAttribute("event") EventDTO event
            , BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "event-edit";
        }
        event.setId(Id);
        eventService.updateEvent(event);
        return "redirect:/events/" + event.getId();
    }

    //delete
    @GetMapping("/events/{eventId}/delete")
    public String deleteClub(@PathVariable("eventId") Long Id){
        EventDTO event = eventService.findEventById(Id);
        eventService.deleteEvent(Id);
        return "redirect:/clubs/" + event.getClub().getId();
    }

}
