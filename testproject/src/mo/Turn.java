package mo;

public class Turn {
	private int turn;
	
	/**
	 * constructor
	 */
	public Turn(){
		this.turn = 0;
	}
	
	/**
	 * returns the turncount
	 * @return
	 */
	public int getTurn(){
		return turn;
	}
	
	/**
	 * increments the turncount
	 */
	public void incTurn(){
		turn++;
	}
	
	/**
	 * resets the counter in case of a new game
	 */
	public void resetTurn(){
		turn = 0;
	}

}
