package model;

public class Globals {
	public int ROW_SIZE;
	public int COL_SIZE;
	public int MAX_TURNS;
	public int NEW_ROW_SIZE;
	public int NEW_COL_SIZE;
	
	public Globals(int row, int col){
		ROW_SIZE = row;
		COL_SIZE = col;
		NEW_COL_SIZE = col;
		NEW_ROW_SIZE = row;
		MAX_TURNS = row * col;
	}
	
	public void resizeGrid(int row, int col){
		NEW_ROW_SIZE = row;
		NEW_COL_SIZE = col;
	}
}
