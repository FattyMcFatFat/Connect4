package view;

import java.awt.BorderLayout;
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

import model.Globals;

public class GridSizeDialog extends JDialog implements ActionListener{
	
	private static final long serialVersionUID = 1L;
    private static final int TEN = 10;
    private static final int FIVE = 5;
    private JButton saveButton = new JButton("Save");
    private JButton quitButton = new JButton("Cancel");
    private JLabel label1;
    private JLabel label2;
    private int selectedColSize;
    private int selectedRowSize;
    private int[] availableSizes = new int[] { 4, 5, 6, 7, 8, 9 };
    private String[] availableSizesAsStrings = new String[] { "4", "5", "6", "7", "8", "9" };
    private Globals gv;
    
    // Supress Warnings because Maven gets problems otherwise
    @SuppressWarnings("rawtypes")
   	private JComboBox colBox;
    @SuppressWarnings("rawtypes")
   	private JComboBox rowBox;

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
	
	public void showDialog() {
		// TODO: Noch n wurm drin bei selected index
		colBox.setSelectedIndex(Arrays.asList(availableSizes).indexOf(gv.COL_SIZE));
		rowBox.setSelectedIndex(Arrays.asList(availableSizes).indexOf(gv.ROW_SIZE));
		selectedColSize = gv.COL_SIZE;
		selectedRowSize = gv.ROW_SIZE;
        label1.setText("Column Size:");
        label2.setText("Row Size:");
        setVisible(true);
    }

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
	
	public int getColumnSize(){
		return selectedColSize;
	}

	public int getRowSize(){
		return selectedRowSize;
	}
}
