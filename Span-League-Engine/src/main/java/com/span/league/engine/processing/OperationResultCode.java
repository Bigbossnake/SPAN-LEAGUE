package com.span.league.engine.processing;

/**
 * Enum that is used to store an information CODE about how the League Engine operations end. The
 * only values allowed for the OperationResultCode are [SUCCESS, NOT_STARTED, ERROR]
 *
 * @see com.span.league.engine.processing.OperationResult
 */
public enum OperationResultCode {
  SUCCESS("Success"),
  NOT_STARTED("Not Started"),
  ERROR("Error");

  private String resultValue;

  /**
   * EnumerationType Constructor required to get the String value representation of the OperationResultCode
   * members. The only values allowed for the resultValue are the ones defined in the enumeration:
   * [SUCCESS, NOT_STARTED, ERROR] members.
   *
   * @param resultValue String Value description for the Enumeration members.
   */
  OperationResultCode(String resultValue) {
    this.resultValue = resultValue;
  }

  /**
   * Getter Method to return the resultValue description for a given Enumeration member.
   *
   * @return The String Value description for the Enumeration members.
   */
  public String getResultValue() {
    return this.resultValue;
  }

}
