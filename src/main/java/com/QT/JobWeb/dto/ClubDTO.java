package com.QT.JobWeb.dto;

import com.QT.JobWeb.models.Event;
import com.QT.JobWeb.models.UserEntity;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClubDTO {

    private Long id;

    @NotEmpty(message = "Title should not be empty")
    private String title;

    @NotEmpty(message = "PhotoUrl should not be empty")
    private String photoUrl;

    @NotEmpty(message = "Content should not be empty")
    private String content;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private List<EventDTO> events;

    private UserEntity createdBy;

}
