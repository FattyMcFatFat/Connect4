package model;

import java.awt.Color;

/**
 * Class player has the settings for the two players
 * @author stgebhar
 */
public class Player {
    private int player;
    private String playerOne;
    private String playerTwo;
    private Color playerOneColor;
    private Color playerTwoColor;
    private String winner;
    private boolean hasWon;
    private boolean winnerMessageHasBeenShown = false;
    private static final int PLAYER_ONE = 1;
    private static final int PLAYER_TWO = 2;

    /**
     * constructor sets the default playernames and colors
     */
    public Player() {
        this.player = PLAYER_ONE;
        this.playerOne = "Player 1";
        this.playerTwo = "Player 2";
        this.playerOneColor = Color.RED;
        this.playerTwoColor = Color.YELLOW;
        this.hasWon = false;

    }

    /**
     * changes the player
     */
    public void changePlayer() {
        if (player == PLAYER_ONE) {
            player = PLAYER_TWO;
        } else {
            player = PLAYER_ONE;
        }
    }

    /**
     * returns the current player
     * @return 1 or 2
     */
    public int getPlayer() {
        return player;
    }

    /**
     * return the waiting player
     * @return 1 or 2
     */
    public int getOtherPlayer() {
        if (player == PLAYER_ONE) {
            return PLAYER_TWO;
        } else {
            return PLAYER_ONE;
        }
    }

    /**
     * return name of palyer1
     * @return player1 name
     */
    public String getPlayerOneName() {
        return playerOne;
    }

    /**
     * return name of player2
     * @return player2 name
     */
    public String getPlayerTwoName() {
        return playerTwo;
    }

    /**
     * returns the name of the current player
     * @return name of current player
     */
    public String getPlayerName() {
        if (player == PLAYER_ONE) {
            return playerOne;
        } else {
            return playerTwo;
        }
    }

    /**
     * returns the name of the wating player
     * @return name of waiting player
     */
    public String getOtherPlayerName() {
        if (player == PLAYER_ONE) {
            return playerTwo;
        } else {
            return playerOne;
        }
    }

    /**
     * sets the parameters as playernames
     * @param p1 new name player1
     * @param p2 new name player2
     */
    public void setPlayerNames(String p1, String p2) {
        if (p1.equals("")) {
            playerTwo = p2;
        } else if (p2.equals("")) {
            playerOne = p1;
        } else {
            playerOne = p1;
            playerTwo = p2;
        }
    }

    /**
     * sets the Colors for the players
     * @param p1 new color player1
     * @param p2 new color player2
     */
    public void setColors(Color p1, Color p2) {
        if (p1 == null) {
            playerTwoColor = p2;
        } else if (p2 == null) {
            playerOneColor = p1;
        } else {
            playerOneColor = p1;
            playerTwoColor = p2;
        }
    }

    /**
     * returns the current color of player one
     * @return color player1
     */
    public Color getPlayerOneColor() {
        return playerOneColor;
    }

    /**
     * return the current color of player two
     * @return color player2
     */
    public Color getPlayerTwoColor() {
        return playerTwoColor;
    }

    /**
     * sets hasWon = true if someone won the game
     * @param s player who won the game
     */
    public void setWon(String s) {
        winner = s;
        hasWon = true;
    }

    /**
     * returns if someone has won the game
     * @return true if a player has won
     */
    public boolean hasWon() {
        return hasWon;
    }

    /**
     * returns the winner
     * @return the name of the winner
     */
    public String getWinner() {
        return winner;
    }

    /**
     * retets the Object Player
     */
    public void resetPlayer() {
        hasWon = false;
        winner = "";
    }
    
    /**
     * getter for winnerMessageHasBeenShown
     * @return true if message has already been displayed once
     */
    public boolean getWinnerMessageHasBeenShown(){
    	return winnerMessageHasBeenShown;
    }
    
    /**
     * Message should only be displayed once
     * If shown this is set true
     */
    public void setWinnerMessageHasBeenShown(boolean bool){
    	winnerMessageHasBeenShown = bool;
    }
}
