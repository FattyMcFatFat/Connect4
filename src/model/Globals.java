package model;

public class Globals {
	public final int ROW_SIZE;
	public final int COL_SIZE;
	public final int MAX_TURNS;
	public int NEW_ROW_SIZE;
	public int NEW_COL_SIZE;
	
	public Globals(int row, int col){
		ROW_SIZE = row;
		COL_SIZE = col;
		MAX_TURNS = row * col;
		NEW_COL_SIZE = col;
		NEW_ROW_SIZE = row;
	}
	
	public void resizeGrid(int row, int col){
		NEW_ROW_SIZE = row;
		NEW_COL_SIZE = col;
	}
}
