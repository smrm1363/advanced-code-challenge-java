package com.statista.code.challenge.domain.model.department;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record Department(
    @NotNull(message = "Timestamp is required") UUID id, @NotBlank String name,
    @NotNull DepartmentStrategy departmentStrategy
) {

}
