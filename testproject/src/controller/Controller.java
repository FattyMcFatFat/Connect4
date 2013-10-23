package controller;

import java.awt.Color;

import observer.Observable;
import model.Cell;
import model.Grid;
import model.Player;
import model.Status;
import model.Turn;

public class Controller extends Observable {

	private Grid grid;
	private Status status;
	private Player player;
	private Turn turn;
	private static final int MAX_TURNS = 42;

	/**
	 * Constructor
	 * @param grid: Grid
	 * @param player: Player
	 */
	public Controller(Grid grid, Player player) {
		this.grid = grid;
		this.status = grid.getStatus();
		this.player = player;
		this.turn = new Turn();

	}

	/**
	 * generates new game:
	 * -clears cells
	 * -set player to player1
	 * -reset turn counter
	 */
	public void newGrid() {
		grid.reset();
		if (player.getPlayer() != 1) {
			player.changePlayer();
		}
		player.resetPlayer();
		turn.resetTurn();
		status.setText("A new Game was created");
		System.out.println(grid.toString());
		System.out.println(grid.getStatus().getText());
		notifyObservers();
	}

	/**
	 * sets the current player as value for the cell
	 * set value 1 if current player is player 1, else 2
	 * 
	 * then checks if the grid is full or a player has won the game
	 * 
	 * @param cell: the cell to get filled
	 */
	public void setValue(Cell cell) {

		turn.incTurn();
		cell.setValue(player.getPlayer());
		System.out.println(grid.toString());
		grid.getStatus().setText(player.getOtherPlayerName() + "'s turn.");

		// checks if the grid is full
		if (turn.getTurn() == MAX_TURNS) {
			grid.getStatus().setText("It's a tie!");
		}

		// checks if someone won the game
		if (grid.winCheck()) {
			if (!player.hasWon()) {
				player.setWon(player.getPlayerName());
			}
			grid.getStatus().setText(player.getWinner() + " has won the game!");
		}
		System.out.println(grid.getStatus().getText());

		player.changePlayer();

		notifyObservers();
	}

	/**
	 * changes the payernames
	 * 
	 * @param p1: new name of player 1
	 * @param p2: new name of player 2
	 */
	public void setPlayernames(String p1, String p2) {
		player.setPlayerNames(p1, p2);
		notifyObservers();
	}

	// TODO: Grüße an Fabi entfernen
	/**
	 * sets the colors for the players
	 * prints an error message if colors are the same
	 * @param p1: new color of player 1
	 * @param p2: new color of player 2
	 */
	public void setColors(Color p1, Color p2) {
		if (p1 == p2){
			grid.getStatus().setText("NUR FÜR DICH FABI!");
			System.out.println(grid.getStatus().getText());
		} else {
			player.setColors(p1, p2);
		}
		notifyObservers();
	}

	/**
	 * Quits the game
	 */
	public void doExit() {
		grid.getStatus().setText("Game has been closed");
		System.out.println(grid.getStatus().getText());
		notifyObservers();

		System.exit(0);
	}
}
