package com.nt.votemanager.service;

import com.nt.votemanager.enums.MessageEnum;
import com.nt.votemanager.exceptions.ApiErrorException;
import com.nt.votemanager.model.Agenda;
import com.nt.votemanager.repository.AgendaRepository;
import com.nt.votemanager.service.dto.request.CreateAgendaRequestDTO;
import com.nt.votemanager.service.dto.request.StartAgendaVottingSessionDTO;
import com.nt.votemanager.service.dto.response.AgendaResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@ActiveProfiles("test")
class AgendaServiceTest {

    @InjectMocks
    private AgendaService agendaService;

    @Mock
    private AgendaRepository agendaRepository;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void shouldCreateAgendaSuccessfully() {
        // Preparação
        CreateAgendaRequestDTO createAgendaRequestDTO = new CreateAgendaRequestDTO("Title", "Description");
        Agenda savedAgenda = new Agenda();
        savedAgenda.setTitle("Title");
        savedAgenda.setDescription("Description");

        when(agendaRepository.save(any(Agenda.class))).thenReturn(savedAgenda);

        // Execução
        AgendaResponseDTO response = agendaService.createAgenda(createAgendaRequestDTO);


        // Verificações
        assertNotNull(response);
        assertEquals(createAgendaRequestDTO.getTitle(), response.getTitle());
        verify(agendaRepository, times(1)).save(any(Agenda.class));
    }

    @Test
    void shouldThrowExceptionWhenAgendaNotFound() {
        // Preparação
        Integer invalidId = 999;
        when(agendaRepository.findById(invalidId)).thenReturn(Optional.empty());

        // Execução / Validação
        ApiErrorException exception = assertThrows(ApiErrorException.class, () ->
                agendaService.getAgendaDTOById(invalidId));
        assertEquals(MessageEnum.AGENDA_NOT_FOUND, exception.getMessageApiEnum());
    }

    @Test
    void shouldStartVotingSessionSuccessfully() throws ApiErrorException {
        // Preparação
        Integer agendaId = 1;
        StartAgendaVottingSessionDTO votingSessionDTO = new StartAgendaVottingSessionDTO("1d 2h 3m 50s");
        Agenda agenda = new Agenda();

        when(agendaRepository.findById(agendaId)).thenReturn(Optional.of(agenda));

        // Execução
        AgendaResponseDTO response = agendaService.initiateAgendaVotingSession(agendaId, votingSessionDTO);

        // Verificações
        assertNotNull(response);
        assertNotNull(response.getStartAt());
        assertNotNull(response.getEndAt());
    }
}