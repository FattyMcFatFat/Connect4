package model;

/**
 * Gloabsl
 * @author stgebhar
 * At the moment there is no resizing, and i dont think there will ever be
 * edit: OHHHHH MAYBE ITS COMING SOON
 */
public class Globals {

	private int rowSize;
	private int colSize;
	private int maxTurns;
	private int newRowSize;
	private int newColSize;
	
	/**
	 * Constructor
	 * @param row row
	 * @param col col
	 */
	public Globals(int row, int col){
		rowSize = row;
		colSize = col;
		maxTurns = row * col;
		newRowSize = row;
		newColSize = col;
	}
	
	/**
	 * sets the new gridsizes
	 * @param row
	 * @param col
	 */
	public void resizeGrid(int row, int col){
		newRowSize = row;
		newColSize = col;
	}

	/**
	 * getter
	 * @return row
	 */
	public int getRowSize() {
		return rowSize;
	}

	/**
	 * getter
	 * @return new row
	 */
	public int getNewRowSize() {
		return newRowSize;
	}
	
	/**
	 * getter
	 * @return col
	 */
	public int getColSize() {
		return colSize;
	}
	
	/**
	 * getter
	 * @return new col
	 */
	public int getNewColSize() {
		return newColSize;
	}
	
	/**
	 * getter
	 * @return maxTurns (col * row)
	 */
	public int getMaxTurns() {
		return maxTurns;
	}
}
