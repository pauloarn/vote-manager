package com.nt.votemanager.controller;

import com.nt.votemanager.exceptions.ApiErrorException;
import com.nt.votemanager.service.AgendaVoteService;
import com.nt.votemanager.service.dto.request.CreateAgendaVoteDTO;
import com.nt.votemanager.service.dto.response.AgendaVoteResponseDTO;
import com.nt.votemanager.service.dto.response.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("agenda-vote/{agendaId}")
@RequiredArgsConstructor
public class AgendaVoteController {


    private final AgendaVoteService agendaVoteService;

    @PostMapping("vote")
    public Response<AgendaVoteResponseDTO> createAgenda(
            @PathVariable("agendaId") Integer agendaId,
            @RequestBody @Valid CreateAgendaVoteDTO newAgendaDTO
    ) throws ApiErrorException {
        Response<AgendaVoteResponseDTO> response = new Response<>();
        response.setData(this.agendaVoteService.voteForAgenda(agendaId, newAgendaDTO));
        response.setOk();
        return response;
    }
}
