package com.statista.code.challenge.infrastructure.in.web.mapper;

import com.statista.code.challenge.application.usecase.DepartmentUseCase;
import com.statista.code.challenge.domain.model.Booking;
import com.statista.code.challenge.domain.model.department.Department;
import com.statista.code.challenge.infrastructure.in.web.dto.BookingDtoIn;
import com.statista.code.challenge.infrastructure.in.web.dto.BookingDtoOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.UUID;

@Component
@AllArgsConstructor
public class BookingMapper {

  private DepartmentUseCase departmentUseCase;

  public BookingDtoOut toBookingDtoOut(Booking booking) {
    return new BookingDtoOut(booking.id(), booking.description(), booking.price(),
        booking.currency(), booking.subscriptionStartDate(), booking.email(),
        booking.department().name()
    );
  }

  public Booking toBooking(BookingDtoIn bookingDtoIn) {
    return new Booking(bookingDtoIn.description(), bookingDtoIn.price(), bookingDtoIn.currency(),
        bookingDtoIn.subscriptionStartDate(), bookingDtoIn.email(),
        findDepartmentByName(bookingDtoIn.departmentName())
    );
  }

  public Booking toBooking(BookingDtoIn bookingDtoIn, UUID id) {
    return new Booking(id, bookingDtoIn.description(), bookingDtoIn.price(),
        bookingDtoIn.currency(), bookingDtoIn.subscriptionStartDate(), bookingDtoIn.email(),
        findDepartmentByName(bookingDtoIn.departmentName())
    );
  }

  private Department findDepartmentByName(String departmentName) {
    return departmentUseCase.findByDepartment(departmentName)
        .orElseThrow(() -> new NoSuchElementException("Department not found: " + departmentName));
  }

}