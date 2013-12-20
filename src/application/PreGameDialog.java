package application;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PreGameDialog extends JDialog implements ActionListener{
	
	private static final long serialVersionUID = 1L;
    private static final int TEN = 10;
    private static final int FIVE = 5;
    private JButton goButton = new JButton("GOOOOOOO");
    private JLabel label1;
    private JLabel label2;
    private int selectedColSize;
    private int selectedRowSize;
    private int[] availableSizes = new int[] { 4, 5, 6, 7, 8, 9 };
    private String[] availableSizesAsStrings = new String[] {"4", "5", "6", "7", "8", "9" };
    
    // Supress Warnings because Maven gets problems otherwise
    @SuppressWarnings("rawtypes")
   	private JComboBox colBox;
    @SuppressWarnings("rawtypes")
   	private JComboBox rowBox;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public PreGameDialog(JFrame frame) {
		super(frame, "Set Grid Size", true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        goButton.addActionListener(this);
        colBox = new JComboBox(availableSizesAsStrings);
        rowBox = new JComboBox(availableSizesAsStrings);
        JPanel panelN = new JPanel(new GridLayout(0, 2, FIVE, FIVE));
        label1 = new JLabel("Player 1", JLabel.RIGHT);
        panelN.add(label1);
        panelN.add(colBox);
        label2 = new JLabel("Player 2", JLabel.RIGHT);
        panelN.add(label2);
        panelN.add(rowBox);
        panelN.setBorder(BorderFactory.createEmptyBorder(TEN, TEN, TEN, TEN));
        add(panelN, BorderLayout.CENTER);
        JPanel panelS = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelS.add(goButton);
        add(panelS, BorderLayout.SOUTH);
        pack();
	}
	
	public void showDialog() {
		// Sehr Fehleranfaellig, aber tut :D
		colBox.setSelectedIndex(3);
		rowBox.setSelectedIndex(2);
		selectedColSize = 7;
		selectedRowSize = 6;
        label1.setText("Column Size:");
        label2.setText("Row Size:");
        setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
        if (source.equals(goButton)) {
            int ret1 = colBox.getSelectedIndex();
            selectedColSize = availableSizes[ret1];
            int ret2 = rowBox.getSelectedIndex();
            selectedRowSize = availableSizes[ret2];

        }
        setVisible(false);
    }
	
	public int getColumnSize(){
		return selectedColSize;
	}

	public int getRowSize(){
		return selectedRowSize;
	}
}
