package com.span.league.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import lombok.extern.log4j.Log4j2;

/**
 * Action Listener that responds to the OnClick Event from the Open File Menu, to create a file
 * chooser and allow the user to browse the File System and choose the league file that is going to
 * be run by the Span-League-Engine module which is included as a dependency in this project. By
 * default, the file chooser looks for a Span-League-File that ends with an extension .league,
 * although the filter can be removed from the file chooser to allow the user choose any other
 * text-based file, as long as contains a valid structure the Span-League-Engine should be able to
 * processed.
 */
@Log4j2
public class ChooseFileListener implements ActionListener {

  private static final String LEAGUE_FILTER_DESCRIPTION = "League Files";
  private static final String LEAGUE_FILTER_TXT_EXTENSION   = "league";

  private final JMenuItem openMenuItem;
  private final JTextField txtFilePath;

  /**
   * Listener constructor that receives the references of the Swing components that is going to be
   * able to interact with them.
   *
   * @param openMenuItem Swing Component Reference that is used as a parent of the listener
   * @param txtFilePath Swing Component Reference that is used to show the file path in a Text Field
   */
  public ChooseFileListener(JMenuItem openMenuItem, JTextField txtFilePath) {
    this.openMenuItem = openMenuItem;
    this.txtFilePath = txtFilePath;
  }

  /**
   * Listener Method that responds to the OnClick Event from the Open File Menu, to create a file
   * chooser and allow the user to browse the File System and choose the league file that is going
   * to be run by the Span-League-Engine module which is included as a dependency in this project.
   * If the user chose a file, the file path is going to be shown in the main application GUI in
   * a TextField.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    log.info("Creating File Chooser To Choose A Valid League File");

    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter imgFilter = new FileNameExtensionFilter(LEAGUE_FILTER_DESCRIPTION,
        LEAGUE_FILTER_TXT_EXTENSION);

    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    fileChooser.setFileFilter(imgFilter);

    int userAction = fileChooser.showOpenDialog(openMenuItem);

    if (userAction != JFileChooser.CANCEL_OPTION) {
      String leagueFilePath = fileChooser.getSelectedFile().getPath();
      log.info("League File Was Located At: {}", leagueFilePath);

      txtFilePath.setText(leagueFilePath);
    }

  }
}
