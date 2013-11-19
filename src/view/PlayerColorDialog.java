package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The PlayerColorDialog is for changing colors of the players.
 * 
 * @author stgebhar
 * 
 */
public class PlayerColorDialog extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private static final int TEN = 10;
    private static final int FIVE = 5;
    private JComboBox<?> playerOneBox;
    private JComboBox<?> playerTwoBox;
    private JButton saveButton = new JButton("Save");
    private JButton quitButton = new JButton("Cancel");
    private JLabel label1;
    private JLabel label2;
    private Color playerOne = Color.RED;
    private Color playerTwo = Color.YELLOW;
    private Color[] color = new Color[] { Color.BLACK, Color.LIGHT_GRAY,
            Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE,
            Color.PINK };

    /**
     * builds the frame for colo-dialog
     * 
     * @param f
     *            : Frame
     */
    public PlayerColorDialog(JFrame f) {
        super(f, "Playercolors", true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        saveButton.addActionListener(this);
        quitButton.addActionListener(this);
        String[] colorStrings = { "Black", "Gray", "Red", "Orange", "Yellow",
                "Green", "Blue", "Pink" };
        playerOneBox = new JComboBox<Object>(colorStrings);
        playerTwoBox = new JComboBox<Object>(colorStrings);
        JPanel panelN = new JPanel(new GridLayout(0, 2, FIVE, FIVE));
        label1 = new JLabel("Player 1", JLabel.RIGHT);
        panelN.add(label1);
        panelN.add(playerOneBox);
        label2 = new JLabel("Player 2", JLabel.RIGHT);
        panelN.add(label2);
        panelN.add(playerTwoBox);
        panelN.setBorder(BorderFactory.createEmptyBorder(TEN, TEN, TEN, TEN));
        add(panelN, BorderLayout.CENTER);
        JPanel panelS = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelS.add(saveButton);
        panelS.add(quitButton);
        add(panelS, BorderLayout.SOUTH);
        pack();
    }

    /**
     * Show the select-color dialog. Colors of players can be changed in this
     * dialog
     * 
     * @param p1
     *            : current color of player 1
     * @param p2
     *            : current color of player 2
     * @param name1
     *            : name of player 1
     * @param name2
     *            : name of player 2
     */
    public void showDialog(Color p1, Color p2, String name1, String name2) {
        playerOneBox.setSelectedIndex(Arrays.asList(color).indexOf(p1));
        playerTwoBox.setSelectedIndex(Arrays.asList(color).indexOf(p2));
        playerOne = p1;
        playerTwo = p2;
        label1.setText(name1);
        label2.setText(name2);
        setVisible(true);
    }

    /**
     * Action Listener Save-button: Sets the selected Colors Cancel-button:
     * Drops the changes
     */
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(saveButton)) {
            int ret1 = playerOneBox.getSelectedIndex();
            playerOne = color[ret1];
            int ret2 = playerTwoBox.getSelectedIndex();
            playerTwo = color[ret2];

        }
        setVisible(false);
    }

    /**
     * Returns the Color of Player2
     * 
     * @return Color Player 2
     */
    public Color getP2Color() {
        return playerTwo;
    }

    /**
     * Returns the Color of Player1
     * 
     * @return Color Player 1
     */
    public Color getP1Color() {
        return playerOne;
    }
}
