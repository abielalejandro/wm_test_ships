package com.rgarcia.w2m.application.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateSpaceShipDto {

    @NotNull
    @NotEmpty
    @Size(max = 100)
    String name;

    @NotNull
    @NotEmpty
    @Size(max = 255)
    String relatedTo;

}
