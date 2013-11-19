package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;

import model.Cell;
import model.Player;

public class CellPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private Cell cell;
    private Player player;
    private static final int INSET = 3;
    private static final int THREE = 3;
    private static final int FOUR = 4;

    /**
     * constructor
     * 
     * @param cell
     */
    public CellPanel(Cell cell, Player player) {
        this.cell = cell;
        this.player = player;

        setLayout(new GridLayout(1, 1));

    }

    /**
     * repaint for Cells
     */
    @Override
    public void paintComponent(Graphics g) {

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Für optisch krasse Ränder
        g.draw3DRect(INSET, INSET, getWidth() - INSET * 2, getHeight() - INSET
                * 2, true);
        g.draw3DRect(INSET + 1, INSET + 1, getWidth() - INSET * 2 - 2,
                getHeight() - THREE * 2 - 2, true);

        g.setColor(Color.WHITE);
        g.fillRect(INSET + 2, INSET + 2, getWidth() - INSET * 2 - FOUR,
                getHeight() - INSET * 2 - FOUR);

        if (cell.isSet()) {
            if (cell.getValue() == 1) {
                paintValue(g, player.getPlayerOneColor());
            } else if (cell.getValue() == 2) {
                paintValue(g, player.getPlayerTwoColor());
            }
        }
    }

    /**
     * Zeichnet Kreise in der entsprechenden Farbe an der entsprechenden
     * Position
     * 
     * @param g
     * @param width
     * @param height
     * @param c
     */
    private void paintValue(Graphics g, Color c) {

        g.setColor(c);
        g.fillOval(INSET + 2, INSET + 2, getWidth() - INSET * 2 - FOUR,
                getHeight() - INSET * 2 - FOUR);
    }

}
