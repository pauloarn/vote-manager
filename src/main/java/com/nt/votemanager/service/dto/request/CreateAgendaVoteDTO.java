package com.nt.votemanager.service.dto.request;

import com.nt.votemanager.enums.VoteEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateAgendaVoteDTO {
    private String voterCpf;
    private VoteEnum vote;
}
