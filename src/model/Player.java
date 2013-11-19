package model;

import java.awt.Color;

public class Player {
    private int player;
    private String playerOne;
    private String playerTwo;
    private Color playerOneColor;
    private Color playerTwoColor;
    private String winner;
    private boolean hasWon;
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
     * 
     * @return
     */
    public int getPlayer() {
        return player;
    }

    /**
     * return the waiting player
     * 
     * @return
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
     * 
     * @return
     */
    public String getPlayerOneName() {
        return playerOne;
    }

    /**
     * return name of player2
     * 
     * @return
     */
    public String getPlayerTwoName() {
        return playerTwo;
    }

    /**
     * returns the name of the current player
     * 
     * @return
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
     * 
     * @return
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
     * 
     * @param p1
     * @param p2
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
     * 
     * @param p1
     * @param p2
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
     * 
     * @return
     */
    public Color getPlayerOneColor() {
        return playerOneColor;
    }

    /**
     * return the current color of player two
     * 
     * @return
     */
    public Color getPlayerTwoColor() {
        return playerTwoColor;
    }

    /**
     * sets hasWon = true if someone won the game
     * 
     * @param s
     */
    public void setWon(String s) {
        winner = s;
        hasWon = true;
    }

    /**
     * returns if someone has won the game
     * 
     * @return
     */
    public boolean hasWon() {
        return hasWon;
    }

    /**
     * returns the winner
     * 
     * @return
     */
    public String getWinner() {
        return winner;
    }

    /**
     * retets the Object
     */
    public void resetPlayer() {
        hasWon = false;
        winner = "";
    }
}
