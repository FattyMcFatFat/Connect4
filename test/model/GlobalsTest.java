package model;

import junit.framework.TestCase;

public class GlobalsTest extends TestCase {
	private Globals globals;
	private static final int COL_VALUE = 7;
	private static final int ROW_VALUE = 6;
	
	public GlobalsTest(){
		globals = new Globals(ROW_VALUE, COL_VALUE);
	}
	
	public void testGetNewColSize(){
		assertEquals( COL_VALUE, globals.getNewColSize());
	}
	
	public void testGetNewRowSize(){
		assertEquals( ROW_VALUE, globals.getNewRowSize());
	} 
	
	public void testGetColSize(){
		assertEquals( COL_VALUE, globals.getColSize());
	}
	
	public void testGetRowSize(){
		assertEquals( ROW_VALUE, globals.getRowSize());
	}
	
	public void testResizeGrid(){
		globals.resizeGrid(4, 4);
		assertEquals( 4, globals.getNewColSize());
		assertEquals( 4, globals.getNewRowSize());
	}
}
