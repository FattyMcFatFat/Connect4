package model;

/**
 * Class grid is the grid
 * @author stgebhar
 */
public class Grid {
    private Cell[][] cells;
    private static final int THREE = 3;
    private Status status = new Status();
    private Globals gv;
    private boolean isTestGrid;

    /**
     * constructor
     * @param isTest true is UnitTestCase, else false
     */
    public Grid(boolean isTest, Globals globs) {
    	
    	this.gv = globs;
    	isTestGrid = isTest;
        cells = new Cell[gv.getRowSize()][gv.getColSize()];

        for (int row = 0; row < gv.getRowSize(); row++) {
            for (int column = 0; column < gv.getColSize(); column++) {
                cells[row][column] = new Cell();
            }
        }
    }

    /**
     *  sets a value for a cell
     * @param row row
     * @param column column
     * @param value value
     */
    public void setCell(int row, int column, int value) {
        cells[row][column].setValue(value);
    }

    /**
     * returns the cell at this position
     * @param row row
     * @param column columns
     * @return the Cell
     */
    public Cell getCell(int row, int column) {
        return cells[row][column];
    }

    /**
     * returns the current status
     * 
     * @return status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * returns the height of a column or -1 if value to high
     * @param col column
     * @return the height of the column
     */
    public int getHeight(int col) {
        int i = 0;
        for (i = 0; i < gv.getRowSize(); i++) {
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
        for (int row = 0; row < gv.getRowSize(); row++) {
            for (int column = 0; column < gv.getColSize(); column++) {
                cells[row][column].setValue(0);
            }
        }
        status.setText("The grid was reseted");
    }

    /**
     * returns the grid as string
     */
    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("+");
        for (int i = 0; i < gv.getColSize(); i++) {
			result.append("---+");
		}
        result.append("\n");
        for (int row = (gv.getRowSize()-1); row >= 0; row--) {
            result.append("| ");
            for (int column = 0; column < gv.getColSize(); column++) {
                result.append(cells[row][column].toStringNoZero());
                result.append(" ");
                result.append("|");
                result.append(" ");
            }
            result.append("\n");
            result.append("+");
            for (int i = 0; i < gv.getColSize(); i++) {
    			result.append("---+");
    		}
            result.append("\n");

        }
        return result.toString();
    }

    /**
     * returns only the cellvalues as String Used for savegames
     * @return the grid without whitespace
     */
    public String vaulesToString() {
        StringBuffer result = new StringBuffer();
        for (int row = (gv.getRowSize()-1); row >= 0; row--) {
            for (int column = 0; column < gv.getColSize(); column++) {
                result.append(cells[row][column].toString());
            }
            result.append("\n");

        }
        return result.toString();
    }

    /**
     * checks if a player has won the game
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
        for (int column = 0; column < (gv.getColSize()-THREE); column++) {
            for (int row = 0; row < gv.getRowSize(); row++) {
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
        for (int column = 0; column < gv.getColSize(); column++) {
            for (int row = 0; row < (gv.getRowSize()-THREE); row++) {
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
        for (int column = 0; column < (gv.getColSize()-THREE); column++) {
            for (int row = 0; row < (gv.getRowSize()-THREE); row++) {
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
        for (int column = THREE; column < gv.getColSize(); column++) {
            for (int row = 0; row < (gv.getRowSize()-THREE); row++) {
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
    
    /**
     * checks if lauched by UnitTests
     * @return true if UnitTestCase; else false
     */
    public boolean getIsTest(){
    	return isTestGrid;
    }
}
