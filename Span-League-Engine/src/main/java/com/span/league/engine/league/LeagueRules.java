package com.span.league.engine.league;

import com.span.league.engine.team.TeamInfo;

/**
 * Class that it used as a rules engine to determine which team wins the match, or if the match is
 * a Tie, and then update the points for the winner through LeagueRanking.
 *
 * @see com.span.league.engine.LeagueEngine
 * @see com.span.league.engine.team.TeamInfo
 * @see com.span.league.engine.team.TeamType
 * @see com.span.league.engine.league.LeagueRanking
 */
public class LeagueRules {

  public static final int WINNER_POINTS = 3;
  public static final int TIE_POINTS = 1;
  public static final boolean WAS_TIE = true;

  /**
   * Method that decides which team Wins the Match. The Team is represented by an Object Called
   * TeamInfo. The winner team receives 3 Points and then Team Score is updated in the LeagueRanking.
   *
   * @param localTeam Opponent Representation in the TeamInfo Class.
   * @param visitorTeam Opponent Representation in the TeamInfo Class.
   * @param leagueRanking Team Ranking with the Points of the Team Information.
   *
   * @see com.span.league.engine.team.TeamInfo
   * @see com.span.league.engine.league.LeagueRanking
   */
  public void decideWinner(TeamInfo localTeam, TeamInfo visitorTeam, LeagueRanking leagueRanking) {
    if (localTeam.getPoints() > visitorTeam.getPoints()) {
      leagueRanking.updateRanking(localTeam.getTeamType(), WINNER_POINTS);
      return;
    }

    leagueRanking.updateRanking(visitorTeam.getTeamType(), WINNER_POINTS);
  }

  /**
   * Method that evaluates if a team Match ends in a Tie. The Team is represented by an Object Called
   * TeamInfo. If the match ends in a Tie, both teams received 1 Point and then Team Score is updated
   * in the LeagueRanking.
   *
   * @param localTeam Opponent Representation in the TeamInfo Class.
   * @param visitorTeam Opponent Representation in the TeamInfo Class.
   * @param leagueRanking Team Ranking with the Points of the Team Information.
   *
   * @see com.span.league.engine.team.TeamInfo
   * @see com.span.league.engine.league.LeagueRanking
   *
   * @return True if the match between a localTeam and visitorTeam ends in a Tie.
   */
  public boolean isTie(TeamInfo localTeam, TeamInfo visitorTeam, LeagueRanking leagueRanking) {
    if (localTeam.getPoints() == visitorTeam.getPoints()) {
      leagueRanking.updateRanking(localTeam.getTeamType(), TIE_POINTS);
      leagueRanking.updateRanking(visitorTeam.getTeamType(), TIE_POINTS);

      return WAS_TIE;
    }

    return !WAS_TIE;
  }

  /**
   * Method that evaluates if a match between 2 teams ends in a Tie or with a winner. If the match
   * ends in a Tie, both teams received 1 Point and then Team Score is updated in the LeagueRanking.
   * By the other had if the match ends with a Winner, The winner team receives 3 Points and then
   * Team Score is updated in the LeagueRanking.
   *
   * @param localTeam Opponent Representation in the TeamInfo Class.
   * @param visitorTeam Opponent Representation in the TeamInfo Class.
   * @param leagueRanking Team Ranking with the Points of the Team Information.
   *
   * @see com.span.league.engine.team.TeamInfo
   * @see com.span.league.engine.league.LeagueRanking
   */
  public void decideMatch(TeamInfo localTeam, TeamInfo visitorTeam, LeagueRanking leagueRanking) {
    if (!isTie(localTeam, visitorTeam, leagueRanking)) {
      decideWinner(localTeam, visitorTeam, leagueRanking);
    }
  }
}
