package com.nt.votemanager.repository;

import com.nt.votemanager.model.AgendaVote;

import java.util.Optional;

public interface AgendaVoteRepository extends GenericRepository<AgendaVote, Integer> {
    Optional<AgendaVote> findByVoterAndAgenda_Id(String voter, Integer agendaId);
}
