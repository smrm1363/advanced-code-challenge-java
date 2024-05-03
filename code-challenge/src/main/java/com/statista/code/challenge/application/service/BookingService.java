package com.statista.code.challenge.application.service;

import com.statista.code.challenge.application.port.BookingRepositoryPort;
import com.statista.code.challenge.application.port.NotificationPort;
import com.statista.code.challenge.application.usecase.BookingUseCase;
import com.statista.code.challenge.application.usecase.DepartmentUseCase;
import com.statista.code.challenge.domain.model.Booking;
import com.statista.code.challenge.domain.model.Currency;
import com.statista.code.challenge.domain.model.department.Department;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService implements BookingUseCase {

  private final BookingRepositoryPort bookingRepositoryPort;
  private final DepartmentUseCase departmentUseCase;
  private final NotificationPort notificationPort;

  @Override
  public Booking replaceOrAddBooking(Booking booking) {
    return bookingRepositoryPort.insertUpdate(booking);
  }

  @Override
  public Booking createNewBooking(Booking booking) {
    Booking storedBooking = bookingRepositoryPort.insertUpdate(booking);
    notificationPort.send(String.format("The booking stored with id : %s", storedBooking.id()),
        storedBooking.email()
    );
    log.info(
        "The new booking stored successfully with the id %s and an email sent to the email address %s",
        storedBooking.id(), storedBooking.email()
    );
    return storedBooking;
  }

  @Override
  public Optional<Booking> findBooking(UUID id) {
    return bookingRepositoryPort.findById(id);
  }

  @Override
  public List<UUID> findBookingIds(String departmentName) {
    Optional<Department> departmentOptional = departmentUseCase.findByDepartment(departmentName);
    if (departmentOptional.isPresent()) {
      List<Booking> bookings = bookingRepositoryPort.findBookingsByDepartmentId(
          departmentOptional.get().id());
      if (!bookings.isEmpty()) {
        return bookings.stream().map(Booking::id).toList();
      }
    }
    log.debug(String.format("Could not find the department with the name : %s", departmentName));
    return Collections.emptyList();
  }

  @Override
  public List<Department> findAllDepartments() {
    return departmentUseCase.findAll();
  }

  @Override
  public List<Currency> findAllUsedCurrencies() {
    return bookingRepositoryPort.findAllUsedCurrencies();
  }

  @Override
  public BigDecimal getSumBookingPrice(Currency currency) {
    List<Booking> bookings = bookingRepositoryPort.findAllBookingByCurrency(currency);
    if (!bookings.isEmpty()) {
      return bookings.stream().map(Booking::price).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    return BigDecimal.ZERO;
  }

  @Override
  public Optional<String> doBusinessOfTheDepartment(UUID bookingId) {
    Optional<Booking> booking = bookingRepositoryPort.findById(bookingId);
    if (booking.isPresent()) {
      Department department = booking.get().department();
      return Optional.of(department.departmentStrategy().doBusiness(department));
    }
    log.debug("Could not find the Booking with id : %s", bookingId);
    return Optional.empty();
  }
}
