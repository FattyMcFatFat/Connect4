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

import model.Globals;

/**
 * GridSizeDialog
 * @author stgebhar
 * currently this can be used but there is no point in using it
 */
public class GridSizeDialog extends JDialog implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private static final int FOUR = 4;
	private static final int FIVE = 5;
	private static final int SIX = 6;
	private static final int SEVEN = 7;
	private static final int EIGHT = 8;
	private static final int NINE = 9;
    private static final int TEN = 10;
    private JButton saveButton = new JButton("Save");
    private JButton quitButton = new JButton("Cancel");
    private JLabel label1;
    private JLabel label2;
    private int selectedColSize;
    private int selectedRowSize;
    private int[] availableSizes = new int[] { 
    		FOUR, 
    		FIVE, 
    		SIX, 
    		SEVEN, 
    		EIGHT, 
    		NINE};
    private String[] availableSizesAsStrings = new String[] { "5", "6", "7", "8", "9" };
    private Globals gv;
    
    // Supress Warnings because Maven gets problems otherwise
    @SuppressWarnings("rawtypes")
   	private JComboBox colBox;
    @SuppressWarnings("rawtypes")
   	private JComboBox rowBox;

    /**
     * constructor
     * @param frame f
     * @param glob g
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public GridSizeDialog(JFrame frame, Globals glob) {
		super(frame, "Grid Size", true);
		this.gv = glob;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        saveButton.addActionListener(this);
        quitButton.addActionListener(this);
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
        panelS.add(saveButton);
        panelS.add(quitButton);
        add(panelS, BorderLayout.SOUTH);
        pack();
	}
	
    /**
     * show the dialog
     */
	public void showDialog() {
		// Sehr Fehleranfaellig, aber tut :D
		colBox.setSelectedIndex(gv.getColSize() - FIVE);
		rowBox.setSelectedIndex(gv.getRowSize() - FIVE);
		selectedColSize = gv.getColSize();
		selectedRowSize = gv.getRowSize();
        label1.setText("Column Size:");
        label2.setText("Row Size:");
        setVisible(true);
    }

	/**
	 * ActionListener
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
        if (source.equals(saveButton)) {
            int ret1 = colBox.getSelectedIndex();
            selectedColSize = availableSizes[ret1];
            int ret2 = rowBox.getSelectedIndex();
            selectedRowSize = availableSizes[ret2];

        }
        setVisible(false);
    }
	
	/**
	 * getter for colsize
	 * @return rowsize
	 */
	public int getColumnSize(){
		return selectedColSize;
	}

	/**
	 * setter for colsize
	 * @return rowsize
	 */
	public int getRowSize(){
		return selectedRowSize;
	}
}
