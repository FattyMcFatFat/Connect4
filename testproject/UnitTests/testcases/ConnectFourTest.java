package testcases;

import connect4.ConnectFour;
import junit.framework.TestCase;

public class ConnectFourTest extends TestCase {

	private ConnectFour c4;

	public void setUp() {
		c4 = new ConnectFour();
	}
	
	public void testHandleInputQuit() {
		assertEquals(false, c4.handleInput("quit"));
	}
	
	public void testHandleInputExit() {
		assertEquals(false, c4.handleInput("exit"));
	}
	
	public void testHandleInputHelp() {
		assertEquals(true, c4.handleInput("help"));
	}
	
	public void testHandleInputNew() {
		assertEquals(true, c4.handleInput("new"));
		assertEquals(true, c4.handleInput("2"));
		assertEquals(true, c4.handleInput("new"));
	}

	public void testHandleInputOne() {
		assertEquals(true, c4.handleInput("1"));
	}
	
	public void testHandleInputZero() {
		assertEquals(true, c4.handleInput("0"));
	}
	
	public void testPlayername1(){
		assertEquals(true, c4.handleInput("n1blubb"));
	}
	
	public void testPlayername2(){
		assertEquals(true, c4.handleInput("n2blabla"));
	}
	
	public void testSetColor1(){
		assertEquals(true, c4.handleInput("c1blubb"));
		assertEquals(true, c4.handleInput("c11"));
		assertEquals(true, c4.handleInput("c12"));
		assertEquals(true, c4.handleInput("c13"));
		assertEquals(true, c4.handleInput("c14"));
		assertEquals(true, c4.handleInput("c15"));
		assertEquals(true, c4.handleInput("c16"));
		assertEquals(true, c4.handleInput("c17"));
		assertEquals(true, c4.handleInput("c18"));
		assertEquals(true, c4.handleInput("c10"));	
	}
	
	public void testSetColor2(){
		assertEquals(true, c4.handleInput("c2blubb"));
		assertEquals(true, c4.handleInput("c21"));
		assertEquals(true, c4.handleInput("c20"));
	}
	
	public void testHandleInputColumnOverflow() {
		assertEquals(true, c4.handleInput("1"));
		assertEquals(true, c4.handleInput("1"));
		assertEquals(true, c4.handleInput("1"));
		assertEquals(true, c4.handleInput("1"));
		assertEquals(true, c4.handleInput("1"));
		assertEquals(true, c4.handleInput("1"));
		assertEquals(true, c4.handleInput("1"));
	}
	
	public void testWin1(){	
		assertEquals(true, c4.handleInput("1"));
		assertEquals(true, c4.handleInput("1"));
		assertEquals(true, c4.handleInput("2"));
		assertEquals(true, c4.handleInput("2"));
		assertEquals(true, c4.handleInput("3"));
		assertEquals(true, c4.handleInput("3"));
		assertEquals(true, c4.handleInput("4"));		
	}
	
	public void testWin2(){	
		assertEquals(true, c4.handleInput("1"));
		assertEquals(true, c4.handleInput("2"));
		assertEquals(true, c4.handleInput("1"));
		assertEquals(true, c4.handleInput("2"));
		assertEquals(true, c4.handleInput("1"));
		assertEquals(true, c4.handleInput("2"));
		assertEquals(true, c4.handleInput("1"));		
	}
	
	public void testWin3(){
		assertEquals(true, c4.handleInput("1"));
		assertEquals(true, c4.handleInput("2"));
		assertEquals(true, c4.handleInput("4"));
		assertEquals(true, c4.handleInput("3"));
		assertEquals(true, c4.handleInput("3"));
		assertEquals(true, c4.handleInput("2"));
		assertEquals(true, c4.handleInput("2"));
		assertEquals(true, c4.handleInput("1"));
		assertEquals(true, c4.handleInput("7"));
		assertEquals(true, c4.handleInput("1"));
		assertEquals(true, c4.handleInput("1"));
		
	}
	
	public void testWin4(){
		assertEquals(true, c4.handleInput("1"));
		assertEquals(true, c4.handleInput("2"));
		assertEquals(true, c4.handleInput("2"));
		assertEquals(true, c4.handleInput("3"));
		assertEquals(true, c4.handleInput("4"));
		assertEquals(true, c4.handleInput("3"));
		assertEquals(true, c4.handleInput("3"));
		assertEquals(true, c4.handleInput("4"));
		assertEquals(true, c4.handleInput("7"));
		assertEquals(true, c4.handleInput("4"));
		assertEquals(true, c4.handleInput("4"));
		assertEquals(true, c4.handleInput("7"));
		assertEquals(true, c4.handleInput("7"));
		assertEquals(true, c4.handleInput("7"));
		assertEquals(true, c4.handleInput("7"));
		assertEquals(true, c4.handleInput("7"));
		assertEquals(true, c4.handleInput("6"));
		assertEquals(true, c4.handleInput("6"));
		assertEquals(true, c4.handleInput("6"));
		assertEquals(true, c4.handleInput("6"));
		assertEquals(true, c4.handleInput("6"));
		assertEquals(true, c4.handleInput("6"));
		assertEquals(true, c4.handleInput("5"));
		assertEquals(true, c4.handleInput("5"));
		assertEquals(true, c4.handleInput("5"));
		assertEquals(true, c4.handleInput("5"));
		assertEquals(true, c4.handleInput("5"));
		assertEquals(true, c4.handleInput("5"));
		assertEquals(true, c4.handleInput("4"));
		assertEquals(true, c4.handleInput("4"));
		assertEquals(true, c4.handleInput("3"));
		assertEquals(true, c4.handleInput("3"));
		assertEquals(true, c4.handleInput("3"));
		assertEquals(true, c4.handleInput("2"));
		assertEquals(true, c4.handleInput("2"));
		assertEquals(true, c4.handleInput("2"));
		assertEquals(true, c4.handleInput("2"));
		assertEquals(true, c4.handleInput("1"));
		assertEquals(true, c4.handleInput("1"));
		assertEquals(true, c4.handleInput("1"));
		assertEquals(true, c4.handleInput("1"));
		assertEquals(true, c4.handleInput("1"));
	
	}
}
