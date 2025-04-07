package com.nt.votemanager.service;

import com.nt.votemanager.enums.MessageEnum;
import com.nt.votemanager.enums.VoteEnum;
import com.nt.votemanager.exceptions.ApiErrorException;
import com.nt.votemanager.model.Agenda;
import com.nt.votemanager.model.AgendaVote;
import com.nt.votemanager.repository.AgendaVoteRepository;
import com.nt.votemanager.service.dto.request.CreateAgendaVoteDTO;
import com.nt.votemanager.service.dto.response.AgendaVoteResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@ActiveProfiles("test")
class AgendaVoteServiceTest {

    @InjectMocks
    private AgendaVoteService agendaVoteService;

    @Mock
    private AgendaVoteRepository agendaVoteRepository;

    @Mock
    private AgendaService agendaService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void shouldVoteSuccessfully() throws ApiErrorException {
        // Preparação
        Integer agendaId = 1;
        CreateAgendaVoteDTO voteDTO = new CreateAgendaVoteDTO("12345678901", VoteEnum.SIM);
        Agenda agenda = new Agenda();

        when(agendaService.getAgentaVorVote(agendaId)).thenReturn(agenda);
        when(agendaVoteRepository.findByVoterAndAgenda_Id(voteDTO.getVoterCpf(), agendaId)).thenReturn(Optional.empty());

        AgendaVote savedVote = new AgendaVote();
        savedVote.setVoter(voteDTO.getVoterCpf());
        savedVote.setVote(voteDTO.getVote());
        when(agendaVoteRepository.save(any(AgendaVote.class))).thenReturn(savedVote);

        // Execução
        AgendaVoteResponseDTO response = agendaVoteService.voteForAgenda(agendaId, voteDTO);

        // Verificações
        assertNotNull(response);
        assertEquals(voteDTO.getVoterCpf(), response.getVoter());
        assertEquals(voteDTO.getVote(), response.getVote());
        verify(agendaVoteRepository, times(1)).save(any(AgendaVote.class));
    }

    @Test
    void shouldThrowExceptionWhenVoterAlreadyVoted() {
        // Preparação
        Integer agendaId = 1;
        CreateAgendaVoteDTO voteDTO = new CreateAgendaVoteDTO("12345678901", VoteEnum.SIM);
        when(agendaVoteRepository.findByVoterAndAgenda_Id(voteDTO.getVoterCpf(), agendaId))
                .thenReturn(Optional.of(new AgendaVote()));

        // Execução / Validação
        ApiErrorException exception = assertThrows(ApiErrorException.class, () ->
                agendaVoteService.voteForAgenda(agendaId, voteDTO));
        assertEquals(MessageEnum.VOTE_ALREADY_VOTED_IN_SESSION, exception.getMessageApiEnum());
    }
}