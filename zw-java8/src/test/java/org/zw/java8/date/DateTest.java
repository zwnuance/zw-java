package org.zw.java8.date;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.function.Predicate;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zw on 4/26/2017.
 */
public class DateTest {

  @Test
  public void testDuration() {
    Instant start = Instant.now();
    double sum = 0;
    for (int i = 0; i < 1000000; i++) {
      sum += i;
    }
    Instant end = Instant.now();
    Duration duration = Duration.between(start, end);
    long millis = duration.toMillis();
    assertTrue(millis > 0);
  }

  @Test
  public void programmerDay() {
    LocalDate programmerDay = LocalDate.now().withDayOfYear(1);
    programmerDay = programmerDay.plusYears(1).minusDays(Year.now().length() - 256);
    System.out.println(programmerDay);
  }

  @Test
  public void leapYearAdd() {
    LocalDate leapYear = LocalDate.of(2000, Month.FEBRUARY, 29);
    LocalDate leapNextYear = leapYear.plusYears(1);
    LocalDate leapNext4Year = leapYear.plusYears(4);
    LocalDate leapYear4Times = leapYear.plusYears(1).plusYears(1).plusYears(1).plusYears(1);
    System.out.println(leapNextYear);
    System.out.println(leapNext4Year);
    System.out.println(leapYear4Times);
  }

  @Test
  public void nextWorkDay() {
    LocalDate today = LocalDate.now();
    LocalDate nextWorkDay = today.with(next(w -> w.getDayOfWeek().getValue() < 6));
    System.out.println(nextWorkDay);
    LocalDate nextWeekend = today.with(next(w -> w.getDayOfWeek().getValue() >= 6));
    System.out.println(nextWeekend);
  }

  private TemporalAdjuster next(Predicate<LocalDate> predicate) {
    TemporalAdjuster temporalAdjuster = d -> {
      LocalDate result = (LocalDate)d;
      do{
        result = result.plusDays(1);
      }while (predicate.test(result));
      return result;
    };
    return temporalAdjuster;
  }

  @Test
  public void unixCal() {
    String dayStr = "9 2019";
    String[] split = dayStr.split(" ");
    LocalDate day = LocalDate.of(Integer.parseInt(split[1]), Integer.parseInt(split[0]), 1);
    if(day.getDayOfWeek().getValue() != 7) {
      for (int i = 0; i < day.getDayOfWeek().getValue(); i++) {
        System.out.print("   ");
      }
      System.out.print(" 1");
      if(day.getDayOfWeek().getValue() == 6) {
        System.out.println();
      }
    }else {
      System.out.print(" 1 ");
    }

    for (int i = 2; i <= day.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth(); i++) {
      day = day.plusDays(1);
      System.out.print((i > 9 ? i : " " + i) + " ");
      if(day.getDayOfWeek().getValue() == 6) {
        System.out.println();
      }
    }
  }

  @Test
  public void birth2Days() {
    LocalDate appleBirth = LocalDate.of(1976, Month.APRIL, 1);
    long days = ChronoUnit.DAYS.between(appleBirth, LocalDate.now());
    System.out.println(days);
  }

  @Test
  public void firdaysIn20Centry() {
    LocalDate begin = LocalDate.of(2000, Month.JANUARY, 1);
    LocalDate end = LocalDate.of(2100, Month.DECEMBER, 31);
    LocalDate date = begin;
    long between = ChronoUnit.DAYS.between(begin, end);
    for (int i = 1; i <= between; i++) {
      if(date.getDayOfWeek().getValue() == 5) {
        System.out.println(date);
      }
      date = date.plusDays(1);
    }
  }

  @Test
  public void timeInterval() {
    LocalDateTime time1 = LocalDateTime.now();
    LocalDateTime time2 = time1.plusHours(1);
    LocalDateTime time3 = time1.plusMinutes(120);
    LocalDateTime time4 = time3.plusMinutes(30);
    TimeInterval ti1 = TimeInterval.of(time1, time2);
    TimeInterval ti2 = TimeInterval.of(time3, time4);
    boolean conflict = TimeInterval.isConflict(ti1, ti2);
    assertFalse(conflict);
  }
}

class TimeInterval{
  private LocalDateTime begin;
  private LocalDateTime end;

  public TimeInterval(LocalDateTime begin, LocalDateTime end) {
    this.begin = begin;
    this.end = end;
  }

  public static TimeInterval of(LocalDateTime t1, LocalDateTime t2) {
    return new TimeInterval(t1, t2);
  }

  public static boolean isConflict(TimeInterval interval1, TimeInterval interval2) {
    return interval1.getBegin().isBefore(interval2.getBegin()) && interval1.getEnd().isAfter(interval2.getBegin());
  }

  public LocalDateTime getBegin() {
    return begin;
  }

  public void setBegin(LocalDateTime begin) {
    this.begin = begin;
  }

  public LocalDateTime getEnd() {
    return end;
  }

  public void setEnd(LocalDateTime end) {
    this.end = end;
  }
}
