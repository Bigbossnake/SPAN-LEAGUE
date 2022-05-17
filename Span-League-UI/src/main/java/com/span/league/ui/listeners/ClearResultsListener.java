package com.span.league.ui.listeners;

import com.span.league.ui.components.ComponentProperties;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import lombok.extern.log4j.Log4j2;

/**
 * Action Listener that responds to the OnClick Event from a Button to clear the Labels and the Text fields
 * from the UI.
 */
@Log4j2
public class ClearResultsListener implements ActionListener {

  private static final String TEXT_AREA_IS_ALREADY_CLEANED_MESSAGE  = "Results Area Is Already Cleaned";

  private final JTextArea txtLeagueResult;
  private final JButton btnClearResults;
  private final JTextField txtFilePath;

  /**
   * Listener constructor that receives the references of the Swing components that is going to be
   * able to interact with them.
   *
   * @param btnClearResults Swing Component Reference that is used as a parent of the listener
   * @param txtLeagueResult text field that the listener is able to interact
   * @param txtFilePath text field that the listener is able to interact
   */
  public ClearResultsListener(
      JButton btnClearResults, JTextArea txtLeagueResult, JTextField txtFilePath) {
    this.txtLeagueResult = txtLeagueResult;
    this.btnClearResults = btnClearResults;
    this.txtFilePath     = txtFilePath;
  }

  /**
   * Action Listener that responds to the OnClick Event from a Button to clear the Labels and the Text fields
   * from the UI.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {

    if (txtLeagueResult.getText().isBlank()) {
      log.info(TEXT_AREA_IS_ALREADY_CLEANED_MESSAGE);
      JOptionPane.showMessageDialog(btnClearResults, TEXT_AREA_IS_ALREADY_CLEANED_MESSAGE,
          ComponentProperties.SPAN_WARN_TITLE, JOptionPane.WARNING_MESSAGE);
      return;
    }

    txtFilePath.setText(ComponentProperties.EMPTY_MESSAGE);
    txtLeagueResult.setText(ComponentProperties.EMPTY_MESSAGE);
  }
}
