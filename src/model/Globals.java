package model;

/**
 * Gloabsl
 * @author stgebhar
 * At the moment there is no resizing, and i dont think there will ever be
 */
public class Globals {
	public int ROW_SIZE;
	public int COL_SIZE;
	public int MAX_TURNS;
	public int NEW_ROW_SIZE;
	public int NEW_COL_SIZE;
	
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

}
