package main.java.api.exception;

public class InvalidDateTimeException extends RuntimeException {
  public InvalidDateTimeException(String message) {
    super(message);
  }
}
