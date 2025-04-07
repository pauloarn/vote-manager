package com.nt.votemanager.service.dto.response;

import com.nt.votemanager.model.Agenda;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseAgendaResponseDTO {
    public Integer id;
    public String title;
    public String description;
    public LocalDateTime startAt;
    public LocalDateTime endAt;

    public BaseAgendaResponseDTO(Agenda agenda) {
        this.id = agenda.getId();
        this.title = agenda.getTitle();
        this.description = agenda.getDescription();
        this.startAt = agenda.getStartAt();
        this.endAt = agenda.getEndAt();
    }
}
