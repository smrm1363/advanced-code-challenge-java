package com.statista.code.challenge;

import com.statista.code.challenge.domain.model.Booking;
import com.statista.code.challenge.domain.model.Currency;
import com.statista.code.challenge.domain.model.department.Department;
import com.statista.code.challenge.domain.model.department.EducationDepartment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class TestDataProvider {

  public static Booking getATestBooking(Currency currency) {
    return new Booking("Some Description", BigDecimal.ONE, currency, LocalDate.now(),
        "test@testMail.com", getADepartment()
    );
  }

  public static Booking getATestBooking() {
    return getATestBooking(Currency.EUR);
  }

  public static Department getADepartment() {
    return new Department(UUID.randomUUID(), "Test department", new EducationDepartment());
  }

}
