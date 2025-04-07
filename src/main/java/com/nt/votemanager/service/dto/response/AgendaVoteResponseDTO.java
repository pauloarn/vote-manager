package com.nt.votemanager.service.dto.response;

import com.nt.votemanager.model.AgendaVote;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendaVoteResponseDTO extends BaseVoteResponseDTO {
    private BaseAgendaResponseDTO agenda;

    public AgendaVoteResponseDTO(AgendaVote vote) {
        super(vote);
        this.agenda = new BaseAgendaResponseDTO(vote.getAgenda());
    }
}
