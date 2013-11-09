package model;

public class Grid {
    private Cell[][] cells;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private Status status = new Status();

    /**
     * constructor
     */
    public Grid() {
        cells = new Cell[ROWS][COLUMNS];

        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                cells[row][column] = new Cell();
            }
        }
    }

    /**
     * TODO: currently unsued
     * 
     * @param row
     * @param column
     * @param value
     */
    public void setCell(int row, int column, int value) {
        cells[row][column].setValue(value);
    }

    /**
     * returns a cell
     * 
     * @param row
     * @param column
     * @return
     */
    public Cell getCell(int row, int column) {
        return cells[row][column];
    }

    /**
     * returns the status
     * 
     * @return
     */
    public Status getStatus() {
        return status;
    }

    /**
     * returns the Height of a column or -1 if value to high
     * 
     * @param col
     * @return
     */
    public int getHeight(int col) {
        int i = 0;
        for (i = 0; i < ROWS; i++) {
            if (cells[i][col].getValue() == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * resets the grid
     */
    public void reset() {
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                cells[row][column].setValue(0);
            }
        }
        status.setText("The grid was reseted");
    }

    /**
     * returns the grid as string
     */
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("|---+---+---+---+---+---+---|\n");
        for (int row = FIVE; row >= 0; row--) {
            result.append("| ");
            for (int column = 0; column < COLUMNS; column++) {
                result.append(cells[row][column].toStringNoZero());
                result.append(" ");
                result.append("|");
                result.append(" ");
            }
            result.append("\n");
            result.append("|---+---+---+---+---+---+---|");
            result.append("\n");

        }
        return result.toString();
    }

    /**
     * returns only the cellvalues as String Used for savegames
     * 
     * @return
     */
    public String vaulesToString() {
        StringBuffer result = new StringBuffer();
        for (int row = FIVE; row >= 0; row--) {
            for (int column = 0; column < COLUMNS; column++) {
                result.append(cells[row][column].toString());
            }
            result.append("\n");

        }
        return result.toString();
    }

    /**
     * checks if a player has won the game
     * 
     * @return true if a player has won the game
     */
    public boolean winCheck() {

        if (wincheck1()) {
            return true;
        }
        if (wincheck2()) {
            return true;
        }
        if (wincheck3()) {
            return true;
        }
        if (wincheck4()) {
            return true;
        }
        return false;
    }

    /**
     * wincheck1: Checks for a winner across
     * @return true if player has won
     */
    private boolean wincheck1() {
        for (int column = 0; column < FOUR; column++) {
            for (int row = 0; row < ROWS; row++) {
                if (cells[row][column].getValue() != 0
                        && cells[row][column].getValue() == cells[row][column + 1]
                                .getValue()
                        && cells[row][column].getValue() == cells[row][column + 2]
                                .getValue()
                        && cells[row][column].getValue() == cells[row][column
                                + THREE].getValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * wincheck2: Checks for a winner vertical
     * @return true if player has won
     */
    private boolean wincheck2() {
        for (int column = 0; column < COLUMNS; column++) {
            for (int row = 0; row < THREE; row++) {
                if (cells[row][column].getValue() != 0
                        && cells[row][column].getValue() == cells[row + 1][column]
                                .getValue()
                        && cells[row][column].getValue() == cells[row + 2][column]
                                .getValue()
                        && cells[row][column].getValue() == cells[row + THREE][column]
                                .getValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * wincheck3: Checks for a winner diagonal
     * @return true if player has won
     */
    private boolean wincheck3() {
        for (int column = 0; column < FOUR; column++) {
            for (int row = 0; row < THREE; row++) {
                if (cells[row][column].getValue() != 0
                        && cells[row][column].getValue() == cells[row + 1][column + 1]
                                .getValue()
                        && cells[row][column].getValue() == cells[row + 2][column + 2]
                                .getValue()
                        && cells[row][column].getValue() == cells[row + THREE][column
                                + THREE].getValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * wincheck4: Checks for a winner diagonal2
     * @return true if player has won
     */
    private boolean wincheck4() {
        for (int column = THREE; column < COLUMNS; column++) {
            for (int row = 0; row < THREE; row++) {
                if (cells[row][column].getValue() != 0
                        && cells[row][column].getValue() == cells[row + 1][column - 1]
                                .getValue()
                        && cells[row][column].getValue() == cells[row + 2][column - 2]
                                .getValue()
                        && cells[row][column].getValue() == cells[row + THREE][column
                                - THREE].getValue()) {
                    return true;
                }
            }
        }
        return false;
    }
}
