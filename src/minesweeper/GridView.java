package minesweeper;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;
import javax.swing.JSlider;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SpinnerNumberModel;

public class GridView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JPanel panelCells;
	private JPanel panelButtonsContainer;
	private JPanel panelCellsContainer;
	
	private List<CellJButton> cells;
	
	private Grid grid;

	public static final int CELL_SIZE = 30;
	private JPanel panelButtons;
	private JButton btnNewGame;
	private JButton btnSettings;
	private JPanel panelSettingsContainer;
	private JPanel panelSettings;
	private JLabel lblSettings;
	private JPanel panelRows;
	private JLabel lblRows;
	private JSlider sliderRows;
	private JSpinner spinnerRows;
	private JPanel panelColumns;
	private JLabel lblColumns;
	private JSlider sliderColumns;
	private JSpinner spinnerColumns;
	private JPanel panelMinesAmount;
	private JLabel lblMinesAmount;
	private JSlider sliderMinesAmount;
	private JSpinner spinnerMinesAmount;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GridView frame = new GridView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GridView() {
		GridController controller = new GridController(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(Color.decode("#1B1B1B"));
		
		// ============================== //
		// [Buttons container]
		panelButtonsContainer = new JPanel();
		panelButtonsContainer.setBackground(Color.decode("#1B1B1B"));
		contentPane.add(panelButtonsContainer, BorderLayout.NORTH);
		
		// [Buttons panel]
		panelButtons = new JPanel();
		panelButtons.setPreferredSize(new Dimension(360, 36));
		panelButtons.setBackground(Color.decode("#1B1B1B"));
		FlowLayout flowLayout = (FlowLayout) panelButtons.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelButtonsContainer.add(panelButtons);
		
		// [Settings button]
		btnSettings = new JButton("<html><body style='padding: 5px 10px;'>Settings</body></html>");
		btnSettings.setName("Settings");
		btnSettings.setForeground(Color.decode("#C4C38A"));
		btnSettings.setBackground(Color.decode("#1B1B1B"));
		btnSettings.setBorder(new LineBorder(new Color(85, 53, 85)));
		panelButtons.add(btnSettings);
		
		// [New game button]
		btnNewGame = new JButton("<html><body style='padding: 5px 10px;'>New game</body></html>");
		btnNewGame.setName("New Game");
		btnNewGame.setForeground(Color.decode("#C4C38A"));
		btnNewGame.setBackground(Color.decode("#1B1B1B"));
		btnNewGame.setBorder(new LineBorder(new Color(85, 53, 85)));
		btnNewGame.addActionListener(controller);
		panelButtons.add(btnNewGame);
		
		panelSettingsContainer = new JPanel();
		panelSettingsContainer.setMaximumSize(new Dimension(15, 15));
		panelSettingsContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelSettingsContainer.setBackground(Color.decode("#1B1B1B"));
		contentPane.add(panelSettingsContainer, BorderLayout.CENTER);
		
		// ============================== //
		// [Settings panel]
		GridBagLayout gbl_panelSettings = new GridBagLayout();
		gbl_panelSettings.columnWidths = new int[]{360, 0};
		gbl_panelSettings.rowHeights = new int[] {13, 20, 22, 0, 0};
		gbl_panelSettings.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelSettings.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelSettings = new JPanel();
		panelSettings.setBackground(Color.decode("#1B1B1B"));
		panelSettings.setLayout(gbl_panelSettings);
		panelSettingsContainer.add(panelSettings);
		
		// [Settings label]
		GridBagConstraints gbc_lblSettings = new GridBagConstraints();
		gbc_lblSettings.anchor = GridBagConstraints.NORTH;
		gbc_lblSettings.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSettings.insets = new Insets(0, 0, 5, 0);
		gbc_lblSettings.gridx = 0;
		gbc_lblSettings.gridy = 0;
		lblSettings = new JLabel("Settings");
		lblSettings.setBorder(new MatteBorder(0, 0, 1, 0, new Color(85, 53, 85)));
		lblSettings.setForeground(Color.decode("#C4C38A"));
		panelSettings.add(lblSettings, gbc_lblSettings);
		
		// ============================== //
		// [Rows panel]
		GridBagConstraints gbc_panelRows = new GridBagConstraints();
		gbc_panelRows.fill = GridBagConstraints.BOTH;
		gbc_panelRows.insets = new Insets(0, 0, 5, 0);
		gbc_panelRows.gridx = 0;
		gbc_panelRows.gridy = 1;
		
		GridBagLayout gbl_panelRows = new GridBagLayout();
		gbl_panelRows.columnWidths = new int[] {80, 160, 120, 0};
		gbl_panelRows.rowHeights = new int[]{22, 0};
		gbl_panelRows.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelRows.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		
		panelRows = new JPanel();
		panelRows.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelRows.setBackground(Color.decode("#1B1B1B"));
		panelRows.setLayout(gbl_panelRows);
		panelSettings.add(panelRows, gbc_panelRows);

		// [Rows label]
		GridBagConstraints gbc_lblRows = new GridBagConstraints();
		gbc_lblRows.fill = GridBagConstraints.BOTH;
		gbc_lblRows.insets = new Insets(0, 0, 0, 5);
		gbc_lblRows.gridx = 0;
		gbc_lblRows.gridy = 0;
		lblRows = new JLabel("Rows");
		lblRows.setForeground(Color.decode("#C4C38A"));
		panelRows.add(lblRows, gbc_lblRows);
		
		// [Rows slider]
		GridBagConstraints gbc_sliderRows = new GridBagConstraints();
		gbc_sliderRows.fill = GridBagConstraints.BOTH;
		gbc_sliderRows.insets = new Insets(0, 0, 0, 5);
		gbc_sliderRows.gridx = 1;
		gbc_sliderRows.gridy = 0;
		sliderRows = new JSlider();
		sliderRows.setForeground(new Color(255, 255, 255));
		sliderRows.setMinimum(5);
		sliderRows.setMaximum(30);
		sliderRows.setBackground(Color.decode("#1B1B1B"));
		panelRows.add(sliderRows, gbc_sliderRows);
		
		// [Rows spinner]
		GridBagConstraints gbc_spinnerRows = new GridBagConstraints();
		gbc_spinnerRows.fill = GridBagConstraints.BOTH;
		gbc_spinnerRows.gridx = 2;
		gbc_spinnerRows.gridy = 0;
		spinnerRows = new JSpinner();
		spinnerRows.setModel(new SpinnerNumberModel(5, 5, 30, 1));
		panelRows.add(spinnerRows, gbc_spinnerRows);
		
		// ============================== //
		// [Columns panel]
		GridBagConstraints gbc_panelColumns = new GridBagConstraints();
		gbc_panelColumns.insets = new Insets(0, 0, 5, 0);
		gbc_panelColumns.anchor = GridBagConstraints.NORTH;
		gbc_panelColumns.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelColumns.gridx = 0;
		gbc_panelColumns.gridy = 2;

		GridBagLayout gbl_panelColumns = new GridBagLayout();
		gbl_panelColumns.columnWidths = new int[] {80, 160, 120, 0};
		gbl_panelColumns.rowHeights = new int[]{22, 0};
		gbl_panelColumns.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelColumns.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		
		panelColumns = new JPanel();
		panelColumns.setBackground(Color.decode("#1B1B1B"));
		panelSettings.add(panelColumns, gbc_panelColumns);
		panelColumns.setLayout(gbl_panelColumns);
		
		// [Columns label]
		GridBagConstraints gbc_lblColumns = new GridBagConstraints();
		gbc_lblColumns.fill = GridBagConstraints.BOTH;
		gbc_lblColumns.insets = new Insets(0, 0, 0, 5);
		gbc_lblColumns.gridx = 0;
		gbc_lblColumns.gridy = 0;
		lblColumns = new JLabel("Columns");
		lblColumns.setForeground(Color.decode("#C4C38A"));
		panelColumns.add(lblColumns, gbc_lblColumns);
		
		// [Columns slider]
		GridBagConstraints gbc_sliderColumns = new GridBagConstraints();
		gbc_sliderColumns.fill = GridBagConstraints.BOTH;
		gbc_sliderColumns.insets = new Insets(0, 0, 0, 5);
		gbc_sliderColumns.gridx = 1;
		gbc_sliderColumns.gridy = 0;
		sliderColumns = new JSlider();
		sliderColumns.setMaximum(30);
		sliderColumns.setMinimum(5);
		sliderColumns.setBackground(Color.decode("#1B1B1B"));
		panelColumns.add(sliderColumns, gbc_sliderColumns);
		
		// [Columns spinner]
		GridBagConstraints gbc_spinnerColumns = new GridBagConstraints();
		gbc_spinnerColumns.fill = GridBagConstraints.BOTH;
		gbc_spinnerColumns.gridx = 2;
		gbc_spinnerColumns.gridy = 0;
		spinnerColumns = new JSpinner();
		spinnerColumns.setModel(new SpinnerNumberModel(5, 5, 30, 1));
		panelColumns.add(spinnerColumns, gbc_spinnerColumns);
		
		// ============================== //
		// [Mines amount panel]
		GridBagConstraints gbc_panelMinesAmount = new GridBagConstraints();
		gbc_panelMinesAmount.fill = GridBagConstraints.BOTH;
		gbc_panelMinesAmount.gridx = 0;
		gbc_panelMinesAmount.gridy = 3;
		
		GridBagLayout gbl_panelMinesAmount = new GridBagLayout();
		gbl_panelMinesAmount.columnWidths = new int[] {80, 160, 120, 0};
		gbl_panelMinesAmount.rowHeights = new int[]{22, 0};
		gbl_panelMinesAmount.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelMinesAmount.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		
		panelMinesAmount = new JPanel();
		panelMinesAmount.setBackground(Color.decode("#1B1B1B"));
		panelSettings.add(panelMinesAmount, gbc_panelMinesAmount);
		panelMinesAmount.setLayout(gbl_panelMinesAmount);
		
		// [Mines amount label]
		GridBagConstraints gbc_lblMinesAmount = new GridBagConstraints();
		gbc_lblMinesAmount.fill = GridBagConstraints.BOTH;
		gbc_lblMinesAmount.insets = new Insets(0, 0, 0, 5);
		gbc_lblMinesAmount.gridx = 0;
		gbc_lblMinesAmount.gridy = 0;
		lblMinesAmount = new JLabel("Mines amount");
		lblMinesAmount.setForeground(Color.decode("#C4C38A"));
		panelMinesAmount.add(lblMinesAmount, gbc_lblMinesAmount);
		
		// [Mines amount slider]
		GridBagConstraints gbc_sliderMinesAmount = new GridBagConstraints();
		gbc_sliderMinesAmount.fill = GridBagConstraints.BOTH;
		gbc_sliderMinesAmount.insets = new Insets(0, 0, 0, 5);
		gbc_sliderMinesAmount.gridx = 1;
		gbc_sliderMinesAmount.gridy = 0;
		sliderMinesAmount = new JSlider();
		sliderMinesAmount.setBackground(Color.decode("#1B1B1B"));
		panelMinesAmount.add(sliderMinesAmount, gbc_sliderMinesAmount);
		
		// [Mines amount spinner]
		GridBagConstraints gbc_spinnerMinesAmount = new GridBagConstraints();
		gbc_spinnerMinesAmount.fill = GridBagConstraints.BOTH;
		gbc_spinnerMinesAmount.gridx = 2;
		gbc_spinnerMinesAmount.gridy = 0;
		spinnerMinesAmount = new JSpinner();
		panelMinesAmount.add(spinnerMinesAmount, gbc_spinnerMinesAmount);
		
		// ============================== //
		// [Cells panel]
		/*
		panelCellsContainer = new JPanel();
		panelCellsContainer.setMaximumSize(new Dimension(15, 15));
		panelCellsContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelCellsContainer.setBackground(Color.decode("#1B1B1B"));
		contentPane.add(panelCellsContainer, BorderLayout.CENTER);
		
		panelCells = new JPanel();
		panelCellsContainer.add(panelCells);
		initialiseCells(12, 12, controller);*/
	}
	
	public void initialiseCells(int rows, int columns, GridController controller) {
		panelCellsContainer.remove(panelCells);
		panelCellsContainer.revalidate();
		panelCellsContainer.repaint();
		panelCells = new JPanel();
		panelCellsContainer.add(panelCells);
		panelCells.setLayout(new GridLayout(rows, columns, 0, 0));
		cells = new ArrayList<>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				CellJButton cell = new CellJButton(i, j);
				cell.setState(State.UNDISCOVERED);
				cell.addMouseListener(controller);
				cell.addActionListener(controller);
				cell.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
				cell.setBorderPainted(false);
				cells.add(cell);
				panelCells.add(cell);
			}
		}
		this.grid = new Grid(rows, columns, 30);
		System.out.println(grid);
	}
	
	public List<CellJButton> getCells(){
		return this.cells;
	}
	
	public Grid getGrid() {
		return this.grid;
	}
}
