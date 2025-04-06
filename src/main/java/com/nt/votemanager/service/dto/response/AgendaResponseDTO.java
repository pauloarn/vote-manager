package com.nt.votemanager.service.dto.response;

import com.nt.votemanager.model.Agenda;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AgendaResponseDTO {
    private Integer id;
    private String title;
    private String description;
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    public AgendaResponseDTO(Agenda agenda) {
        this.id = agenda.getId();
        this.title = agenda.getTitle();
        this.description = agenda.getDescription();
        this.startAt = agenda.getStartAt();
        this.endAt = agenda.getEndAt();
    }
}
