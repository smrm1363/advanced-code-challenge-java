package com.statista.code.challenge.application.service;

import com.statista.code.challenge.application.port.BookingRepositoryPort;
import com.statista.code.challenge.application.port.NotificationPort;
import com.statista.code.challenge.application.usecase.DepartmentUseCase;
import com.statista.code.challenge.domain.model.Booking;
import com.statista.code.challenge.domain.model.department.Department;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.statista.code.challenge.TestDataProvider.getATestBooking;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

  @InjectMocks
  private BookingService bookingService;
  @Mock
  private BookingRepositoryPort bookingRepositoryPort;
  @Mock
  private NotificationPort notificationPort;
  @Mock
  private DepartmentUseCase departmentUseCase;
  @Captor
  private ArgumentCaptor<String> emailArgumentCaptor;

  @Test
  void givenBooking_replaceOrAddBooking_BookingPersisted() {
    Booking booking = getATestBooking();

    when(bookingRepositoryPort.insertUpdate(booking)).thenReturn(booking);

    Booking result = bookingService.replaceOrAddBooking(booking);

    assertEquals(booking, result);
  }

  @Test
  void givenBooking_createNewBooking_BookingPersistedAndEmailSent() {
    Booking booking = getATestBooking();

    when(bookingRepositoryPort.insertUpdate(booking)).thenReturn(booking);

    Booking result = bookingService.createNewBooking(booking);

    verify(notificationPort).send(anyString(), emailArgumentCaptor.capture());
    String emailAddress = emailArgumentCaptor.getValue();
    assertEquals(booking.email(), emailAddress);
    assertEquals(booking, result);
  }

  @Test
  void givenABooking_findBooking_theBookingFound() {
    Booking booking = getATestBooking();

    when(bookingRepositoryPort.findById(booking.id())).thenReturn(Optional.of(booking));

    Optional<Booking> result = bookingService.findBooking(booking.id());

    assertEquals(booking, result.get());
  }

  @Test
  void givenAbookingList_findBookingIds_theBookingIdsByDepartmentNameFound() {
    Booking booking = getATestBooking();
    Department department = booking.department();

    when(departmentUseCase.findByDepartment(department.name())).thenReturn(Optional.of(department));
    when(bookingRepositoryPort.findBookingsByDepartmentId(department.id())).thenReturn(
        List.of(booking));

    List<UUID> result = bookingService.findBookingIds(department.name());

    assertEquals(1, result.size());
    assertEquals(booking.id(), result.get(0));
  }

  @Test
  void getSumBookingPrice() {
    Booking booking1 = getATestBooking();
    Booking booking2 = getATestBooking();
    BigDecimal sum = booking1.price().add(booking2.price());
    List<Booking> bookings = Arrays.asList(booking1, booking2);

    when(bookingRepositoryPort.findAllBookingByCurrency(booking1.currency())).thenReturn(bookings);

    BigDecimal result = bookingService.getSumBookingPrice(booking1.currency());

    assertEquals(sum, result);
  }
}