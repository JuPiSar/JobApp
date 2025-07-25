package com.QT.JobWeb.repo;

import com.QT.JobWeb.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClubRepo extends JpaRepository<Club, Long> {

    Optional<Club> findByTitle(String url);

    @Query("SELECT c from Club c where lower(c.title) like lower(concat('%', :query, '%')) ")
    List<Club> searchClub(String query);
}
