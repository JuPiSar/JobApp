package com.QT.JobWeb.controller;

import com.QT.JobWeb.dto.ClubDTO;
import com.QT.JobWeb.dto.EventDTO;
import com.QT.JobWeb.models.Club;
import com.QT.JobWeb.models.UserEntity;
import com.QT.JobWeb.securityConfig.SecurityUtil;
import com.QT.JobWeb.service.ClubService;
import com.QT.JobWeb.service.EventService;
import com.QT.JobWeb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClubController {

    @Autowired
    private UserService userService;

    @Autowired
    private ClubService clubService;


    @GetMapping("/clubs")
    public String club(Model model) {
        UserEntity user = new UserEntity();
        List<ClubDTO> clubs = clubService.findAllClub();
        String username = SecurityUtil.getSessionUser();
        if(username != null){
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("clubs", clubs);
        return "club-list";
    }


    @GetMapping("/clubs/new")
    public String createClubForm(Model model){
        Club club = new Club();
        model.addAttribute("club", club);
        return "club-create";
    }

    @PostMapping("/clubs/new")
    public String saveClub(@Valid @ModelAttribute("club") ClubDTO club
                            ,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "club-create";
        }
        clubService.save(club);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") Long Id, Model model){
        ClubDTO club = clubService.fingClubById(Id);
        model.addAttribute("club", club);
        return "club-edit";
    }

    @PostMapping("/clubs/{clubId}/edit")
    public String updateClubForm(@PathVariable("clubId") Long Id
                                 ,@Valid @ModelAttribute("club") ClubDTO club
                                 , BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "club-edit";
        }
        club.setId(Id);
        clubService.updateClub(club);
        return "redirect:/clubs";
    }



    @GetMapping("/clubs/{clubId}")
    public String clubDetail(@PathVariable("clubId")Long clubId, Model model){
        UserEntity user = new UserEntity();
        String username = SecurityUtil.getSessionUser();
        if(username != null){
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }

        model.addAttribute("user", user);
        ClubDTO clubDTO = clubService.fingClubById(clubId);
        model.addAttribute("club", clubDTO);
        return "club-detail";
    }

    @GetMapping("/clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") Long Id){
        clubService.delete(Id);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/search")
    public String searchClub(@RequestParam(value = "query")String query, Model model){
        List<ClubDTO> clubs = clubService.searchClub(query);
        model.addAttribute("clubs", clubs);
        return "club-list";
    }


}
