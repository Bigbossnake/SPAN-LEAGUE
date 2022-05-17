package com.span.league.engine.league;

import com.span.league.engine.team.TeamInfo;
import lombok.AllArgsConstructor;

/**
 * Abstract representation of a Match between 2 teams Local vs Visitor, this class is responsible to
 * call the LeagueRules class to decide which team is the winner of the Match. Note that the class
 * responsible to update the points of each team is the LeagueRanking class.
 *
 * @see com.span.league.engine.team.TeamInfo
 * @see com.span.league.engine.league.LeagueRanking
 * @see com.span.league.engine.league.LeagueRules
 */
@AllArgsConstructor
public class LeagueMatch {

  public final TeamInfo localTeam;
  public final TeamInfo visitorTeam;
  public final LeagueRanking leagueRanking;

  public final LeagueRules leagueRules;

  /**
   * Utility method responsible to call the Rules Engine class which is LeagueRules that is
   * responsible to decide which team is the winner of the Match. Note that the class responsible to
   * update the points of each team is the LeagueRanking class.
   *
   * @see com.span.league.engine.team.TeamInfo
   * @see com.span.league.engine.league.LeagueRules
   * @see com.span.league.engine.league.LeagueRanking
   */
  public void matchWinner() {
    leagueRules.decideMatch(localTeam, visitorTeam, leagueRanking);
  }

}
