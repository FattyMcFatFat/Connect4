package view;

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

/**
 * class PreGameDialog
 * @author stgebhar
 *
 */
public class PreGameDialog extends JDialog implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private static final int THREE = 3;
	private static final int FOUR = 4;
	private static final int FIVE = 5;
    private static final int SIX = 6;
    private static final int SEVEN = 7;
    private static final int EIGHT = 8;
    private static final int NINE = 9;
    private static final int TEN = 10;
    private JButton goButton = new JButton("GOOOOOOO");
    private JLabel label1;
    private JLabel label2;
    private int selectedColSize;
    private int selectedRowSize;
    private int[] availableSizes = new int[] { FOUR, FIVE, SIX, SEVEN, EIGHT, NINE };
    private String[] availableSizesAsStrings = new String[] {"4", "5", "6", "7", "8", "9" };
    
    // Supress Warnings because Maven gets problems otherwise
    @SuppressWarnings("rawtypes")
   	private JComboBox colBox;
    @SuppressWarnings("rawtypes")
   	private JComboBox rowBox;

    /**
     * constructor
     * @param frame
     */
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
	
    /**
     * show the dialog
     */
	public void showDialog() {
		// Sehr Fehleranfaellig, aber tut :D
		rowBox.setSelectedIndex(2);
		colBox.setSelectedIndex(THREE);
		selectedRowSize = SIX;
		selectedColSize = SEVEN;
        label2.setText("Row Size:");
        label1.setText("Column Size:");
        setVisible(true);
    }

	/**
	 * ActionListener
	 */
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
	
	/**
	 * getter
	 * @return colSize
	 */
	public int getColumnSize(){
		return selectedColSize;
	}

	/**
	 * getter
	 * @return rowSize
	 */
	public int getRowSize(){
		return selectedRowSize;
	}
}
