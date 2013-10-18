package connect4;

import java.awt.Color;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controller.Controller;
import view.Frame;
import model.Grid;
import model.Player;

/**
 *                             _
	   _._ _..._ .-',     _.._(`))
	   '-. `     '  /-._.-'    ',/
	      )         \            '.
	     / _    _    |             \
	    |  a    a    /              |
	    \   .-.                     ;  
	     '-('' ).-'       ,'       ;
	        '-;           |      .'
	           \           \    /
	           | 7  .__  _.-\   \
	           | |  |  ``/  /`  /
	          /,_|  |   /,_/   /
	             /,_/      '`-'
 * TODO:
 * 		 -immerhin 90,5% code Coverage
 * 		 -Wenn gewonnen 4 steine hervorheben oder linie ziehen, aufteilen des wincheck in 4 methoden dazu hilfreich
 * @author stgebhar
 * @version 1.5.7
 *   + mehr farben
 *   + Namen+Farben werden über controller und dadurch mit Observerbenachrichtigung geändert => sofort aktualisiert
 *   ++ namen in TUI änderbar
 *   ++ farben in tui änderbar
 *   +++ kleiner fix bei farbenwahl
 *   +++ verminderung der checkstyle-warnings
 *   +++ es wird jetzt immer der Sieger als "has won the game" angezeigt, auch falls weitergespielt wird
 *   ++++ "Tie" wird nur noch angezeigt falls wirklich niemand gewonnen hat nach 42 Steinen
 *   ++++ neue tests
 *   +++++ Verminderung der MagicNumbers
 *   +++++ neues Spiel kann jetzt auch per TUI aufgerufen werden und bringt entsprechende Benachrichtigungen
 *   +++++ newGrid resettet jetzt auch turn und player
 *   +++++ keiner helpoutput-fix
 *   ++++++ Bei Colorwahl wird jetzt immer der aktuelle Name angezeigt anstelle von "Player1/2"
 *   ++++++ Neue returnMethoden bei Player, genutzt in Frame. Bei c4 Player jetzt als klassenvariable
 *   ++++++ Bug bei Player gefixt durch neue Methoden, aufruf im Controller
 *   +++++++ confirmDialogs bei new/quit in gui, quit in gui macht jetzt doexit anstelle von system.exit 
 */
public class ConnectFour {
	
	private Grid grid;
	private Player player;
	private Controller controller;
	private Frame frame;
	private static final int BLACK = 1;
	private static final int LIGHT_GRAY = 2;
	private static final int RED = 3;
	private static final int ORANGE = 4;
	private static final int YELLOW = 5;
	private static final int GREEN = 6;
	private static final int BLUE = 7;
	private static final int PINK = 8;
	
	/**
	 * constructor
	 */
	public ConnectFour(){
		grid = new Grid();
		player = new Player();
		controller = new Controller(grid, player);
		frame = new Frame(grid,controller, player);
		controller.addObserver(frame);
		
	}
	
	/**
	 * loop for turns
	 */
	private void controlLoop(){
		Scanner scanner = new Scanner (System.in);
		String line = "";
		controller.newGrid();
		
		System.out.println("Enter \"help\" to see available commands.");
		
		boolean cont = true;
		while(cont){
			frame.update();
		    line = scanner.next();
			cont = handleInput(line);
		}
		controller.doExit();
	}
	
	/**
	 * handles the given inputvalue
	 * @param line
	 * @return
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
			System.out.println("Possible TUI-input:");
			System.out.println("\t [1-7]: the column you want to put in your token");
			System.out.println("\t n1[Name]: change the name of Player 1");
			System.out.println("\t n2[Name]: change the name of Player 2");
			System.out.println("\t c1[ColorNumber]: change the color of Player 2");
			System.out.println("\t c2[ColorNumber]: change the color of Player 2");
			System.out.println("\t --Available colors: 1=Black, 2=Gray, 3=Red, 4=Orange, 5=Yellow, 6=Green, 7=Blue, 8=Pink");
			System.out.println("\t help: request help");
			System.out.println("\t new: start a new game");
			System.out.println("\t quit: exit the game");
			return true;
		}
		// starts a new game
		if(line.equalsIgnoreCase("new")){
			controller.newGrid();
			return true;
		}
		
		// set name for player1
		if(line.startsWith("n1")) {
			String name = line.substring(2, line.length());
			controller.setPlayernames(name, "");
			return true;
		}
		//set name for player2
		if(line.startsWith("n2")){
			String name = line.substring(2, line.length());
			controller.setPlayernames("", name);
			return true;
		}
		// set color for player1
		if(line.startsWith("c1")){
			String input = line.substring(2, line.length());
			try { 
		        int colNo = Integer.parseInt(input);
		        Color retCol = getColor(colNo);
		        if (retCol != null){
		        	controller.setColors(retCol, null);
		        	return true;
		        }
		    } catch(NumberFormatException e) {
		    	System.out.println("invalid input");
		        return true; 
		    }
		}
		// set color for player2
		if(line.startsWith("c2")){
			String input = line.substring(2, line.length());
			try { 
		        int colNo = Integer.parseInt(input);
		        Color retCol = getColor(colNo);
		        if (retCol != null){
		        	controller.setColors(null, retCol);
		        	return true;
		        }
		    } catch(NumberFormatException e) {
		    	System.out.println("invalid input");
		        return true; 
		    }
		}
		// handles input 1-7
		if (line.matches("[1-7]")){
			Pattern p = Pattern.compile("[1-7]");
			Matcher m = p.matcher(line);
			m.find();
			int arg = Integer.parseInt(m.group());
			arg = arg - 1;
			int i = grid.getHeight(arg);
			if(i == -1){
				return true;
			}
			controller.setValue(grid.getCell(i, arg));
			System.out.println(grid.toString());
			System.out.println(grid.getStatus().getText());
			return true;
		}
		// in case there was no valid input
		System.out.println("invalid input");
		return true;
	}
	
	/**
	 * returns the Color for the given inputnumber
	 * ---java 1.6 cannot switch Strings---
	 * @param col
	 * @return
	 */
	private Color getColor(int col){
		switch(col){
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
	 * main;
	 * Enter "help" if you want to know how to use the program
	 * @param args
	 */
	public static void main(String[] args) {
		new ConnectFour().controlLoop();

	}

}
