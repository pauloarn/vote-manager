package com.nt.votemanager.service;

import com.nt.votemanager.enums.MessageEnum;
import com.nt.votemanager.exceptions.ApiErrorException;
import com.nt.votemanager.model.Agenda;
import com.nt.votemanager.repository.AgendaRepository;
import com.nt.votemanager.service.dto.request.CreateAgendaRequestDTO;
import com.nt.votemanager.service.dto.request.StartAgendaVottingSessionDTO;
import com.nt.votemanager.service.dto.response.AgendaResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.nt.votemanager.utils.DateUtils.getEndDateFromFormatedString;


@Service
@Log4j2
public class AgendaService extends AbstractServiceRepo<AgendaRepository, Agenda, Integer> {

    public AgendaService(AgendaRepository repository) {
        super(repository);
    }

    public AgendaResponseDTO createAgenda(CreateAgendaRequestDTO agenda) {
        Agenda newAgenda = createAgendaDTOtoAgenda(agenda);
        this.save(newAgenda);
        return new AgendaResponseDTO(newAgenda);
    }

    public AgendaResponseDTO getAgendaDTOById(Integer id) throws ApiErrorException {
        Agenda agenda = getAgendaBydId(id);
        return new AgendaResponseDTO(agenda);
    }

    public List<Agenda> findFinishedAndNotSyncedAgendas() {
        LocalDateTime now = LocalDateTime.now();
        List<Agenda> finishedAgendas = repository.findAgendaFinishedLastMinute(now);
        finishedAgendas.forEach(a -> {
            a.setHasSynced(true);
            save(a);
        });
        return finishedAgendas;
    }


    public AgendaResponseDTO initiateAgendaVotingSession(Integer id, StartAgendaVottingSessionDTO vottingSessionDTO) throws ApiErrorException {
        Agenda agenda = getAgendaBydId(id);
        validateAgendaCanBeStarted(agenda);
        agenda.setStartAt(LocalDateTime.now());
        var endDateTime = getEndDateFromFormatedString(vottingSessionDTO.vottingSessionDuration);
        agenda.setEndAt(endDateTime);
        save(agenda);
        return new AgendaResponseDTO(agenda);
    }

    public Agenda getAgentaVorVote(Integer id) throws ApiErrorException {
        Agenda agenda = this.getAgendaBydId(id);
        validateAgendaIsOpen(agenda);
        return agenda;
    }

    private void validateAgendaIsOpen(Agenda agenda) throws ApiErrorException {
        if (isNull(agenda.getStartAt()) || isNull(agenda.getEndAt())) {
            throwBadRequest(MessageEnum.AGENDA_VOTE_SESSION_NOT_STARTED);
        }
        if (LocalDateTime.now().isBefore(agenda.getStartAt())) {
            throwBadRequest(MessageEnum.AGENDA_VOTE_SESSION_NOT_STARTED);
        }
        if (LocalDateTime.now().isAfter(agenda.getEndAt())) {
            throwBadRequest(MessageEnum.AGENDA_VOTE_SESSION_ALREADY_ENDED);
        }
    }

    private void validateAgendaCanBeStarted(Agenda agenda) throws ApiErrorException {
        if (validateSessionStarted(agenda)) {
            throwBadRequest(MessageEnum.AGENDA_VOTE_SESSION_ALREADY_STARTED);
        }
        if (validateSessionEnded(agenda)) {
            throwBadRequest(MessageEnum.AGENDA_VOTE_SESSION_ALREADY_ENDED);
        }
    }

    private boolean validateSessionEnded(Agenda agenda) {
        if (isNull(agenda.getStartAt())) {
            return false;
        }
        return agenda.getEndAt().isAfter(LocalDateTime.now());
    }

    private boolean validateSessionStarted(Agenda agenda) {
        if (isNull(agenda.getStartAt())) {
            return false;
        }
        return agenda.getStartAt().isBefore(LocalDateTime.now());
    }

    private Agenda getAgendaBydId(Integer id) throws ApiErrorException {
        return repository.findById(id)
                .orElseThrow(() -> new ApiErrorException(HttpStatus.BAD_REQUEST, MessageEnum.AGENDA_NOT_FOUND));
    }

    private Agenda createAgendaDTOtoAgenda(CreateAgendaRequestDTO agendaRequestDTO) {
        Agenda agenda = new Agenda();
        agenda.setTitle(agendaRequestDTO.getTitle());
        agenda.setDescription(agendaRequestDTO.getDescription());
        return agenda;
    }
}
