package com.span.league.ui;

import com.span.league.ui.components.LeagueFrame;
import lombok.extern.log4j.Log4j2;

/**
 * Application Main Class, that is the entry point for the GUI Application. This class is
 * responsible to instance and create the GUI that is going to call Span-League-Engine module
 * which is included as a dependency in this project.
 */
@Log4j2
public class Application {

  /**
   * Main Entry Point of the application once that the user runs GUI application. This method
   * is responsible to instance and create the GUI that is going to be shown to the user.
   *
   * @param args array of arguments, for the GUI application no arguments are required.
   */
  public static void main(String args[]) {
    LeagueFrame leagueFrame = new LeagueFrame();
  }

}
