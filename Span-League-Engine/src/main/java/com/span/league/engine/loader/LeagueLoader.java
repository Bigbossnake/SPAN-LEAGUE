package com.span.league.engine.loader;

import com.span.league.engine.league.LeagueMatch;
import com.span.league.engine.league.LeagueRanking;
import com.span.league.engine.league.LeagueRules;
import com.span.league.engine.team.TeamInfo;
import com.span.league.engine.team.TeamType;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

/**
 * Class Responsible to load and parse a file containing the league information. After the load process
 * finishes the final structure is a list of team Matches that shares the same object Ranking and Rules
 * engine.
 *
 * @see com.span.league.engine.league.LeagueRules
 * @see com.span.league.engine.league.LeagueRanking
 * @see com.span.league.engine.league.LeagueMatch
 */
@Log4j2
@Getter
public class LeagueLoader {

  public static final String COMMA_SEPARATOR = ",";
  public static final String SPACE_SEPARATOR = "\\s+";
  public static final String NO_PATH_PROVIDED = "";

  public final LeagueRules leagueRules;
  public final LeagueRanking leagueRanking;
  public final List<LeagueMatch> leagueMatches;

  /**
   * Class Constructor that Initializes the LeagueRules, LeagueRanking and prepare the league matches
   * list to be filled with the Teams Information loaded from league file.
   */
  public LeagueLoader() {
    leagueRules   = new LeagueRules();
    leagueRanking = new LeagueRanking();
    leagueMatches = new ArrayList<>();
  }

  /**
   * Utility method responsible to create the TeamInfo Object with the information loaded from the
   * league file. The TeamInfo Object is a representation of the Team Information that contains the
   * points and the team type. Note that the TeamType can only take one of the following supported
   * values: [LIONS, SNAKES, TARANTULAS, FC, GROUCHES], if the TeamType contains an Invalid Value an
   * IllegalArgumentException is thrown.
   *
   * @param teamInfo information parsed from the league file that contains the points and the
   *        team type
   *
   * @see com.span.league.engine.team.TeamType
   * @see com.span.league.engine.team.TeamInfo
   *
   * @return TeamInfo Object which is a representation of the Team Information that contains the
   *         points and the team type.
   */
  public TeamInfo createTeam(String[] teamInfo) {

    String teamTypeString   = teamInfo.length > 2 ? teamInfo[0] + " " + teamInfo[1] : teamInfo[0];
    String teamPointsString = teamInfo.length > 2 ? teamInfo[2] : teamInfo[1];

    TeamType teamType = TeamType.fromString(teamTypeString);
    int teamPoints    = Integer.parseInt(teamPointsString);

    return TeamInfo.builder()
        .teamType(teamType)
        .points(teamPoints)
        .build();
  }

  /**
   * Utility method that parse the Team Information from the league file to get the information of
   * the TeamInfo Object which is a representation of the Team Information that contains the points
   * and the team type. At the end of the parse this method generates a list of Matches that
   * are an Abstract representation of a Match between 2 teams Local vs Visitor.
   *
   * @param leagueFile Text file with the Team Information that needs to be parsed
   *
   * @see com.span.league.engine.league.LeagueMatch
   *
   * @return List<LeagueMatch> Abstract representation of a Match between 2 teams Local vs Visitor.
   *
   * @throws IOException if the file doesn't exist or contains an error or is corrupted.
   */
  public List<LeagueMatch> loadLeagueFromFile(String leagueFile) throws IOException {

    log.info("League File Received: {}", leagueFile);
    String filePath = Objects.requireNonNullElse(leagueFile, NO_PATH_PROVIDED);

    if (!filePath.isBlank()) {
      log.info("Loading Simulation From File: {}", filePath);

      Files.readAllLines(Paths.get(filePath)).forEach(rawTeamInfo -> {
        String[] teamInfoTokens = rawTeamInfo.split(COMMA_SEPARATOR);

        TeamInfo localTeam = createTeam(teamInfoTokens[0].trim().split(SPACE_SEPARATOR));
        TeamInfo visitor   = createTeam(teamInfoTokens[1].trim().split(SPACE_SEPARATOR));

        LeagueMatch leagueMatch = new LeagueMatch(localTeam, visitor, leagueRanking, leagueRules);
        leagueMatches.add(leagueMatch);
      });
    }

    return leagueMatches;
  }

}
