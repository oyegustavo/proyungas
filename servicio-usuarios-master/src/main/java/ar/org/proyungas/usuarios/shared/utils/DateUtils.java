package ar.org.proyungas.usuarios.shared.utils;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ar.org.proyungas.usuarios.shared.infrastructure.output.restclient.ErrorCode;
import ar.org.proyungas.usuarios.shared.infrastructure.output.restclient.InvalidDateRangeException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateUtils {
  private static final DateTimeFormatter STRING_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  public static void validateDateRange(LocalDateTime start, LocalDateTime finish) {
    if (start == null || finish == null) {
      log.error("start and finish can´t be null");
      throw new InvalidDateRangeException(ErrorCode.INVALID_DATE_RANGE);
    }

    if (start.isAfter(finish)) {
      log.error("Invalid period start: {} finish: {}", start, finish);
      throw new InvalidDateRangeException(ErrorCode.INVALID_DATE_RANGE);
    }
  }

  public static LocalDateTime fromString(String dateTime) {
    if (dateTime == null) return null;

    return LocalDateTime.parse(dateTime, STRING_TIME_FORMATTER);
  }
}
