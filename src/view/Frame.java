package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import controller.Controller;
import observer.IObserver;
import model.Grid;
import model.Player;
import model.Status;

/**
 * Frame for GUI
 * @author stgebhar
 */
public class Frame extends JFrame implements IObserver {

    private static final long serialVersionUID = 1L;
    private Controller controller;
    private Status status;
    private Player player;
    private Grid grid;
    private StatusPanel statusPanel;
    private GridPanel gridPanel;
    private PlayerNamesDialog playerNamesDialog;
    private PlayerColorDialog playerColorDialog;
    private static final int WIDTH = 528;
    private static final int HEIGH = 630;

    private Container pane;

    /**
     * constructor
     * @param grid grid
     * @param controller controller
     */
    public Frame(final Grid grid, final Controller controller,
            final Player player) {
        this.status = grid.getStatus();
        this.controller = controller;
        this.player = player;
        this.grid = grid;

        setTitle("Vier Gewinnt");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGH);
        pane = getContentPane();
        pane.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        /**
         * New Game Menu
         */
        JMenuItem newMenuItem = new JMenuItem("New game");
        newMenuItem.addActionListener(new ActionListener() {
            /**
             * New Game ActionListener
             */
            public void actionPerformed(ActionEvent e) {
                int n = JOptionPane.showConfirmDialog(null, "Are you sure?",
                        "Creating a new game", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    controller.newGrid();
                }
            }
        });

        fileMenu.add(newMenuItem);
        fileMenu.addSeparator();

        /**
         * Options menu
         */
        JMenu optionsMenuItem = new JMenu("Options");

        /**
         *  options-submenu playernames
         */
        JMenuItem playerSettings = new JMenuItem("Set Playernames");
        playerSettings.addActionListener(new ActionListener() {
            /**
             * ActionListener for SetPlayernames
             */
            public void actionPerformed(ActionEvent e) {
                String p1 = player.getPlayerOneName();
                String p2 = player.getPlayerTwoName();

                playerNamesDialog.showDialog(p1, p2);
                controller.setPlayernames(playerNamesDialog.getNameOne(),
                        playerNamesDialog.getNameTwo());

            }
        });

        /**
         *  options-submenu colors
         */
        JMenuItem colorSettings = new JMenuItem("Set Colors");
        colorSettings.addActionListener(new ActionListener() {
            /**
             * ActionListener for colors
             */
            public void actionPerformed(ActionEvent e) {
                String p1 = player.getPlayerOneName();
                String p2 = player.getPlayerTwoName();

                playerColorDialog.showDialog(player.getPlayerOneColor(),
                        player.getPlayerTwoColor(), p1, p2);
                controller.setColors(playerColorDialog.getP1Color(),
                        playerColorDialog.getP2Color());
            }
        });
        optionsMenuItem.add(playerSettings);
        optionsMenuItem.add(colorSettings);

        fileMenu.add(optionsMenuItem);
        fileMenu.addSeparator();

        /**
         *  save menu
         */
        JMenuItem saveMenuItem = new JMenuItem("Save Game");
        saveMenuItem.addActionListener(new ActionListener() {
            /**
             * ActionListener savegame
             */
            public void actionPerformed(ActionEvent event) {
                saveGame(Frame.this);
            }
        });
        fileMenu.add(saveMenuItem);

        /**
         *  load menu
         */
        JMenuItem loadMenuItem = new JMenuItem("Load Game");
        loadMenuItem.addActionListener(new ActionListener() {
            /**
             * ActionListener loadgame
             */
            public void actionPerformed(ActionEvent event) {
                loadGame(Frame.this);
            }
        });
        fileMenu.add(loadMenuItem);
        fileMenu.addSeparator();

        /**
         *  quit menu
         */
        JMenuItem quitMenuItem = new JMenuItem("Quit");
        quitMenuItem.addActionListener(new ActionListener() {
            /**
             * ActionListener quit menu
             */
            public void actionPerformed(ActionEvent event) {
                int n = JOptionPane.showConfirmDialog(null, "Are you sure?",
                        "Quit game", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    controller.doExit();
                }

            }
        });
        fileMenu.add(quitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        playerNamesDialog = new PlayerNamesDialog(this);
        playerColorDialog = new PlayerColorDialog(this);

        constructPane();
    }

    /**
     * buildes the components
     * @param grid grid
     */
    public final void constructPane() {

        if (gridPanel != null) {
            pane.remove(gridPanel);
        }
        gridPanel = new GridPanel(grid, controller, player);
        pane.add(gridPanel, BorderLayout.CENTER);

        if (statusPanel != null) {
            pane.remove(statusPanel);
        }
        statusPanel = new StatusPanel();
        pane.add(statusPanel, BorderLayout.SOUTH);
        setVisible(true);
        repaint();
    }

    /**
     * saves a game
     * @param frame JFrame
     */
    public void saveGame(JFrame frame) {
        JFileChooser fileChooser = new JFileChooser(".");
        int result = fileChooser.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (file.exists()
                    && JOptionPane.showConfirmDialog(frame,
                            "File \"" + file.getName() + "\" already exists.\n"
                                    + "Would you like to replace it?", "Save",
                            JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                return;
            }
            try {
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream outStream = new ObjectOutputStream(fos);
                outStream.writeObject(grid.vaulesToString());
                outStream.flush();
                outStream.close();

            } catch (IOException e) {
                JOptionPane.showMessageDialog(
                        frame,
                        "IOException saving game:\n"
                                + e.getLocalizedMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * loads a saved game
     * @param frame JFrame
     */
    public void loadGame(JFrame frame) {
        JFileChooser fileChooser = new JFileChooser(".");
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                FileInputStream fis = new FileInputStream(
                        fileChooser.getSelectedFile());
                ObjectInputStream inStream = new ObjectInputStream(fis);
                controller.setCellsFromLoad((String) inStream.readObject());
                inStream.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(
                        frame,
                        "IOException reading connect4:\n"
                                + e.getLocalizedMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(
                        frame,
                        "IOException reading connect4:\n"
                                + e.getLocalizedMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * update / repaint
     */
    public void update() {
        statusPanel.setText(status.getText());
        repaint();
    }
}
