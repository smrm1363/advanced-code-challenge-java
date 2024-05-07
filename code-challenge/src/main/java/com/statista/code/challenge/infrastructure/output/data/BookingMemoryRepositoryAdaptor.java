package com.statista.code.challenge.infrastructure.output.data;

import com.statista.code.challenge.application.port.BookingRepositoryPort;
import com.statista.code.challenge.domain.model.Booking;
import com.statista.code.challenge.domain.model.Currency;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Slf4j
public class BookingMemoryRepositoryAdaptor implements BookingRepositoryPort {

  private final Map<UUID, Booking> bookingMap = new ConcurrentHashMap<>();

  @Override
  public Booking insertUpdate(Booking booking) {
    bookingMap.put(booking.id(), booking);
    log.debug(String.format("Booking with the id %s added successfully", booking.id()));
    return booking;
  }

  @Override
  public Optional<Booking> findById(UUID id) {
    return Optional.ofNullable(bookingMap.get(id));
  }

  @Override
  public List<Currency> findAllUsedCurrencies() {
    return bookingMap.values().stream().map(Booking::currency).distinct().toList();
  }

  @Override
  public List<Booking> findBookingsByDepartmentId(UUID id) {
    return bookingMap.values().stream().filter(booking -> booking.department().id().equals(id))
        .toList();
  }

  @Override
  public List<Booking> findAllBookingByCurrency(Currency currency) {
    return bookingMap.values().stream().filter(booking -> booking.currency().equals(currency))
        .toList();
  }
}
