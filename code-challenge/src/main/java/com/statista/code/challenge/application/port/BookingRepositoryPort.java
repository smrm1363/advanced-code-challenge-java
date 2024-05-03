package com.statista.code.challenge.application.port;

import com.statista.code.challenge.domain.model.Booking;
import com.statista.code.challenge.domain.model.Currency;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Validated
public interface BookingRepositoryPort {

  Booking insertUpdate(@Valid Booking booking);

  Optional<Booking> findById(UUID id);

  List<Currency> findAllUsedCurrencies();

  List<Booking> findBookingsByDepartmentId(UUID departmentId);

  List<Booking> findAllBookingByCurrency(Currency currency);
}
