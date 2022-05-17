package com.span.league.engine.exception;

import com.span.league.engine.team.TeamType;
import java.util.Arrays;

/**
 * Enumeration Used By a custom Exception Called: OperationException to represent the Exception Type
 * that was thrown by the LeagueEngine during the League Processing File. The only values allowed for
 * the Exception Type are [IO_EXCEPTION, ARRAY_INDEX_OUT_BOUNDS_EXCEPTION, ILLEGAL_ARGUMENT_EXCEPTION].
 *
 * @see com.span.league.engine.LeagueEngine
 * @see com.span.league.engine.exception.OperationException
 */
public enum ExceptionType {

  IO_EXCEPTION("java.io.IOException"),
  ARRAY_INDEX_OUT_BOUNDS_EXCEPTION("java.lang.ArrayIndexOutOfBoundsException"),
  ILLEGAL_ARGUMENT_EXCEPTION("java.lang.IllegalArgumentException");

  private String exceptionValue;

  /**
   * EnumerationType Constructor required to get the String value representation of the ExceptionType
   * members. The only values allowed for the exceptionValue are the ones defined in the [IO_EXCEPTION,
   * ARRAY_INDEX_OUT_BOUNDS_EXCEPTION, ILLEGAL_ARGUMENT_EXCEPTION] Enumeration members.
   *
   * @param exceptionValue String Value description for the Enumeration members.
   */
  ExceptionType(String exceptionValue) {
    this.exceptionValue = exceptionValue;
  }

  /**
   * Getter Method to return the exceptionValue description for a given Enumeration member.
   *
   * @return The String Value description for the Enumeration members.
   */
  public String getExceptionValue() {
    return this.exceptionValue;
  }

  /**
   * Creates an Enumeration Member from a String Value. If the value is not defined in any member
   * of the enumeration [IO_EXCEPTION, ARRAY_INDEX_OUT_BOUNDS_EXCEPTION, ILLEGAL_ARGUMENT_EXCEPTION],
   * an IllegalArgumentException is thrown.
   *
   * @param exceptionTypeString String value of the Enumeration members, that can take one of the
   *        supported values [IO_EXCEPTION, ARRAY_INDEX_OUT_BOUNDS_EXCEPTION, ILLEGAL_ARGUMENT_EXCEPTION]
   *
   * @return ExceptionType if a valid string value was received otherwise an IllegalArgumentException is thrown.
   *
   * @throws IllegalArgumentException if the string value received is not on the supported values of the Enum
   */
  public static ExceptionType fromString(String exceptionTypeString) {

    for (ExceptionType exceptionType : ExceptionType.values()) {
      if (exceptionType.exceptionValue.equalsIgnoreCase(exceptionTypeString)) {
        return exceptionType;
      }
    }

    final String ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE = "Invalid Exception Value! For: %s Only: %s Are Allowed";
    String allowedValues = Arrays.toString(TeamType.values());
    String errorMessage = String.format(ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE, exceptionTypeString, allowedValues);

    throw new IllegalArgumentException(errorMessage);
  }

}
