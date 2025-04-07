package com.nt.votemanager.service.dto.request;

import com.nt.votemanager.enums.VoteEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAgendaVoteDTO {
    private String voterCpf;
    private VoteEnum vote;
}
