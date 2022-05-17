package com.span.league.engine.processing;

import com.span.league.engine.exception.ExceptionType;
import com.span.league.engine.exception.OperationException;
import lombok.Builder;
import lombok.Data;

/**
 * Class that is used to store the information about how the League Engine operations end. The
 * information stored contains a detailed message if an error happened as well as the Exception type
 * that was thrown during the file processing or the league processing side.
 *
 * @see com.span.league.engine.processing.OperationResultCode
 */
@Data
@Builder
public class OperationResult {
  private String message;
  private String operationType;
  private OperationResultCode operationResultCode;

  /**
   * Utility method used to propagate the Exceptions OutSide the LeagueEngine Module, to get some
   * debug data of what went wrong during the file processing of a given league as well as a
   * possible root cause of the error.
   *
   * @see com.span.league.engine.exception.OperationException
   *
   * @throws OperationException to propagate the Exceptions OutSide the LeagueEngine Module.
   */
  public void throwError() throws OperationException {
    throw OperationException.builder()
        .message(message)
        .exceptionType(ExceptionType.fromString(operationType))
        .build();
  }
}
