package com.statista.code.challenge.infrastructure.output.data;

import com.statista.code.challenge.domain.model.Booking;
import com.statista.code.challenge.domain.model.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static com.statista.code.challenge.TestDataProvider.getATestBooking;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingMemoryRepositoryAdaptorTest {

  @InjectMocks
  private BookingMemoryRepositoryAdaptor repository;
  @Mock
  private Map<UUID, Booking> bookingMap;

  @BeforeEach
  void innitEachTest() {
    ReflectionTestUtils.setField(repository, "bookingMap", bookingMap);
  }

  @Test
  void givenBooking_insertUpdate_insertedBookingReturned() {
    Booking booking = getATestBooking();

    when(bookingMap.put(any(), eq(booking))).thenReturn(booking);

    Booking result = repository.insertUpdate(booking);

    assertEquals(booking, result);
  }

  @Test
  void givenBookingId_findById_returnedBooking() {
    UUID bookingId = UUID.randomUUID();
    Booking booking = getATestBooking();

    when(bookingMap.get(bookingId)).thenReturn(booking);

    Optional<Booking> result = repository.findById(bookingId);

    assertEquals(Optional.of(booking), result);
  }

  @Test
  void givenListOfBookings_findAllUsedCurrencies_returnedListOfCurrencies() {
    Booking booking1 = getATestBooking(Currency.EUR);
    Booking booking2 = getATestBooking(Currency.USD);
    Booking booking3 = getATestBooking(Currency.EUR);

    when(bookingMap.values()).thenReturn(Arrays.asList(booking1, booking2, booking3));

    List<Currency> currencies = repository.findAllUsedCurrencies();

    assertEquals(2, currencies.size());
    assertTrue(currencies.contains(Currency.EUR));
    assertTrue(currencies.contains(Currency.USD));
    verify(bookingMap, times(1)).values();
  }

  @Test
  void givenListOfBookings_findBookingsByDepartmentId_theBookingsOfTheDepartmentFound() {
    Booking booking = getATestBooking();
    UUID departmentId = booking.department().id();

    when(bookingMap.values()).thenReturn(List.of(booking));

    List<Booking> result = repository.findBookingsByDepartmentId(departmentId);

    assertEquals(1, result.size());
    assertEquals(departmentId, result.get(0).department().id());
    verify(bookingMap, times(1)).values();
  }

  @Test
  void findAllBookingByCurrency() {
    Booking booking1 = getATestBooking(Currency.EUR);
    Booking booking2 = getATestBooking(Currency.USD);
    Booking booking3 = getATestBooking(Currency.EUR);

    when(bookingMap.values()).thenReturn(Arrays.asList(booking1, booking2, booking3));

    List<Booking> bookings = repository.findAllBookingByCurrency(Currency.EUR);

    assertEquals(2, bookings.size());
    verify(bookingMap, times(1)).values();
  }
}