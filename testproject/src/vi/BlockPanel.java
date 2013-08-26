package vi;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class BlockPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param blockSize
	 */
	public BlockPanel(int blockSize) {
		setLayout(new GridLayout(blockSize, blockSize, 1, 1));
	}

}
