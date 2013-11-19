package model;

/**
 * Class cell are the cells of the grid
 * @author stoff
 */
public class Cell {

    private int value = 0;

    /**
     * sets the value v for the cell
     * @param v value
     */
    public void setValue(int v) {
        value = v;
    }

    /**
     * returns the value of the cell
     * @return value of the cell
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns true if the cell is occupied, fasle if not
     * @return if cell is set
     */
    public boolean isSet() {
        return value == 0 ? false : true;
    }

    /**
     * returns the value as String
     */
    public String toString() {
        return "" + value;
    }

    /**
     * returns empty string if value is zero
     * or the value of the cell if value is != 0
     * @return empty String if value is zero
     */
    public String toStringNoZero() {
        if (value == 0) {
            return " ";
        } else {
            return "" + value;
        }
    }
}
