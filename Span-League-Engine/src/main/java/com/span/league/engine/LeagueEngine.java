package com.span.league.engine;

import com.span.league.engine.league.LeagueMatch;
import com.span.league.engine.loader.LeagueLoader;
import com.span.league.engine.processing.OperationResult;
import com.span.league.engine.processing.OperationResultCode;
import com.span.league.engine.team.TeamInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

/**
 * Class responsible to start the league processing. The first step of the league processing is calling
 * the LeagueLoader to load and parse a file containing the league information, after the file is
 * successfully processed and loaded into a list, the engine is responsible to trigger the matchWinner
 * method to decide which team is the winner of each match contained in LeagueMatch list. After the
 * matchWinner method is called for every member of the LeagueMatch list we can print the results
 * of the league sorted by points and by name in a Desc. order.
 *
 * @see com.span.league.engine.league.LeagueRanking
 * @see com.span.league.engine.loader.LeagueLoader
 * @see com.span.league.engine.league.LeagueMatch
 *
 */
@Log4j2
@Getter
public class LeagueEngine {

  private static final String OPERATION_SUCCESSFULLY_MESSAGE = "SUCCESS! Operation Finished Successfully";
  private static final String IO_EXCEPTION_MESSAGE = "Something Went Wrong Running The League Simulation Caused By: %s Reason: %s";
  private static final String ARRAY_INDEX_EXCEPTION_MESSAGE = "An Error Happened Processing The League Simulation Caused BY: %s Reason: %s";

  private static final String POINT_SEPARATOR   = ". ";
  private static final String SPACE_SEPARATOR   = " ";
  private static final String POINT_DESCRIPTION = " pts";
  private static final String BREAK_LINE        = "\n";

  public final LeagueLoader leagueLoader;
  public List<LeagueMatch> leagueMatches;

  public LeagueEngine() {
    leagueLoader = new LeagueLoader();
    leagueMatches = new ArrayList<>();
  }

  /**
   * Class responsible to create the OperationResult Object that is going to store the detailed
   * information about how the engine operations ended.
   *
   * @param message Contains a detailed message of the reason of the Exception that was catch
   *        by the Engine.
   * @param operationType Contains the String Representation of the Class Name of the Exception
   *        that was catch by the Engine.
   * @param operationResultCode num that is used to store an information CODE about how the League
   *        Engine operations end.
   *
   * @see com.span.league.engine.processing.OperationResult
   * @see com.span.league.engine.processing.OperationResultCode
   *
   * @return OperationResult that stores the information about how the League Engine operations end.
   */
  private OperationResult createOperationResult(
      String message, String operationType, OperationResultCode operationResultCode) {
    return OperationResult.builder()
        .message(message)
        .operationType(operationType)
        .operationResultCode(operationResultCode)
        .build();
  }

  /**
   * Utility Method to Print the results of the league, after the league simulation ends.
   *
   * @param rankingList TreeSet of the league sorted by points and by name in a Desc. order.
   */
  public String printLeagueResults(SortedSet<TeamInfo> rankingList) {

    log.info("SUCCESS! League Simulation Finished With: ");

    AtomicInteger place = new AtomicInteger(0);
    StringBuilder rankingResult = new StringBuilder();
    AtomicInteger previousPoints = new AtomicInteger(-1);

    rankingList.forEach(ranking->{
      StringBuilder currentResult = new StringBuilder();

      if (previousPoints.intValue() != ranking.getPoints()) {
          currentResult.append(place.incrementAndGet());
      } else {
        currentResult.append(place);
      }

      currentResult.append(POINT_SEPARATOR);
      currentResult.append(ranking.getTeamType().getTeamTypeValue());
      currentResult.append(SPACE_SEPARATOR);
      currentResult.append(ranking.getPoints());
      currentResult.append(POINT_DESCRIPTION);
      currentResult.append(BREAK_LINE);

      System.out.print(currentResult);
      rankingResult.append(currentResult);
      previousPoints.set(ranking.getPoints());
    });

    return rankingResult.toString();
  }

  /**
   * Engine entry point to start the league file processing and then the match processing between
   * the Teams contained in the league file. The first step of the league processing is calling
   * the LeagueLoader to load and parse a file containing the league information, after the file is
   * successfully processed and loaded into a list, the engine is responsible to trigger the
   * matchWinner method to decide which team is the winner of each match contained in LeagueMatch
   * list. After the  matchWinner method is called for every member of the LeagueMatch list we can
   * print the results of the league, sorted by points and by name in a Desc. order.
   *
   * @param leagueFile File System Path where the league file is loaded.
   *
   * @return OperationResult that stores the information about how the League Engine operations end.
   */
  public OperationResult runLeague(String leagueFile) {

    try {
      System.out.print(
               "#########################################\n"
             + "#        WELCOME TO SPAN LEAGUE         #\n"
             + "#########################################\n");

      leagueMatches = leagueLoader.loadLeagueFromFile(leagueFile);

      if (leagueMatches.isEmpty()) {
        log.warn("WARNING! NO SIMULATION LEAGUE FILE WAS PROVIDED, NOTHING TO DO");

        return createOperationResult("WARNING! NO SIMULATION LEAGUE FILE WAS PROVIDED, NOTHING TO DO",
            OperationResult.class.getTypeName(),
            OperationResultCode.NOT_STARTED);
      }

      log.info("Running League Simulation...");
      leagueMatches.forEach(LeagueMatch::matchWinner);
      printLeagueResults(leagueLoader.getLeagueRanking().getRanking());

      return createOperationResult(OPERATION_SUCCESSFULLY_MESSAGE,
          OperationResult.class.getTypeName(),
          OperationResultCode.SUCCESS);

    } catch (IOException ioe) {
      String errorMessage = String.format(IO_EXCEPTION_MESSAGE, IOException.class.getTypeName(), ioe.getMessage());
      log.error(errorMessage);

      return createOperationResult(errorMessage,
          IOException.class.getTypeName(),
          OperationResultCode.ERROR);

    } catch (ArrayIndexOutOfBoundsException e) {
      String errorMessage = String.format(ARRAY_INDEX_EXCEPTION_MESSAGE, ArrayIndexOutOfBoundsException.class.getTypeName(),e.getMessage());
      log.error(errorMessage);

      return createOperationResult(errorMessage,
          ArrayIndexOutOfBoundsException.class.getTypeName(),
          OperationResultCode.ERROR);
    } catch (IllegalArgumentException e) {
      log.error(e.getMessage());

      return createOperationResult(e.getMessage(),
          IllegalArgumentException.class.getTypeName(),
          OperationResultCode.ERROR);
    }

  }

}
