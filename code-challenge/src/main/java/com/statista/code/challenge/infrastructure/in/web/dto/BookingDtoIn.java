package com.statista.code.challenge.infrastructure.in.web.dto;

import com.statista.code.challenge.domain.model.Currency;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BookingDtoIn(
    String description, BigDecimal price, Currency currency, LocalDate subscriptionStartDate,
    String email, String departmentName
) {

}
