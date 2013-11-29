package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import controller.Controller;
import model.Globals;
import model.Grid;
import model.Player;

/**
 * class gridpanel
 * @author stgebhar
 */
public class GridPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * constructor
     * @param grid grid
     * @param controller controller
     */
    public GridPanel(final Grid grid, final Controller controller,
            final Player player, final Globals gv) {
        setLayout(new GridLayout(gv.ROW_SIZE, gv.COL_SIZE, 2, 2));
        setBorder(BorderFactory.createLoweredBevelBorder());
        BlockPanel[] block = new BlockPanel[gv.MAX_TURNS];

        for (int index = 0; index < gv.MAX_TURNS; index++) {
            block[index] = new BlockPanel(1);
            add(block[index]);
        }
        int i = 0;
        for (int row = (gv.ROW_SIZE-1); row >= 0; row--) {
            for (int column = 0; column < gv.COL_SIZE; column++) {
                CellPanel cellPanel = new CellPanel(grid.getCell(row, column),
                        player);
                block[i].add(cellPanel);
                i++;
            }
        }

        /**
         * MouseListener for GUI
         */
        addMouseListener(new MouseAdapter() {
            /**
             * mouseListener
             */
            public void mouseClicked(MouseEvent e) {
                int clicked = e.getX();
                int width = (int) getSize().getWidth();
                int split = (int) (gv.COL_SIZE * ((double) clicked / (double) width));
                if (grid.getHeight(split) != -1) {
                    controller.setValue(grid.getCell(grid.getHeight(split),
                            split));
                }
            }
        });
    }

    /**
     * repaint for grid
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, getSize().width, getSize().height);
    }

}
