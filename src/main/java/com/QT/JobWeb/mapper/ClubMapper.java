package com.QT.JobWeb.mapper;

import com.QT.JobWeb.dto.ClubDTO;
import com.QT.JobWeb.models.Club;

import java.util.stream.Collectors;

import static com.QT.JobWeb.mapper.EventMapper.maptoEventDto;

public class ClubMapper {

    public static Club mapToClub(ClubDTO clubDTO){
        Club club = Club.builder()
                .id(clubDTO.getId())
                .title(clubDTO.getTitle())
                .content(clubDTO.getContent())
                .photoUrl(clubDTO.getPhotoUrl())
                .createdBy(clubDTO.getCreatedBy())
                .build();
        return club;
    }

    public static ClubDTO mapToClubDto(Club club) {
        ClubDTO clubDTO = ClubDTO.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createdDate(club.getCreatedDate())
                .updatedDate(club.getUpdatedDate())
                .createdBy(club.getCreatedBy())
                .events(club.getEvents().stream().map((event -> maptoEventDto(event))).collect(Collectors.toList()))
                .build();
        return clubDTO;
    }

}
