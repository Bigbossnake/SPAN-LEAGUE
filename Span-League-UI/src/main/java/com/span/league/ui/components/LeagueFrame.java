package com.span.league.ui.components;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.span.league.ui.listeners.ChooseFileListener;
import com.span.league.ui.listeners.ClearResultsListener;
import com.span.league.ui.listeners.StartLeagueListener;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.FontUIResource;
import lombok.extern.log4j.Log4j2;

/**
 * Class that extends from JFrame which is responsible to create, configure and show the GUI for the
 * application.
 */
@Log4j2
public class LeagueFrame extends JFrame {

  // UI - COMPONENTS
  private final JFrame mainFrame;
  private final JMenuBar leagueMenuBar;
  private final JMenu fileMenu;
  private final JMenuItem openMenuItem;
  private final JPanel mainPanel;
  private final JLabel lblFilePath;
  private final JTextField txtFilePath;
  private final JButton btnStart;
  private final JLabel lblResults;
  private final JTextArea txtLeagueResult;
  private final JButton btnClearResults;

  /**
   * Utility method that configures the GUI look and feel as well as the font for the different swing
   * components through the UIManager. Note that if an error occurs when the GUI is being configured
   * through the UIManager. The UIManager will throw an UnsupportedLookAndFeelException causing the
   * application terminating abruptly.
   */
  public void setupLookAndFeel() {

    try {
      log.info("Configuring Application Look and Feel");

      // We Set Up The UI Theme
      UIManager.setLookAndFeel(new FlatDarculaLaf());

      // We Set Up The UI Fonts
      Font labelFont     = new Font(FontProperties.LABEL_FONT_TYPE, Font.BOLD, FontProperties.LABEL_FONT_SIZE);
      Font buttonFont    = new Font(FontProperties.BUTTON_FONT_TYPE, Font.BOLD, FontProperties.BUTTON_FONT_SIZE);
      Font textFieldFont = new Font(FontProperties.TEXT_FILED_FONT_TYPE, Font.PLAIN, FontProperties.TEXT_FIELD_FONT_SIZE);
      Font textAreaFont  = new Font(FontProperties.TEXT_AREA_FONT_TYPE, Font.PLAIN, FontProperties.TEXT_AREA_FONT_SIZE);
      Font menuBarFont   = new Font(FontProperties.MENU_BAR_FONT_TYPE, Font.BOLD, FontProperties.MENU_BAR_FONT_SIZE);
      Font menuItemFont  = new Font(FontProperties.MENU_ITEM_FONT_TYPE, Font.PLAIN, FontProperties.MENU_ITEM_FONT_SIZE);
      Font panelFont     = new Font(FontProperties.PANEL_FONT_TYPE, Font.BOLD, FontProperties.PANEL_FONT_SIZE);

      // We Tell The UIManager Which Font Use
      UIManager.put(FontProperties.LABEL_FONT_KEY, new FontUIResource(labelFont));
      UIManager.put(FontProperties.BUTTON_FONT_KEY, new FontUIResource(buttonFont));
      UIManager.put(FontProperties.TEXT_FILED_FONT_KEY, new FontUIResource(textFieldFont));
      UIManager.put(FontProperties.TEXT_AREA_FONT_KEY, new FontUIResource(textAreaFont));
      UIManager.put(FontProperties.MENU_BAR_FONT_KEY, new FontUIResource(menuBarFont));
      UIManager.put(FontProperties.MENU_ITEM_FONT_KEY, new FontUIResource(menuItemFont));
      UIManager.put(FontProperties.PANEL_FONT_KEY, new FontUIResource(panelFont));

    } catch (UnsupportedLookAndFeelException unsupportedLookAndFeelException) {
      log.error("Could Not Configure Application Look And Feel Caused By: ");
      unsupportedLookAndFeelException.printStackTrace();

      log.warn("Nothing To Do, System Will Exited");
      System.exit(ComponentProperties.EXIT_CODE);
    }

  }

  /**
   * Utility method that configures the application layout for the Main JFrame to show the Swing
   * components in concordance with its position definition.
   */
  public void initLeagueFrame() {
    log.info("Adding UI Components To The Main Frame");

    // Configuring The Main Frame
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setSize(ComponentProperties.WINDOW_WIDTH, ComponentProperties.WINDOW_HIGH);
    mainFrame.setResizable(false);

    // Adding components to each menu
    leagueMenuBar.add(fileMenu);
    fileMenu.add(openMenuItem);

    // Configuration Of The Main Panel
    mainPanel.setBackground(ColorProperties.GRAY);
    mainPanel.setBorder(BorderFactory.createTitledBorder(
        BorderFactory.createEtchedBorder(), ComponentProperties.MAIN_PANEL_TITLE));

    GridBagConstraints panelConstraints = new GridBagConstraints();
    panelConstraints.fill = GridBagConstraints.HORIZONTAL;
    panelConstraints.insets = new Insets(
        ComponentProperties.MAIN_PANEL_MARGIN_TOP,
        ComponentProperties.MAIN_PANEL_MARGIN_LEFT,
        ComponentProperties.MAIN_PANEL_MARGIN_BUTTON,
        ComponentProperties.MAIN_PANEL_MARGIN_RIGHT);

    // Adding Components Of The Main Panel
    panelConstraints.gridx = 0;
    panelConstraints.gridy = 0;
    mainPanel.add(lblFilePath, panelConstraints);

    panelConstraints.gridx = 1;
    panelConstraints.gridy = 0;
    mainPanel.add(txtFilePath, panelConstraints);

    panelConstraints.gridx = 2;
    panelConstraints.gridy = 0;
    btnStart.setForeground(ColorProperties.GREEN);
    mainPanel.add(btnStart, panelConstraints);

    panelConstraints.gridx = 0;
    panelConstraints.gridy = 1;
    lblResults.setForeground(ColorProperties.YELLOW);
    mainPanel.add(lblResults, panelConstraints);

    panelConstraints.gridx = 0;
    panelConstraints.gridy = 2;
    panelConstraints.gridwidth = 3;

    txtLeagueResult.setLineWrap(ComponentProperties.ENABLED);
    txtLeagueResult.setWrapStyleWord(ComponentProperties.ENABLED);
    txtLeagueResult.setEditable(!ComponentProperties.IS_EDITABLE);
    txtLeagueResult.setRows(ComponentProperties.TEXT_AREA_ROWS);
    mainPanel.add(txtLeagueResult, panelConstraints);

    panelConstraints.gridx = 0;
    panelConstraints.gridy = 3;
    panelConstraints.gridwidth = 3;
    btnClearResults.setForeground(ColorProperties.ORANGE);
    mainPanel.add(btnClearResults, panelConstraints);

    // Adding All UI Components To The Main Frame
    mainFrame.getContentPane().add(BorderLayout.NORTH, leagueMenuBar);
    mainFrame.getContentPane().add(BorderLayout.CENTER, mainPanel);
    mainFrame.setVisible(true);
    mainFrame.pack();
  }

  /**
   * Utility Method that adds the application listeners to the required UIComponents to response
   * the OnClick Event, and trigger the different actions defined in the Class Listener.
   *
   * @see com.span.league.ui.listeners.ChooseFileListener
   * @see com.span.league.ui.listeners.StartLeagueListener
   * @see com.span.league.ui.listeners.ClearResultsListener
   */
  public void initListeners() {
    log.info("Adding Listeners to the UI Components");

    ChooseFileListener chooseFileListener = new ChooseFileListener(openMenuItem, txtFilePath);
    StartLeagueListener startLeagueListener = new StartLeagueListener(btnStart, txtLeagueResult, txtFilePath);
    ClearResultsListener clearResultsListener = new ClearResultsListener(btnClearResults, txtLeagueResult, txtFilePath);

    openMenuItem.addActionListener(chooseFileListener);
    btnStart.addActionListener(startLeagueListener);
    btnClearResults.addActionListener(clearResultsListener);
  }

  /**
   * Class constructor responsible to call the methods to:
   * - Set Up the look and feel of the application
   * - Add the listeners to the required UI-Components
   * - Create the main Swing UI Components
   * - Initialize the application layout and show the UI to the User
   */
  public LeagueFrame() {
    log.info("Initializing GUI Application...");

    // Configure Look And Feel Theme
    setupLookAndFeel();

    // Creating the Frame And Its Layout
    mainFrame = new JFrame(ComponentProperties.WINDOW_TITLE);

    // Creating the MenuBar
    leagueMenuBar = new JMenuBar();
    fileMenu      = new JMenu(ComponentProperties.FILE_MENU);
    openMenuItem  = new JMenuItem(ComponentProperties.FILE_OPEN_MENU);

    // Creating the Main JPanel
    mainPanel       = new JPanel(new GridBagLayout(), ComponentProperties.BUFFERED_ENABLED);
    lblFilePath     = new JLabel(ComponentProperties.LBL_FILE_PATH_TEXT);
    txtFilePath     = new JTextField(ComponentProperties.TXT_FIELD_COLUMNS);
    btnStart        = new JButton(ComponentProperties.START_LEAGUE_BUTTON_TXT);
    lblResults      = new JLabel(ComponentProperties.LBL_RESULTS_TEXT);
    txtLeagueResult = new JTextArea();
    btnClearResults = new JButton(ComponentProperties.CLEAR_BUTTON_TXT);

    // Adds Listeners To The UI
    initListeners();

    // Initialization Of The UI
    initLeagueFrame();
  }

}
