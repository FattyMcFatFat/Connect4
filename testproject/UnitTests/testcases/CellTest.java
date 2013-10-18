package testcases;

import model.Cell;
import junit.framework.TestCase;

public class CellTest extends TestCase {
	private Cell cell;
	
	@Override
	protected void setUp(){
		cell = new Cell();
	}
	
	public void testGetValue() {
		cell.setValue(1);
		assertEquals(1, cell.getValue());
		cell.setValue(2);
		assertEquals(2, cell.getValue());
	}
	
	public void testIsSet() {
		assertEquals(false, cell.isSet());
		cell.setValue(1);
		assertEquals(true, cell.isSet());
		cell.setValue(0);
		assertEquals(false, cell.isSet());
	}
	
	public void testToString() {
		cell.setValue(1);
		assertEquals("1",cell.toString());
	}
	
	public void testToStringNoZero() {
		assertEquals(" ",cell.toStringNoZero());
		cell.setValue(1);
		assertEquals("1",cell.toStringNoZero());
		cell.setValue(0);
		assertEquals(" ",cell.toStringNoZero());
	}

}
