package com.nt.votemanager.controller;

import com.nt.votemanager.exceptions.ApiErrorException;
import com.nt.votemanager.service.AgendaService;
import com.nt.votemanager.service.dto.request.CreateAgendaRequestDTO;
import com.nt.votemanager.service.dto.request.StartAgendaVottingSessionDTO;
import com.nt.votemanager.service.dto.response.AgendaResponseDTO;
import com.nt.votemanager.service.dto.response.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("agenda")
@RequiredArgsConstructor
public class AgendaController {

    private final AgendaService agendaService;

    @PostMapping("create")
    public Response<AgendaResponseDTO> createAgenda(
            @RequestBody @Valid CreateAgendaRequestDTO newAgendaDTO
    ) {
        Response<AgendaResponseDTO> response = new Response<>();
        response.setData(this.agendaService.createAgenda(newAgendaDTO));
        response.setOk();
        return response;
    }

    @GetMapping("/{id}")
    public Response<AgendaResponseDTO> findAgendaById(
            @PathVariable("id") Integer agendaId
    ) throws ApiErrorException {
        Response<AgendaResponseDTO> response = new Response<>();
        response.setData(this.agendaService.getAgendaDTOById(agendaId));
        response.setOk();
        return response;
    }

    @PatchMapping("/{id}/start-agenda-voting-session")
    public Response<AgendaResponseDTO> startAgendaVotingSession(
            @RequestBody @Valid StartAgendaVottingSessionDTO vottingSessionDTO,
            @PathVariable("id") Integer agendaId
    ) throws ApiErrorException {
        Response<AgendaResponseDTO> response = new Response<>();
        response.setData(this.agendaService.initiateAgendaVotingSession(agendaId, vottingSessionDTO));
        response.setOk();
        return response;
    }
}