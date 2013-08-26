package vi;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import observer.IObserver;

import mo.Grid;
import mo.Player;
import mo.Status;
import co.Controller;

/**
 * 
 * @author stgebhar
 * 
 */

public class Frame extends JFrame implements IObserver {

	private static final long serialVersionUID = 1L;
	private Controller controller;
	private Status status;
	private Player player;
	private Grid grid;
	private StatusPanel statusPanel;
	private GridPanel gridPanel;
	private PlayerNamesDialog playerNamesDialog;
	private PlayerColorDialog playerColorDialog;

	private Container pane;

	/**
	 * constructor
	 * 
	 * @param grid
	 * @param controller
	 */
	public Frame(final Grid grid, final Controller controller, final Player player) {
		this.status = grid.getStatus();
		this.controller = controller;
		this.player = player;
		this.grid = grid;
		
		setTitle("Vier Gewinnt");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(528, 630);
		pane = getContentPane();
		pane.setLayout(new BorderLayout());
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");

		// name game menu
		JMenuItem newMenuItem = new JMenuItem("New game");
		newMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null,"Are you sure?","Creating a new game",JOptionPane.YES_NO_OPTION); 
				if(n == JOptionPane.YES_OPTION){
					controller.newGrid();
				}
			}
		});

		fileMenu.add(newMenuItem);
		fileMenu.addSeparator();
		
		// options menu
		JMenu optionsMenuItem = new JMenu("Options");
		
		// options-submenu playernames
		JMenuItem playerSettings = new JMenuItem("Set Playernames");
		playerSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String p1 = player.getPlayerOneName();
				String p2 = player.getPlayerTwoName();
			
				playerNamesDialog.showDialog(p1, p2);
				controller.setPlayernames(playerNamesDialog.getFirstName(), playerNamesDialog.getLastName());
				
				 
			}
		});

		// options-submenu colors
		JMenuItem colorSettings = new JMenuItem("Set Colors");
		colorSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String p1 = player.getPlayerOneName();
				String p2 = player.getPlayerTwoName();
				
				playerColorDialog.showDialog(player.getPlayerOneColor(), player.getPlayerTwoColor(), p1, p2);
				controller.setColors(playerColorDialog.getP1Color(), playerColorDialog.getP2Color());
			}
		});
		optionsMenuItem.add(playerSettings);
		optionsMenuItem.add(colorSettings);
		
		fileMenu.add(optionsMenuItem);
		fileMenu.addSeparator();

		// quit menu
		JMenuItem quitMenuItem = new JMenuItem("Quit");
		quitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int n = JOptionPane.showConfirmDialog(null,"Are you sure?","Quit game",JOptionPane.YES_NO_OPTION); 
				if(n == JOptionPane.YES_OPTION){
					controller.doExit();
				}
				
			}
		});
		fileMenu.add(quitMenuItem);
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);

		playerNamesDialog = new PlayerNamesDialog(this);
		playerColorDialog = new PlayerColorDialog(this);
		
		constructPane();
	}

	/**
	 * buildes the components
	 * 
	 * @param grid
	 */
	public void constructPane() {

		if (gridPanel != null){
			pane.remove(gridPanel);
		}
		gridPanel = new GridPanel(grid, controller, player);
		pane.add(gridPanel, BorderLayout.CENTER);

		if (statusPanel != null){
			pane.remove(statusPanel);
		}
		statusPanel = new StatusPanel();
		pane.add(statusPanel, BorderLayout.SOUTH);
		setVisible(true);
		repaint();
	}

	/**
	 * update / repaint
	 */
	public void update() {
		statusPanel.setText(status.getText());
		repaint();
	}
}
