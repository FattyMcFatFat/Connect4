package model;

/**
 * Class Turn storages the turns
 * @author stgebhar
 */
public class Turn {
    private int turn;

    /**
     * constructor
     */
    public Turn() {
        this.turn = 0;
    }

    /**
     * returns the turncounter
     * @return current turncounter
     */
    public int getTurn() {
        return turn;
    }

    /**
     * increments the turncounter
     */
    public void incTurn() {
        turn++;
    }

    /**
     * resets the counter in case of a new game
     */
    public void resetTurn() {
        turn = 0;
    }
}
