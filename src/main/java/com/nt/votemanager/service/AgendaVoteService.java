package com.nt.votemanager.service;

import com.nt.votemanager.enums.MessageEnum;
import com.nt.votemanager.exceptions.ApiErrorException;
import com.nt.votemanager.model.Agenda;
import com.nt.votemanager.model.AgendaVote;
import com.nt.votemanager.repository.AgendaVoteRepository;
import com.nt.votemanager.service.dto.request.CreateAgendaVoteDTO;
import com.nt.votemanager.service.dto.response.AgendaVoteResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class AgendaVoteService extends AbstractServiceRepo<AgendaVoteRepository, AgendaVote, Integer> {


    private AgendaService agendaService;

    public AgendaVoteService(AgendaVoteRepository repository, AgendaService _agendaService) {
        super(repository);
        this.agendaService = _agendaService;
    }

    private static AgendaVote getAgendaVote(CreateAgendaVoteDTO voteDTO, Agenda agenda) {
        AgendaVote vote = new AgendaVote();
        vote.setAgenda(agenda);
        vote.setVoter(voteDTO.getVoterCpf());
        vote.setVote(voteDTO.getVote());
        return vote;
    }

    public AgendaVoteResponseDTO voteForAgenda(Integer agendaId, CreateAgendaVoteDTO voteDTO) throws ApiErrorException {
        Agenda agenda = this.agendaService.getAgentaVorVote(agendaId);
        validateVoter(agendaId, voteDTO);
        AgendaVote vote = getAgendaVote(voteDTO, agenda);
        this.save(vote);
        return new AgendaVoteResponseDTO(vote);
    }

    private void validateVoter(Integer agendaId, CreateAgendaVoteDTO voteDTO) throws ApiErrorException {
        Optional<AgendaVote> vote = repository.findByVoterAndAgenda_Id(voteDTO.getVoterCpf(), agendaId);
        if (vote.isPresent()) {
            throwBadRequest(MessageEnum.VOTE_ALREADY_VOTED_IN_SESSION);
        }

    }

}
