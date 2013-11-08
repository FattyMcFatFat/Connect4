package model;


public class Grid {
	private Cell[][] cells;
	private static final int ROWS = 6;
	private static final int COLUMNS = 7;
	private Status status = new Status();
	

	/**
	 * constructor
	 */
	public Grid(){
		cells = new Cell[ROWS][COLUMNS];
		
		for (int row = 0; row < ROWS; row++) {
			for (int column = 0; column < COLUMNS; column++) {
				cells[row][column] = new Cell();
			}
		}
	}
	
	/**
	 * TODO: currently unsued
	 * @param row
	 * @param column
	 * @param value
	 */
	public void setCell(int row, int column, int value){
		cells[row][column].setValue(value);
	}

	/**
	 * returns a cell
	 * @param row
	 * @param column
	 * @return
	 */
	public Cell getCell(int row, int column) {
		return cells[row][column];
	}
	
	/**
	 * returns the status
	 * @return
	 */
	public Status getStatus() {
		return status;
	}
	
	/**
	 * returns the Height of a column or -1 if value to high
	 * @param col
	 * @return
	 */
	public int getHeight(int col){
		int i = 0;
		for (i = 0; i < ROWS; i++){
			if (cells[i][col].getValue() == 0){
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
		for (int row = 5; row >= 0; row--){
			result.append("| ");
			for (int column = 0; column < COLUMNS; column++){
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
	 * returns only the cellvalues as String
	 * Used for savegames
	 * @return
	 */
	public String vaulesToString() {
		StringBuffer result = new StringBuffer();
		for (int row = 5; row >= 0; row--){
			for (int column = 0; column < COLUMNS; column++){
				result.append(cells[row][column].toString());
			}
			result.append("\n");
			

		}
		return result.toString();
	}
	
	/**
	 * checks if a player has won the game
	 * @return
	 */
	public boolean winCheck() {

        for (int column = 0; column < 4; column++) {
            for (int row = 0; row < 6; row++) {
                if (cells[row][column].getValue() != 0 && cells[row][column].getValue() == cells[row][column+1].getValue() && cells[row][column].getValue() == cells[row][column+2].getValue() && cells[row][column].getValue() == cells[row][column+3].getValue()) {
                    return true;
                }
            }
        }
        for (int column = 0; column < 7; column++) {
            for (int row = 0; row < 3; row++) {
                if (cells[row][column].getValue() != 0 && cells[row][column].getValue() == cells[row+1][column].getValue() && cells[row][column].getValue() == cells[row+2][column].getValue() && cells[row][column].getValue() == cells[row+3][column].getValue()) {
                    return true;
                }
            }
        }
        for (int column = 0; column < 4; column++) {
            for (int row = 0; row < 3; row++) {
                if (cells[row][column].getValue() != 0 && cells[row][column].getValue() == cells[row+1][column+1].getValue() && cells[row][column].getValue() == cells[row+2][column+2].getValue() && cells[row][column].getValue() == cells[row+3][column+3].getValue()) {
                    return true;
                }
            }
        }
        for (int column = 3; column < 7; column++) {
            for (int row = 0; row < 3; row++) {
                if (cells[row][column].getValue() != 0 && cells[row][column].getValue() == cells[row+1][column-1].getValue() && cells[row][column].getValue() == cells[row+2][column-2].getValue() && cells[row][column].getValue() == cells[row+3][column-3].getValue()) {
                    return true;
                }
            }
        }
        return false;
	}
}
