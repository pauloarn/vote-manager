package com.nt.votemanager.service.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateAgendaRequestDTO {
    @NotNull
    @Size(min = 6, max = 255)
    private String title;

    private String description;
}
