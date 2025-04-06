package com.nt.votemanager.service.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAgendaRequestDTO {
    @NotNull
    @Size(min = 6, max = 255)
    private String title;

    private String description;
}
