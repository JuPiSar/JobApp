package com.QT.JobWeb.service;

import com.QT.JobWeb.dto.ClubDTO;
import com.QT.JobWeb.models.Club;

import java.util.List;

public interface ClubService {
    List<ClubDTO> findAllClub();

    Club save(ClubDTO club);

    ClubDTO fingClubById(Long id);

    void updateClub(ClubDTO club);

    void delete(Long id);

    List<ClubDTO> searchClub(String keyword);
}
