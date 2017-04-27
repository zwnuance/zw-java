package org.zw.java8.date;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zw on 4/26/2017.
 */
public class LocalDateTest {

  @Test
  public void dateCreate() {
    LocalDate today = LocalDate.now();

    LocalDate appleBirth = LocalDate.of(1976, 4, 1);
    LocalDate appleBirth2 = LocalDate.of(1976, Month.APRIL, 1);
    LocalDate appleBirth3 = appleBirth.withYear(today.getYear());
    Period applePeriod = appleBirth.until(appleBirth3);

    assertEquals(appleBirth, appleBirth2);
    LocalDate mgBirth = LocalDate.of(1912, Month.JANUARY, 1);
    Period period = mgBirth.until(today);
  }

  @Test
  public void dateOps() {
    LocalDate today = LocalDate.now();
    LocalDate oneYearLater = today.plusDays(365);
    LocalDate normalYearDate = LocalDate.of(2016, Month.FEBRUARY, 29);
    LocalDate leapYearDate = normalYearDate.withYear(2017);
    assertTrue(normalYearDate.isLeapYear());
    assertFalse(leapYearDate.isLeapYear());
  }

  @Test
  public void dateAdjust() {
    LocalDate nextWendsday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
    LocalDate monthFirstDay = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
  }

  @Test
  public void dateCustAdjust() {
    TemporalAdjuster weekendAdjuster = d -> {
      LocalDate result = (LocalDate)d;
      do {
        result = result.plusDays(1);
      } while (result.getDayOfWeek().getValue() < 6);
        return result;
    };

    LocalDate freeTime = LocalDate.now().with(weekendAdjuster);
  }
}
