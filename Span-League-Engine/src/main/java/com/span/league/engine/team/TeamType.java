package com.span.league.engine.team;

import java.util.Arrays;

/**
 * Enumeration Used By TeamInfo Object to represent all the possible values supported for the TeamType
 * The only values allowed for team type are [LIONS, SNAKES, TARANTULAS, FC, GROUCHES].
 *
 * @see com.span.league.engine.team.TeamInfo
 */
public enum TeamType {
  LIONS("Lions"),
  SNAKES("Snakes"),
  TARANTULAS("Tarantulas"),
  FC("FC Awesome"),
  GROUCHES("Grouches");

  public String teamTypeValue;

  /**
   * EnumerationType Constructor required to get the String value representation of the TeamType
   * members. The only values allowed for the TeamType are the ones defined in the enumeration:
   * [LIONS, SNAKES, TARANTULAS, FC, GROUCHES] members.
   *
   * @param teamTypeValue String Value description for the Enumeration members.
   */
  TeamType(String teamTypeValue) {
    this.teamTypeValue = teamTypeValue;
  }

  /**
   * Getter Method to return the teamTypeValue description for a given Enumeration member.
   *
   * @return The String Value description for the Enumeration members.
   */
  public String getTeamTypeValue() {
    return this.teamTypeValue;
  }

  /**
   * Creates an Enumeration Member from a String Value. If the value is not defined in any member
   * of the enumeration [LIONS, SNAKES, TARANTULAS, FC, GROUCHES],an IllegalArgumentException is thrown.
   *
   * @param leagueTypeString String value of the Enumeration members, that can take one of the
   *        supported values [LIONS, SNAKES, TARANTULAS, FC, GROUCHES]
   *
   * @return ExceptionType if a valid string value was received otherwise an IllegalArgumentException is thrown.
   *
   * @throws IllegalArgumentException if the string value received is not on the supported values of the Enum
   */
  public static TeamType fromString(String leagueTypeString) {

    for (TeamType teamType : TeamType.values()) {
      if (teamType.teamTypeValue.equalsIgnoreCase(leagueTypeString)) {
        return teamType;
      }
    }

    final String ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE = "Invalid League Type! For: %s Only: %s Are Allowed";
    String allowedValues = Arrays.toString(TeamType.values());
    String errorMessage = String.format(ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE, leagueTypeString, allowedValues);

    throw new IllegalArgumentException(errorMessage);
  }

}
