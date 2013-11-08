package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PlayerNamesDialog extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private static final int FIVE = 5;
    private static final int TEN = 10;
    private JTextField playerOneField = new JTextField(TEN);
    private JTextField playerTwoField = new JTextField(TEN);
    private JButton saveButton = new JButton("Save");
    private JButton quitButton = new JButton("Cancel");
    private String playerOne = "";
    private String playerTwo = "";

    public PlayerNamesDialog(JFrame f) {
        super(f, "Playernames", true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        saveButton.addActionListener(this);
        quitButton.addActionListener(this);
        JPanel panelN = new JPanel(new GridLayout(0, 2, FIVE, FIVE));
        panelN.add(new JLabel("Player 1", JLabel.RIGHT));
        panelN.add(playerOneField);
        panelN.add(new JLabel("Player 2", JLabel.RIGHT));
        panelN.add(playerTwoField);
        panelN.setBorder(BorderFactory.createEmptyBorder(TEN, TEN, TEN, TEN));
        add(panelN, BorderLayout.CENTER);
        JPanel panelS = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelS.add(saveButton);
        panelS.add(quitButton);
        add(panelS, BorderLayout.SOUTH);
        pack();
    }

    public void showDialog(String p1, String p2) {
        playerOneField.setText(p1);
        playerTwoField.setText(p2);
        playerOne = p1;
        playerTwo = p2;
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(saveButton)) {
            playerOne = playerOneField.getText();
            playerTwo = playerTwoField.getText();
        }
        setVisible(false);
    }

    public String getLastName() {
        return playerTwo;
    }

    public String getFirstName() {
        return playerOne;
    }
}
