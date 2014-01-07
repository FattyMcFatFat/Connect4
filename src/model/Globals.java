package model;

/**
 * Gloabsl
 * @author stgebhar
 * At the moment there is no resizing, and i dont think there will ever be
 */
public class Globals {

	private int ROW_SIZE;
	private int COL_SIZE;
	private int MAX_TURNS;
	private int NEW_ROW_SIZE;
	private int NEW_COL_SIZE;
	
	/**
	 * Constructor
	 * @param row row
	 * @param col col
	 */
	public Globals(int row, int col){
		ROW_SIZE = row;
		COL_SIZE = col;
		MAX_TURNS = row * col;
		NEW_COL_SIZE = col;
		NEW_ROW_SIZE = row;
	}
	
	/**
	 * sets the new gridsizes
	 * @param row
	 * @param col
	 */
	public void resizeGrid(int row, int col){
		NEW_ROW_SIZE = row;
		NEW_COL_SIZE = col;
	}

	/**
	 * getter
	 * @return row
	 */
	public int getRowSize() {
		return ROW_SIZE;
	}

	/**
	 * getter
	 * @return new row
	 */
	public int getNewRowSize() {
		return NEW_ROW_SIZE;
	}
	
	/**
	 * getter
	 * @return col
	 */
	public int getColSize() {
		return COL_SIZE;
	}
	
	/**
	 * getter
	 * @return new col
	 */
	public int getNewColSize() {
		return NEW_COL_SIZE;
	}
	
	/**
	 * getter
	 * @return maxTurns (col * row)
	 */
	public int getMaxTurns() {
		return MAX_TURNS;
	}
}
