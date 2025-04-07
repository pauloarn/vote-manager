package com.nt.votemanager.service.dto.response;

import com.nt.votemanager.model.Agenda;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class AgendaResponseDTO extends BaseAgendaResponseDTO {

    private List<BaseVoteResponseDTO> votes = new ArrayList<>();

    public AgendaResponseDTO(Agenda agenda) {
        super(agenda);
        this.id = agenda.getId();
        this.title = agenda.getTitle();
        this.description = agenda.getDescription();
        this.startAt = agenda.getStartAt();
        this.endAt = agenda.getEndAt();
        this.votes = agenda.getVotes().stream().map(BaseVoteResponseDTO::new).collect(Collectors.toList());
    }
}
