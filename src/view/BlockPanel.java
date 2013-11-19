package view;

import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * Class BlockPanel
 * @author stgebhar
 *
 */
public class BlockPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * sets a gridlayout with the blocksize
     * @param blockSize size
     */
    public BlockPanel(int blockSize) {
        setLayout(new GridLayout(blockSize, blockSize, 1, 1));
    }

}
