package com.span.league.engine.league;

import com.span.league.engine.team.TeamInfo;
import com.span.league.engine.team.TeamType;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;
import lombok.Getter;

/**
 * Class that represents the Team's Classifications and stores the points of each team. The teams are
 * sorted by points and by name in a Desc. order. Note that the teams of the league belongs to one
 * of the following [LIONS, SNAKES, TARANTULAS, FC, GROUCHES].
 *
 * @see com.span.league.engine.team.TeamInfo
 * @see com.span.league.engine.team.TeamType
 */
@Getter
public class LeagueRanking {

  public static final int INITIAL_POINTS = 0;

  public static final Comparator<TeamInfo> TEAM_COMPARATOR_BY_NAME =
      Comparator.comparing(TeamInfo::getTeamType);
  public static final Comparator<TeamInfo> TEAM_COMPARATOR_BY_POINTS =
      Comparator.comparing(TeamInfo::getPoints);
  public static final Comparator<TeamInfo> LEAGUE_RANKING_COMPARATOR =
      TEAM_COMPARATOR_BY_POINTS.thenComparing(TEAM_COMPARATOR_BY_NAME).reversed();

  public final TeamInfo lions;
  public final TeamInfo snakes;
  public final TeamInfo tarantulas;
  public final TeamInfo fcAwesome;
  public final TeamInfo grouches;
  public final SortedSet<TeamInfo> ranking;

  /**
   * LeagueRanking Constructor that Initializes the League with the Supported Teams. Each team is
   * initialized at the beginning with 0 Points. Note that the teams of the league belongs to one
   * of the following [LIONS, SNAKES, TARANTULAS, FC, GROUCHES].
   */
  public LeagueRanking() {
    lions = TeamInfo.builder()
        .teamType(TeamType.LIONS)
        .points(INITIAL_POINTS)
        .build();

    snakes = TeamInfo.builder()
        .teamType(TeamType.SNAKES)
        .points(INITIAL_POINTS)
        .build();

    tarantulas = TeamInfo.builder()
        .teamType(TeamType.TARANTULAS)
        .points(INITIAL_POINTS)
        .build();

    fcAwesome = TeamInfo.builder()
        .teamType(TeamType.FC)
        .points(INITIAL_POINTS)
        .build();

    grouches = TeamInfo.builder()
        .teamType(TeamType.GROUCHES)
        .points(INITIAL_POINTS)
        .build();

    ranking = new TreeSet<>(LEAGUE_RANKING_COMPARATOR);
    ranking.add(lions);
    ranking.add(snakes);
    ranking.add(tarantulas);
    ranking.add(fcAwesome);
    ranking.add(grouches);
  }

  /**
   * Method that updates the punctuation in the ranking league receiving the Team League Type. Note
   * that the teams of the league, belongs to one of the following [LIONS, SNAKES, TARANTULAS, FC,
   * GROUCHES].
   *
   * @param teamType Opponent Team League Type.
   * @param points Points that are going to be assigned to the Team.
   *
   * @see com.span.league.engine.team.TeamType
   */
  public void updateRanking(TeamType teamType, int points) {

    TeamInfo winnerTeam = ranking.stream().filter(teamInfo ->
        teamInfo.getTeamType().getTeamTypeValue().equalsIgnoreCase(teamType.getTeamTypeValue()))
        .findFirst()
        .orElse(TeamInfo.builder().build());

    int currentPoints = winnerTeam.getPoints();
    ranking.remove(winnerTeam);
    winnerTeam.setPoints(currentPoints + points);
    ranking.add(winnerTeam);
  }
}
