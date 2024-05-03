package com.statista.code.challenge.infrastructure.in.web;

import com.statista.code.challenge.application.usecase.BookingUseCase;
import com.statista.code.challenge.domain.model.Booking;
import com.statista.code.challenge.domain.model.Currency;
import com.statista.code.challenge.domain.model.department.Department;
import com.statista.code.challenge.infrastructure.in.web.dto.BookingDtoIn;
import com.statista.code.challenge.infrastructure.in.web.dto.BookingDtoOut;
import com.statista.code.challenge.infrastructure.in.web.mapper.BookingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bookingservice")
@RequiredArgsConstructor
public class BookingController {

  private final BookingMapper bookingMapper;
  private final BookingUseCase bookingUseCase;

  @PostMapping("/booking")
  public ResponseEntity<BookingDtoOut> createBooking(@RequestBody BookingDtoIn bookingDtoIn) {
    Booking booking = bookingMapper.toBooking(bookingDtoIn);
    BookingDtoOut bookingDtoOut = bookingMapper.toBookingDtoOut(
        bookingUseCase.createNewBooking(booking));
    return ResponseEntity.status(HttpStatus.CREATED).body(bookingDtoOut);
  }

  @PutMapping("/booking/{bookingId}")
  public ResponseEntity<BookingDtoOut> replaceOrAddBooking(
      @PathVariable UUID bookingId, @RequestBody BookingDtoIn bookingDtoIn
  ) {
    Booking booking = bookingMapper.toBooking(bookingDtoIn, bookingId);
    BookingDtoOut bookingDtoOut = bookingMapper.toBookingDtoOut(
        bookingUseCase.replaceOrAddBooking(booking));
    return ResponseEntity.ok(bookingDtoOut);
  }

  @GetMapping("/booking/{bookingId}")
  public ResponseEntity<BookingDtoOut> getBookingById(@PathVariable UUID bookingId) {
    return ResponseEntity.ok(
        bookingMapper.toBookingDtoOut(bookingUseCase.findBooking(bookingId).orElseThrow()));
  }

  @GetMapping("/bookings/department/{departmentName}")
  public ResponseEntity<List<UUID>> getBookingsByDepartment(@PathVariable String departmentName) {
    return ResponseEntity.ok(bookingUseCase.findBookingIds(departmentName));
  }

  @GetMapping("/bookings/department")
  public ResponseEntity<List<String>> getAllDepartments() {
    return ResponseEntity.ok(
        bookingUseCase.findAllDepartments().stream().map(Department::name).toList());
  }

  @GetMapping("/bookings/currencies")
  public ResponseEntity<List<Currency>> getUsedCurrencies() {
    return ResponseEntity.ok(bookingUseCase.findAllUsedCurrencies());
  }

  @GetMapping("/sum/{currency}")
  public ResponseEntity<BigDecimal> getSumBookingsByCurrency(@PathVariable Currency currency) {
    return ResponseEntity.ok(bookingUseCase.getSumBookingPrice(currency));
  }

  @GetMapping("/bookings/dobusiness/{bookingId}")
  public ResponseEntity<String> getDoBusiness(@PathVariable UUID bookingId) {
    return ResponseEntity.ok(bookingUseCase.doBusinessOfTheDepartment(bookingId).orElseThrow());
  }

}
