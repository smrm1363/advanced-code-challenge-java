package com.statista.code.challenge.infrastructure.in.web;

import com.statista.code.challenge.domain.model.Currency;
import com.statista.code.challenge.infrastructure.in.web.dto.BookingDtoIn;
import com.statista.code.challenge.infrastructure.in.web.dto.BookingDtoOut;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookingControllerIntegrationTest {

  @Autowired
  BookingController bookingController;

  @Test
  void allTogetherTest() {
    BookingDtoIn bookingDtoIn = new BookingDtoIn("", BigDecimal.ONE, Currency.EUR, LocalDate.now(),
        "s@g.com", "Entertainment department"
    );

    ResponseEntity<BookingDtoOut> createdResult = bookingController.createBooking(bookingDtoIn);
    assertNotNull(createdResult.getBody().id());

    ResponseEntity<BookingDtoOut> findingResult = bookingController.getBookingById(
        createdResult.getBody().id());

    ResponseEntity<String> doBusinessResult = bookingController.getDoBusiness(
        createdResult.getBody().id());
    String doBusinessResultString = doBusinessResult.getBody();
    assertTrue(doBusinessResultString.contains("Entertainment department"));
  }
}