package com.span.league;

import com.span.league.engine.LeagueEngine;
import lombok.extern.log4j.Log4j2;

/**
 * Application Main Class, that is the entry point for the console Application. This class is
 * responsible to call the Span-League-Engine module which is included as a dependency in this
 * project. The Console - Application receives the location of the league file through an argument
 * of the command line to start the file processing of the league.
 */
@Log4j2
public class Application {

  public static final int EXIT_CODE = 0;

  /**
   * Main Entry Point of the application once that the user runs console application. Receives as
   * an argument the location of the league file. If the location file path is not provided the
   * program will exit without any errors.
   *
   * @param args array of arguments that inlcudes the location of the league file in the File System
   *        at the position zero.
   */
  public static void main(String args[]) {

    String leagueFile;

    if (args.length == 0) {
      log.warn("WARNING NO SIMULATION FILE WAS PROVIDED AS AN ARGUMENT TERMINATING LEAGUE...");
      System.exit(EXIT_CODE);
    }

    leagueFile = args[0];
    LeagueEngine leagueEngine = new LeagueEngine();
    leagueEngine.runLeague(leagueFile);
  }

}
