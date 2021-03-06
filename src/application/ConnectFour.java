package application;

import java.awt.Color;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import view.Frame;
import view.PreGameDialog;
import model.Globals;
import model.Grid;
import model.Player;

import org.apache.log4j.Logger;

import controller.Controller;

/**
 * ==============================================================
 * When I wrote this, only God and I understood what I was doing
 * Edit: Now, God only knows
 * ==============================================================
 * @author: If this code works, it was written by Stefan Gebhart. 
 *          If not, I don't know who wrote it
 * ==============================================================
 * @version: 1.6.1
 * latest changes: -replaced tabs with whitespaces
 *                 -added some helpful comments in this class
 * ==============================================================           
 * @project.mascot:
 * 
 *   _._ _..._ .-',     _.._(`))
 *   '-. `     '  /-._.-'    ',/
 *     )         \            '.
 *    / _    _    |             \
 *   |  a    a    /              |
 *   \   .-.                     ;  
 *    '-('' ).-'       ,'       ;
 *       '-;           |      .'
 *          \           \    /
 *          | 7  .__  _.-\   \
 *          | |  |  ``/  /`  /
 *         /,_|  |   /,_/   /
 *            /,_/      '`-'
 * ==============================================================
 * @TheProgramWithTheHat
 * 
 * 
 *                        oooo     oooo
 *                      o$$"""$oo$$"""""$o
 *                     $"      $$"      $$$$
 *                    $"      ""        $$$$$o
 *                    $                  $$$$$o
 *                   $                    $$$$$$
 *                  $"                    "$$$$$
 *                  $                      $$$$$$
 *                 $"                      $$$$$$
 *                 $                        $$$$$
 *                 $                       o$$$$$
 *                 $                       $$$$$$
 *                 $                      o$$$$$$
 *              ooo                      o$$$$$$$
 *      ooo$$$$"" $                   oo$$$$$""""""oooo
 *   oo"$$$$$$$ oo"" oooooooooooooooo$$"""           o$$"oo
 *  o"  $$$$$$$ "$o           oo$$$$$"               $$$$o"$o
 * $    $$$$$$$  " ""oooooooooo$$$$"         o$      $$$$$$o"$
 *o     $$""               oo$$$"           o$$     o$$$$$$$o$
 *"o    $$             oo$$$$""            o$$$   o$$$$$$$$$$$
 * "$o  $$$oo                           $$$$$$$   ooo$$$$$""
 *   "$$oooo ""            ooo$$$$      $$$$$$$$$$$$$$""
 *       """"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$""""""
 *                 """"""""""""""""""
 *                 
 * ==============================================================
 */
public class ConnectFour {

    private Grid grid;
    private Controller controller;
    private Frame frame;
    private Globals gv;
    private Logger log = Logger.getLogger(ConnectFour.class);
    private static final int BLACK = 1;
    private static final int LIGHT_GRAY = 2;
    private static final int RED = 3;
    private static final int ORANGE = 4;
    private static final int YELLOW = 5;
    private static final int GREEN = 6;
    private static final int BLUE = 7;
    private static final int PINK = 8;
    private static final int SIX = 6;
    private static final int SEVEN = 7;

    /**
     * constructor
     * @param isTest true when using unit test; else false
     * @param row row
     * @param col col
     */
    public ConnectFour(boolean isTest) {
    	int row;
    	int col;
		if (!isTest) {
			PreGameDialog getGridSize = new PreGameDialog(null);
			getGridSize.showDialog();
			row = getGridSize.getRowSize();
			col = getGridSize.getColumnSize();
		} else {
			row = SIX;
			col = SEVEN;
		}
    	gv = new Globals(row, col);
        grid = new Grid(isTest, gv);
        Player player = new Player();
        controller = new Controller(grid, player, gv);
        frame = new Frame(grid, controller, player, gv);
        controller.addObserver(frame);

    }

    /**
     * loop for turns
     */
    private void controlLoop() {
        Scanner scanner = new Scanner(System.in);
        String line = "";
        controller.newGrid();

        log.info("Enter \"help\" to see available commands.");

        boolean cont = true;
        while (cont) {
            frame.update();
            line = scanner.next();
            cont = handleInput(line);
        }
        scanner.close();
        controller.doExit();
    }

    /**
     * handles the given inputvalue 
     * ---Java 1.6 cannot switch Strings---
     * @param line: Input from Scanner
     * @return: false if the input is "quit" or 
     *          "exit" true if the game continues
     */
    public boolean handleInput(String line) {

        // quit the game
        if (line.equalsIgnoreCase("quit")) {
            return false;
        }
        
        // quit the game
        if (line.equalsIgnoreCase("exit")) {
            return false;
        }
        
        // help quest
        if (line.equalsIgnoreCase("help")) {
            printHelpMessage();
            return true;
        }
        
        // starts a new game
        if (line.equalsIgnoreCase("new")) {
            controller.newGrid();
            return true;
        }

        // set name for player1
        if (line.startsWith("n1")) {
            String name = line.substring(2, line.length());
            controller.setPlayernames(name, "");
            return true;
        }
        
        // set name for player2
        if (line.startsWith("n2")) {
            String name = line.substring(2, line.length());
            controller.setPlayernames("", name);
            return true;
        }
        
        // set color for player1
        if (line.startsWith("c1")) {
            setColorForPlayer(line, 1);
            return true;
        }
        
        // set color for player2
        if (line.startsWith("c2")) {
            setColorForPlayer(line, 2);
            return true;
        }
        
        // handles input 1-7
        String validLines = "[1-" + gv.getColSize() + "]";
        if (line.matches(validLines)) {
            handleNumberInput(line);
            return true;
        }
        
        // in case there was no valid input
        log.warn("invalid input");
        return true;
    }
    
    /**
     * handles the inputs 1-7 and ignore all other inputs
     * @param input: input as string
     */
    private void handleNumberInput(String input){
        Pattern p = Pattern.compile("[1-9]");
        Matcher m = p.matcher(input);
        m.find();
        int arg = Integer.parseInt(m.group());
        arg = arg - 1;
        int i = grid.getHeight(arg);
        if (i == -1) {
            return;
        }
        controller.setValue(grid.getCell(i, arg));
        log.info(grid.toString());
        log.info(grid.getStatus().getText());
    }

    /**
     * prints the help with all available TUI inputs to console
     * 
     *  log.info("\t \t  _________________________________________________________________________________________");
     *  log.info("\t  __\t / It seems like you need help with TUI-commands:                                          \\");
     *  log.info("\t /  \\\t |                                                                                         |");
     *  log.info("\t |  |\t | n1[Name]: change the name of Player 1                                                   |");
     *  log.info("\t @  @\t | n2[Name]: change the name of Player 2                                                   |");
     *  log.info("\t || ||\t | c1[ColorNumber]: change the color of Player 1                                           |");
     *  log.info("\t || ||\t<= c2[ColorNumber]: change the color of Player 2                                           |");
     *  log.info("\t |\\_/|\t | --Available colors: 1=Black, 2=Gray, 3=Red, 4=Orange, 5=Yellow, 6=Green, 7=Blue, 8=Pink |");
     *  log.info("\t \\___/\t | new: start a new game                                                                   |");
     *  log.info("\t \t | quit: exit the game                                                                     |");
     *  log.info("\t \t  \\________________________________________________________________________________________/");
     */
    private void printHelpMessage() {

        log.info("Possible TUI-input:");
        log.info("\t [1-7]: the column you want to put in your token");
        log.info("\t n1[Name]: change the name of Player 1");
        log.info("\t n2[Name]: change the name of Player 2");
        log.info("\t c1[ColorNumber]: change the color of Player 1");
        log.info("\t c2[ColorNumber]: change the color of Player 2");
        log.info("\t --Available colors: 1=Black, 2=Gray, 3=Red, 4=Orange, 5=Yellow, 6=Green, 7=Blue, 8=Pink");
        log.info("\t help: request help");
        log.info("\t new: start a new game");
        log.info("\t quit: exit the game");
    }
    
    /**
     * sets the color for player
     * @param line line
     * @param player player
     */
    private void setColorForPlayer(String line, int player){
        String input = line.substring(2, line.length());
        try {
            int colNo = Integer.parseInt(input);
            Color retCol = getColor(colNo);
            if (retCol != null) {
                if (player == 1){
                    controller.setColors(retCol, null);                    
                } else {
                    controller.setColors(null, retCol);                    
                }
            }
        } catch (NumberFormatException e) {
            log.warn("invalid input");
        }
    }

    /**
     * returns the Color for the given inputnumber 
     * ---java 1.6 cannot switch Strings---
     * @param col column
     * @return return number of the color
     */
    private Color getColor(int col) {
        switch (col) {
        case BLACK:
            return Color.BLACK;
        case LIGHT_GRAY:
            return Color.LIGHT_GRAY;
        case RED:
            return Color.RED;
        case ORANGE:
            return Color.ORANGE;
        case YELLOW:
            return Color.YELLOW;
        case GREEN:
            return Color.GREEN;
        case BLUE:
            return Color.BLUE;
        case PINK:
            return Color.PINK;
        }
        return null;
    }

    /**
     * main; Enter "help" if you want to know how to use the program
     * 
     * @                                    /\  /\
     *  @                                  /  \/  \
     *   @                                /        --
     *    \---\                          /           \
     *     |   \------------------------/       /-\    \
     *     |                                    \-/     \
     *      \                                             ------O
     *       \                                                 /
     *        |    |                    |    |                /
     *        |    |                    |    |-----    -------
     *        |    |\                  /|    |     \  WWWWWW/
     *        |    | \                / |    |      \-------
     *        |    |  \--------------/  |    |
     *       /     |                   /     |
     *       \      \                  \      \
     *        \-----/                   \-----/
     * 
     * @param args: unused
     * creates a dialog box to get Gridsize before the game starts
     */
    public static void main(String[] args) {
        new ConnectFour(false).controlLoop();

    }

}
