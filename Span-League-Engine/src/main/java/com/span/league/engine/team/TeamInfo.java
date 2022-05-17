package com.span.league.engine.team;

import lombok.Builder;
import lombok.Data;

/**
 * Class Representation of the Team information that belongs to a League. The team information
 * that contains this class are the points and the TeamType. The only values allowed for team type
 * are [LIONS, SNAKES, TARANTULAS, FC, GROUCHES]. The team information is loaded from the league file
 * by the LeagueLoader through the LeagueEngine.
 *
 * @see com.span.league.engine.LeagueEngine
 * @see com.span.league.engine.league.LeagueRanking
 * @see com.span.league.engine.league.LeagueMatch
 * @see com.span.league.engine.loader.LeagueLoader
 * @see com.span.league.engine.team.TeamType
 */
@Data
@Builder
public class TeamInfo {
  private int points;
  private TeamType teamType;
}
