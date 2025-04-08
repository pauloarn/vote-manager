package com.nt.votemanager.messaging.producer.dto;

import com.nt.votemanager.enums.VoteEnum;
import com.nt.votemanager.model.Agenda;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotifyAgendaResultDTO {
    private Integer agendaId;
    private String title;
    private String description;
    private Integer positiveVotes;
    private Integer negativeVotes;

    public NotifyAgendaResultDTO(Agenda agenda) {
        this.title = agenda.getTitle();
        this.agendaId = agenda.getId();
        this.description = agenda.getDescription();
        this.positiveVotes = (int) agenda.getVotes().stream()
                .filter(v -> v.getVote().equals(VoteEnum.SIM))
                .count();

        this.negativeVotes = (int) agenda.getVotes().stream()
                .filter(v -> v.getVote().equals(VoteEnum.NAO))
                .count();
    }
}
