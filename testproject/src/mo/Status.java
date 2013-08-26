package mo;

import java.io.Serializable;

public class Status implements Serializable {

	private static final long serialVersionUID = 1L;
	private String text = "Vier Gewinnt Beta";

	/**
	 * sets a text
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * returns the text
	 * @return
	 */
	public String getText() {
		return text;
	}
}

