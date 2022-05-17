package com.span.league.engine.exception;

import lombok.Builder;
import lombok.Data;

/**
 * Custom Exception Definition, that is used to propagate the Exceptions OutSide the LeagueEngine
 * Module, to get some debug data of what went wrong during the file processing of a given league as
 * well as a possible root cause of the error.
 *
 * @see com.span.league.engine.LeagueEngine
 */
@Data
@Builder
public class OperationException extends Exception {
  private String message;
  private ExceptionType exceptionType;
}
