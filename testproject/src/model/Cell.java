package model;

public class Cell {

    private int value = 0;

    /**
     * sets the value v for the cell
     * 
     * @param v
     */
    public void setValue(int v) {
        value = v;
    }

    /**
     * returns the value of the cell
     * 
     * @return
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns true if the cell is occupied, fasle if not
     * 
     * @return
     */
    public boolean isSet() {
        return value == 0 ? false : true;
    }

    /**
     * returns a String of the form "1"
     */
    public String toString() {
        return "" + value;
    }

    /**
     * returns empty string if value is zero
     * 
     * @return
     */
    public String toStringNoZero() {
        if (value == 0) {
            return " ";
        } else {
            return "" + value;
        }
    }
}
