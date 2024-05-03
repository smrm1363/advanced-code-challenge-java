package com.statista.code.challenge.infrastructure.in.web.dto;

import com.statista.code.challenge.domain.model.Currency;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record BookingDtoOut(
    UUID id, String description, BigDecimal price, Currency currency,
    LocalDate subscriptionStartDate, String email, String departmentName
) {

}
