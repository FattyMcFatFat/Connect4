package view;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 * class statuspanel
 * @author stgebhar
 */
public class StatusPanel extends JPanel {

    private final JLabel statusLabel = new JLabel("");
    private static final long serialVersionUID = 1L;

    /**
     * constructor
     */
    public StatusPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        add(statusLabel);
    }

    /**
     * sets a text to panel
     * @param text text
     */
    public final void setText(final String text) {
        statusLabel.setText(" " + text);
    }
}
