package com.span.league.ui.listeners;

import com.span.league.engine.exception.OperationException;
import com.span.league.engine.processing.OperationResult;
import com.span.league.engine.processing.OperationResultCode;
import com.span.league.ui.components.ColorProperties;
import com.span.league.ui.components.ComponentProperties;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import lombok.extern.log4j.Log4j2;

import com.span.league.engine.LeagueEngine;

/**
 * Action Listener that responds to the OnClick Event from the Start League Button to call the
 * Span-League-Engine module which is included as a dependency in this project. The Listener receives
 * the location of the league file through a text field to start the file processing of the league.
 */
@Log4j2
public class StartLeagueListener implements ActionListener {

  private static final String LEAGUE_ERROR_MESSAGE = "An Error Happened Processing The League File For More\nInformation Please See The League Results Area";
  private static final String EMPTY_FILE_PATH_WARNING_MESSAGE = "File Path Cannot Be Empty";

  private final JButton btnStart;
  private final JTextArea leagueResult;
  private final JTextField txtFilePath;

  /**
   * Listener constructor that receives the references of the Swing components that is going to be
   * able to interact with them.
   *
   * @param btnStart Swing Component Reference that is used as a parent of the listener
   * @param leagueResult text field that the listener is able to interact
   * @param txtFilePath text field that the listener is able to interact
   */
  public StartLeagueListener(JButton btnStart, JTextArea leagueResult, JTextField txtFilePath) {
    this.btnStart     = btnStart;
    this.leagueResult = leagueResult;
    this.txtFilePath  = txtFilePath;
  }

  /**
   * Action that is going to be trigger once that the start league button is pressed. Then the
   * listener is going to call the Span-League-Engine module which is included as a dependency in
   * this project. The Listener receives the location of the league file through a text field to
   * start the file processing of the league.
   *
   * @param event the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent event) {

    if (txtFilePath.getText().isBlank()) {
      log.warn("Cannot Start League Simulation Reason: {}", EMPTY_FILE_PATH_WARNING_MESSAGE);
      JOptionPane.showMessageDialog(btnStart, EMPTY_FILE_PATH_WARNING_MESSAGE,
          ComponentProperties.SPAN_WARN_TITLE, JOptionPane.WARNING_MESSAGE);
      return;
    }

    try {
      leagueResult.setForeground(ColorProperties.BLUE);
      leagueResult.setText(ComponentProperties.EMPTY_MESSAGE);

      String leagueFile = txtFilePath.getText();
      log.info("Starting League Simulation at: {}", leagueFile);
      LeagueEngine leagueEngine = new LeagueEngine();
      OperationResult operationResult = leagueEngine.runLeague(leagueFile);

      if (operationResult.getOperationResultCode() != OperationResultCode.SUCCESS) {
        operationResult.throwError();
      }

      log.info("Getting League Results");
      String leagueResults = leagueEngine.printLeagueResults(leagueEngine.getLeagueLoader()
          .getLeagueRanking().getRanking());

      leagueResult.setText(leagueResults);
    } catch (OperationException e) {
      log.error(e.getMessage());

      JOptionPane.showMessageDialog(btnStart, LEAGUE_ERROR_MESSAGE,
          ComponentProperties.SPAN_ERROR_TITLE, JOptionPane.ERROR_MESSAGE);

      leagueResult.setForeground(ColorProperties.RED);
      leagueResult.append(e.getMessage());
    }
  }
}
