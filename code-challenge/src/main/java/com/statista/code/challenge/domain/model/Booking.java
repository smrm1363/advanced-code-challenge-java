package com.statista.code.challenge.domain.model;

import com.statista.code.challenge.domain.model.department.Department;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record Booking(
    @NotNull(message = "The id of the booking could not be null") UUID id, String description,
    @NotNull(message = "The price of the booking could not be null") BigDecimal price,
    @NotNull(message = "The currency of the booking could not be null") Currency currency,
    @NotNull(message = "The subscription-start-date of the booking could not be null") LocalDate subscriptionStartDate,
    @NotBlank(message = "The email of the booking could not be blank") @Email(message = "The email format is not correct") String email,
    @NotNull(message = "The department of the booking could not be null") Department department
) {

  public Booking(
      String description, BigDecimal price, Currency currency, LocalDate subscriptionStartDate,
      String email, Department department
  ) {
    this(UUID.randomUUID(), description, price, currency, subscriptionStartDate, email, department);
  }
}
