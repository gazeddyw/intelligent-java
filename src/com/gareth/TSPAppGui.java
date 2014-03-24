/**
 * 
 */
package com.gareth;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
 * @author Gareth Williams
 *
 */
public class TSPAppGui {
	
	private JFrame frame;
	private JPanel mainPanel;
	private JPanel optionsPanel;
	private JMenuBar menuBar;
	
	private JMenu fileMenu;
	private JMenuItem fileMenuReadFile;
	private JMenuItem fileMenuInitiate;
		
	private JMenu optionsMenu;
	private JMenuItem optionsMenuReset;
	private static JTextArea tspArea;
	private JScrollPane textScroll;
	
	private JTextField popSizeField;
	private JLabel popSizeLabel;
	private JTextField tournamentSizeField;
	private JLabel tournamentSizeLabel;
	private JTextField crossoverRateField;
	private JLabel crossoverRateLabel;
	private JTextField mutationRateField;
	private JLabel mutationRateLabel;
	private JTextField cutOffValueField;
	private JLabel cutOffValueLabel;
	
	
	/**
	 * TSPAppGui Constructor. Builds the GUI when a new TSPAppGui is instantiated.
	 */
	public TSPAppGui() {
		frame = new JFrame();
		BorderLayout bl = new BorderLayout();
		mainPanel = new JPanel(bl);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		GridLayout grid = new GridLayout(6, 2);
		grid.setVgap(75);
		optionsPanel = new JPanel(grid);
		
		frame.setSize(900, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		fileMenuReadFile = new JMenuItem("Read File");
		fileMenuInitiate = new JMenuItem("Initiate GA");
		
		optionsMenu = new JMenu("Options");
		optionsMenuReset = new JMenuItem("Reset");
		
		fileMenuReadFile.addActionListener(new ReadFileMenuListener());
		fileMenuInitiate.addActionListener(new InitiateGA());
		optionsMenuReset.addActionListener(new ResetMenuListener());
		
		fileMenu.add(fileMenuReadFile);
		fileMenu.add(fileMenuInitiate);
		fileMenuInitiate.setEnabled(false);
		menuBar.add(fileMenu);
		optionsMenu.add(optionsMenuReset);
		menuBar.add(optionsMenu);
		
		tspArea = new JTextArea(30, 40);
		tspArea.setEditable(false);
		textScroll = new JScrollPane(tspArea);
		textScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		textScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		popSizeField = new JTextField(15);
		popSizeLabel = new JLabel("Initial population size:");
		tournamentSizeField = new JTextField(15);
		tournamentSizeLabel = new JLabel("Tournament size:");
		crossoverRateField = new JTextField(15);
		crossoverRateLabel = new JLabel("Crossover rate (as %):");
		mutationRateField = new JTextField(15);
		mutationRateLabel = new JLabel("Mutation rate (as %):");
		cutOffValueField = new JTextField(15);
		cutOffValueLabel = new JLabel("Cut-off value:");
		
		// Initialise text fields
		popSizeField.setText("100");
		tournamentSizeField.setText("10");
		crossoverRateField.setText("80");
		mutationRateField.setText("10");
		cutOffValueField.setText("10000");
		
		optionsPanel.add(BorderLayout.WEST, popSizeLabel);
		optionsPanel.add(popSizeField);
		optionsPanel.add(tournamentSizeLabel);
		optionsPanel.add(tournamentSizeField);
		optionsPanel.add(crossoverRateLabel);
		optionsPanel.add(crossoverRateField);
		optionsPanel.add(mutationRateLabel);
		optionsPanel.add(mutationRateField);
		optionsPanel.add(cutOffValueLabel);
		optionsPanel.add(cutOffValueField);
		
		mainPanel.add(BorderLayout.WEST, textScroll);
		mainPanel.add(BorderLayout.EAST, optionsPanel);
		
		frame.getContentPane().add(mainPanel);
		frame.add(mainPanel);
		frame.setJMenuBar(menuBar);
		frame.setVisible(true);
	}
	
	
	/**
	 * Creates the Parser, then sets the TextArea text to the file read in.
	 * 
	 *  @author Gareth Williams
	 */
	public void printFile() {
		Parser.getParser().readFile();
		tspArea.setText(Parser.getParser().getFile());
	}
	
	
	public static JTextArea getTextArea() {
		return tspArea;
	}
	
	
	///// INNER CLASSES /////
	
	// FILE //
	public class ReadFileMenuListener implements ActionListener {
		
		public void actionPerformed(ActionEvent ev) {
			JFileChooser tspFile = new JFileChooser();
			tspFile.showOpenDialog(frame);
			String path = tspFile.getName(tspFile.getSelectedFile());
			Parser.getParser().setFilePath(path);
			printFile();
			fileMenuReadFile.setEnabled(false);
			fileMenuInitiate.setEnabled(true);
		}
	}

	
	public class InitiateGA implements ActionListener {
		
		public void actionPerformed(ActionEvent ev) {
			int initialPopulationSize = Integer.parseInt(popSizeField.getText());
			int tournamentSize = Integer.parseInt(tournamentSizeField.getText());
			int crossoverRate = Integer.parseInt(crossoverRateField.getText());
			int mutationRate = Integer.parseInt(mutationRateField.getText());
			int strongestCutOff = Integer.parseInt(cutOffValueField.getText());
			GAController gac = new GAController();
			gac.setCutOff(strongestCutOff);
			gac.initTSPAlgorithm(initialPopulationSize, tournamentSize, crossoverRate, mutationRate);
		}
	}
	
	
	// OPTIONS //
	public class ResetMenuListener implements ActionListener {
		
		public void actionPerformed(ActionEvent ev) {
			System.out.println("RESET");
			Parser.getParser().resetParser();
			City.getCityList().clear();
			tspArea.setText(null);
			fileMenuReadFile.setEnabled(true);
			fileMenuInitiate.setEnabled(false);
		}
	}
}
