package com.QT.JobWeb.repo;

import com.QT.JobWeb.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface EventRepo extends JpaRepository<Event, Long> {
}
