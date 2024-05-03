package com.statista.code.challenge.application.usecase;

import com.statista.code.challenge.domain.model.Booking;
import com.statista.code.challenge.domain.model.Currency;
import com.statista.code.challenge.domain.model.department.Department;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingUseCase {

  Booking replaceOrAddBooking(Booking booking);

  Booking createNewBooking(Booking booking);

  Optional<Booking> findBooking(UUID id);

  List<UUID> findBookingIds(String departmentName);

  List<Department> findAllDepartments();

  List<Currency> findAllUsedCurrencies();

  BigDecimal getSumBookingPrice(Currency currency);

  Optional<String> doBusinessOfTheDepartment(UUID bookingId);
}
