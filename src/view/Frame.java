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
import model.Globals;
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
    private Globals gv;
    private StatusPanel statusPanel;
    private GridPanel gridPanel;
    private PlayerNamesDialog playerNamesDialog;
    private PlayerColorDialog playerColorDialog;
    private GridSizeDialog gridSizeDialoag;
    private static final int WIDTH = 528;
    private static final int HEIGH = 630;
    private Container pane;

    /**
     * constructor
     * @param grid grid
     * @param controller controller
     */
    public Frame(final Grid grid, final Controller controller,final Player player, Globals globals) {
        this.status = grid.getStatus();
        this.controller = controller;
        this.player = player;
        this.grid = grid;
        this.gv = globals;

        setTitle("Vier Gewinnt");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGH);
        pane = getContentPane();
        pane.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        // new game menu
        JMenuItem newMenuItem = createNewMenu();
        fileMenu.add(newMenuItem);
        fileMenu.addSeparator();

        // options menu
        JMenu optionsMenuItem = new JMenu("Options");
        // playername submenu
        JMenuItem playerSettings = createPlayerNameMenu();
        // color submenu
        JMenuItem colorSettings = createColorMenu();
        
        // gridSize submenu
        JMenuItem gridSettings = createGridMenu();
        
        optionsMenuItem.add(playerSettings);
        optionsMenuItem.add(colorSettings);
        optionsMenuItem.add(gridSettings);

        fileMenu.add(optionsMenuItem);
        fileMenu.addSeparator();

        // save menu
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

        // load menu
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

        // exit menu
        JMenuItem quitMenuItem = new JMenuItem("Quit");
        createQuitMenu(quitMenuItem);
        fileMenu.add(quitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        playerNamesDialog = new PlayerNamesDialog(this);
        playerColorDialog = new PlayerColorDialog(this);
        gridSizeDialoag = new GridSizeDialog(this, gv);

        constructPane();
    }

    private JMenuItem createGridMenu() {
    	JMenuItem gridSettings = new JMenuItem("Set Gridsize");
    	gridSettings.addActionListener(new ActionListener() {
            /**
             * ActionListener for SetPlayernames
             */
            public void actionPerformed(ActionEvent e) {
            	gridSizeDialoag.showDialog();
// CURRENTLY UNUSED
//                int newColSize = gridSizeDialoag.getColumnSize();
//                int newRowSize = gridSizeDialoag.getRowSize();
            }
        });
		return gridSettings;
	}

	/**
     * createExitMenuItem
     * @param quitMenuItem
     */
	private void createQuitMenu(JMenuItem quitMenuItem) {
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
	}

	/**
	 * createColorMenu
	 * @return JMenuItem
	 */
	private JMenuItem createColorMenu() {
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
		return colorSettings;
	}

	/**
	 * createOptionsMenu
	 * @return JMenuItem
	 */
	private JMenuItem createPlayerNameMenu() {
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
		return playerSettings;
	}

	/**
	 * new game menu
	 * @return JMenuItem
	 */
	private JMenuItem createNewMenu() {
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
		return newMenuItem;
	}

    /**
     * buildes the components
     * @param grid grid
     */
    public final void constructPane() {

        if (gridPanel != null) {
            pane.remove(gridPanel);
        }
        gridPanel = new GridPanel(grid, controller, player, gv);
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
     * prints a win-message if someone won the game
     */
    public void update() {
        statusPanel.setText(status.getText());
        repaint();
        if(player.hasWon() && !player.getWinnerMessageHasBeenShown() && !grid.getIsTest()){
        	showWinMessage();
        }
    }
    
    /**
     * shows a message if a player has won the game
     */
    private void showWinMessage(){
    	
    	String message = "Player " + player.getWinner() + " has won the game!";
    	String title = "Player " + player.getWinner() + " has won!";
    	
    	Object[] options = {"Continue playing", "Start new game", "Exit"};
    	int n = JOptionPane.showOptionDialog(null,message, title,
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
					null, options, options[2]);
    	player.setWinnerMessageHasBeenShown(true);
    	if(n == 1){
    		controller.newGrid();
    	} else if(n == 2){
    		controller.doExit();
    	}
    }
    
}
