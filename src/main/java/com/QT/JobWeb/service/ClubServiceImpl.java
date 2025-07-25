package com.QT.JobWeb.service;


import com.QT.JobWeb.dto.ClubDTO;
import com.QT.JobWeb.models.Club;
import com.QT.JobWeb.models.UserEntity;
import com.QT.JobWeb.repo.ClubRepo;
import com.QT.JobWeb.repo.UserRepo;
import com.QT.JobWeb.securityConfig.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.QT.JobWeb.mapper.ClubMapper.mapToClub;
import static com.QT.JobWeb.mapper.ClubMapper.mapToClubDto;

@Service
public class ClubServiceImpl implements ClubService {

    @Autowired
    private ClubRepo clubRepo;
    @Autowired
    private UserRepo userRepo;


    @Override
    public List<ClubDTO> findAllClub() {
        List<Club> clubs =  clubRepo.findAll();
        return clubs.stream().map((club) -> mapToClubDto(club)).collect(Collectors.toList());
    }

    @Override
    public Club save(ClubDTO clubDTO){
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepo.findByUsername(username);
        Club club = mapToClub(clubDTO);
        club.setCreatedBy(user);
        return clubRepo.save(club);
    };

    @Override
    public ClubDTO fingClubById(Long id){
        Club club = clubRepo.findById(id).get();
        return mapToClubDto(club);
    }

    @Override
    public void updateClub(ClubDTO clubDTO){
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepo.findByUsername(username);
        Club club = mapToClub(clubDTO);
        club.setCreatedBy(user);
        clubRepo.save(club);
    }

    @Override
    public void delete(Long id){
        clubRepo.deleteById(id);
    }

    @Override
    public List<ClubDTO> searchClub(String keyword) {
        List<Club> clubData = clubRepo.searchClub(keyword);
        return clubData.stream().map(data ->mapToClubDto(data)).collect(Collectors.toList());
    }



}
