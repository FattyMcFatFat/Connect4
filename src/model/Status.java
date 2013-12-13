package model;

import java.io.Serializable;

/**
 * Class status
 * @author stgebhar
 */
public class Status implements Serializable {

    private static final long serialVersionUID = 1L;
    private String text = " ";

    /**
     * sets a text
     * @param text text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * returns the text
     * @return the text
     */
    public String getText() {
        return text;
    }
}
