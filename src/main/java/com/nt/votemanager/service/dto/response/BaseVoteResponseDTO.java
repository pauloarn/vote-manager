package com.nt.votemanager.service.dto.response;

import com.nt.votemanager.enums.VoteEnum;
import com.nt.votemanager.model.AgendaVote;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseVoteResponseDTO {
    public String voter;
    public VoteEnum vote;

    public BaseVoteResponseDTO(AgendaVote vote) {
        this.voter = vote.getVoter();
        this.vote = vote.getVote();
    }
}
