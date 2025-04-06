package com.nt.votemanager.service.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StartAgendaVottingSessionDTO {
    @Schema(description = "Votting Session Duration in formated string like '1d 2h 3m 4s'")
    public String vottingSessionDuration;
}
