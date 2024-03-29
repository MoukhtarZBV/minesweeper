package minesweeper;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.MatteBorder;

import components.CellJButton;
import components.CustomComponent;
import components.Header;
import components.ShadowJLabel;
import ihm.CustomColor;
import ihm.CustomFont;
import ihm.Icon;
import ihm.Screen;

import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class GridView extends JFrame {
	
	public enum Setting{
		ROW_SPINNER("Rows spinner"), COLUMN_SPINNER("Columns spinner"), MINE_SPINNER("Mines spinner"), ROW_SLIDER("Rows slider"), COLUMN_SLIDER("Columns slider"), MINE_SLIDER("Mines slider");
	
		private String name;
		
		private Setting(String name) {
			this.name = name;
		}
		
		public static Setting getSetting(String name) {
			for (Setting setting : Setting.values()) {
				if (setting.name.equals(name)) {
					return setting;
				}
			}
			return null;
		}
	}

	private static final long serialVersionUID = 1L;
	
	private List<CellJButton> cells;
	
	private Grid grid;
	
	private GridController controller;
	
	public Timer timer;
	
	private long startTime;
	private long elapsedSettingsTime;
	
	private boolean firstGame;
	private boolean gameDone;

	public static final int CELL_SIZE = 30;
	
	private JPanel contentPane;
	private JPanel mainPanel;
	private JPanel panelCells;
	private JPanel panelButtonsContainer;
	private JPanel panelCellsContainer;
	private JPanel panelButtons;
	private JPanel panelRows;
	private JPanel panelSettingsContainer;
	private JPanel panelSettings;
	private JPanel panelColumns;
	private JPanel panelSettingsButtons;
	private JPanel panelMinesAmount;
	
	private JButton btnNewGame;
	private JButton btnSettings;
	private JButton btnStartGame;
	
	private JLabel lblTimer;
	private JLabel lblSettings;
	private JLabel lblRows;
	private JLabel lblColumns;
	private JLabel lblMinesAmount;
	
	private JSlider sliderRows;
	private JSpinner spinnerRows;
	private JSlider sliderColumns;
	private JSpinner spinnerColumns;
	private JSlider sliderMinesAmount;
	private JSpinner spinnerMinesAmount;
	
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

	public GridView() {
		controller = new GridController(this);
		firstGame = true;
		gameDone = false;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Screen.setup();
		setBounds(Screen.posX, Screen.posY, Screen.tailleX, Screen.tailleY);
		setResizable(false);
		setUndecorated(true);
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(CustomColor.DARK_GRAY);
		setContentPane(contentPane);
		
		Header header = new Header(this);
		header.setTitle("Minesweeper");
		contentPane.add(header, BorderLayout.NORTH);
		
		mainPanel = new JPanel();
		mainPanel.setBackground(CustomColor.DARK_GRAY);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(new EmptyBorder(40, 0, 0, 0));
		contentPane.add(mainPanel, BorderLayout.CENTER);
		
		// ============================== //
		// [Buttons container]
		panelButtonsContainer = new JPanel();
		panelButtonsContainer.setBackground(CustomColor.DARK_GRAY);
		mainPanel.add(panelButtonsContainer, BorderLayout.NORTH);
		
		// [Buttons panel]
		lblTimer = new JLabel();
		lblTimer.setFont(CustomFont.TIMER);
		lblTimer.setForeground(CustomColor.YELLOW);
		lblTimer.setPreferredSize(new Dimension(100, 36));
		lblTimer.setIconTextGap(10);
		startTime = System.currentTimeMillis();
		timer = new Timer(500, new ActionListener(){
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	long elapsedTime = System.currentTimeMillis() - startTime;
				long elapsedSeconds = elapsedTime / 1000;
				long secondsDisplay = elapsedSeconds % 60;
				long elapsedMinutes = elapsedSeconds / 60;
		        lblTimer.setText(String.format("%02d", elapsedMinutes) + ":" + String.format("%02d", secondsDisplay));
		    }
		});
		panelButtonsContainer.add(lblTimer);
		
		// [Buttons panel]
		panelButtons = new JPanel();
		panelButtons.setPreferredSize(new Dimension(360, 36));
		panelButtons.setBackground(CustomColor.DARK_GRAY);
		panelButtons.setVisible(false);
		((FlowLayout) panelButtons.getLayout()).setAlignment(FlowLayout.RIGHT);
		panelButtonsContainer.add(panelButtons);
		
		// [Settings button]
		btnSettings = CustomComponent.createButton("Settings", controller);
		panelButtons.add(btnSettings);
		
		// [New game button]
		btnNewGame = CustomComponent.createButton("New game", controller);
		panelButtons.add(btnNewGame);
		
		panelSettingsContainer = new JPanel();
		panelSettingsContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelSettingsContainer.setBackground(CustomColor.DARK_GRAY);
		mainPanel.add(panelSettingsContainer, BorderLayout.CENTER);
		
		// ============================== //
		// [Settings panel]
		GridBagLayout gbl_panelSettings = new GridBagLayout();
		gbl_panelSettings.columnWidths = new int[]{360, 0};
		gbl_panelSettings.rowHeights = new int[] {13, 20, 22, 0, 0, 0};
		gbl_panelSettings.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelSettings.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		panelSettings = new JPanel();
		panelSettings.setBackground(Color.decode("#1B1B1B"));
		panelSettings.setLayout(gbl_panelSettings);
		panelSettingsContainer.add(panelSettings);
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {110, 250, 70, 0};
		gbl_panel.rowHeights = new int[]{22, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.BOTH;
		gbc_spinner.gridx = 2;
		gbc_spinner.gridy = 0;
		
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.fill = GridBagConstraints.BOTH;
		gbc_slider.insets = new Insets(0, 0, 0, 5);
		gbc_slider.gridx = 1;
		gbc_slider.gridy = 0;
		
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		
		// [Settings label]
		GridBagConstraints gbc_lblSettings = new GridBagConstraints();
		gbc_lblSettings.anchor = GridBagConstraints.NORTH;
		gbc_lblSettings.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSettings.insets = new Insets(0, 0, 5, 0);
		gbc_lblSettings.gridx = 0;
		gbc_lblSettings.gridy = 0;
		lblSettings = new JLabel("Settings");
		lblSettings.setBorder(new CompoundBorder(new MatteBorder(0, 0, 1, 0, CustomColor.PURPLE), new EmptyBorder(0, 0, 5, 0)));
		lblSettings.setFont(CustomFont.SETTING);
		lblSettings.setForeground(CustomColor.YELLOW);
		panelSettings.add(lblSettings, gbc_lblSettings);
		
		// ============================== //
		// [Rows panel]
		panelRows = new JPanel();
		panelRows.setBorder(new EmptyBorder(15, 0, 0, 0));
		panelRows.setBackground(CustomColor.DARK_GRAY);
		panelRows.setLayout(gbl_panel);
		panelSettings.add(panelRows, gbc_panel);

		// [Rows label]
		lblRows = new JLabel("Rows");
		lblRows.setFont(CustomFont.SETTING);
		lblRows.setForeground(CustomColor.YELLOW);
		panelRows.add(lblRows, gbc_label);
		
		// [Rows slider]
		sliderRows = CustomComponent.createJSlider("Rows slider", 5, 20, 10, controller);
		panelRows.add(sliderRows, gbc_slider);
		
		// [Rows spinner]
		spinnerRows = CustomComponent.createJSpinner("Rows spinner", 5, 20, 10, controller);
		panelRows.add(spinnerRows, gbc_spinner);
		
		// ============================== //
		// [Columns panel]
		gbc_panel.gridy = 2;
		panelColumns = new JPanel();
		panelColumns.setBackground(CustomColor.DARK_GRAY);
		panelColumns.setLayout(gbl_panel);
		panelSettings.add(panelColumns, gbc_panel);
		
		// [Columns label]
		lblColumns = new JLabel("Columns");
		lblColumns.setFont(CustomFont.SETTING);
		lblColumns.setForeground(CustomColor.YELLOW);
		panelColumns.add(lblColumns, gbc_label);
		
		// [Columns slider]
		sliderColumns = CustomComponent.createJSlider("Columns slider", 5, 20, 10, controller);
		panelColumns.add(sliderColumns, gbc_slider);
		
		// [Columns spinner]
		spinnerColumns = CustomComponent.createJSpinner("Columns spinner", 5, 20, 10, controller);
		panelColumns.add(spinnerColumns, gbc_spinner);
		
		// ============================== //
		// [Mines amount panel]
		gbc_panel.gridy = 3;
		panelMinesAmount = new JPanel();
		panelMinesAmount.setBackground(CustomColor.DARK_GRAY);
		panelMinesAmount.setLayout(gbl_panel);
		panelSettings.add(panelMinesAmount, gbc_panel);
		
		// [Mines amount label]
		lblMinesAmount = new JLabel("Mines amount");
		lblMinesAmount.setFont(CustomFont.SETTING);
		lblMinesAmount.setForeground(CustomColor.YELLOW);
		panelMinesAmount.add(lblMinesAmount, gbc_label);
		
		// [Mines amount slider]
		sliderMinesAmount = CustomComponent.createJSlider("Mines slider", 5, 30, 10, controller);
		panelMinesAmount.add(sliderMinesAmount, gbc_slider);
		
		// [Mines amount spinner]
		spinnerMinesAmount = CustomComponent.createJSpinner("Mines spinner", 5, 30, 10, controller);
		panelMinesAmount.add(spinnerMinesAmount, gbc_spinner);
		setMaxMinesAmount();
		
		// ============================== //
		// [Start button panel]
		gbc_panel.gridy = 4;
		panelSettingsButtons = new JPanel();
		panelSettingsButtons.setBorder(new EmptyBorder(15, 0, 0, 0));
		panelSettingsButtons.setBackground(CustomColor.DARK_GRAY);
		panelSettings.add(panelSettingsButtons, gbc_panel);
		((FlowLayout) panelSettingsButtons.getLayout()).setAlignment(FlowLayout.RIGHT);
		
		// [Start button]
		btnStartGame = CustomComponent.createButton("Start new game", controller);
		panelSettingsButtons.add(btnStartGame);
		
		// ============================== //
		// [Cells panel]
		panelCellsContainer = new JPanel();
		panelCellsContainer.setMaximumSize(new Dimension(15, 15));
		panelCellsContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelCellsContainer.setBackground(CustomColor.DARK_GRAY);
	}
	
	public void initialiseCells(int rows, int columns, int minesAmount) {
		for (Component comp : panelCellsContainer.getComponents()) {
			panelCellsContainer.remove(comp);
		}
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
		grid = new Grid(rows, columns, minesAmount);
		panelButtons.setPreferredSize(new Dimension(Math.max(300, columns * CELL_SIZE) - lblTimer.getSize().width, 40));
		panelButtons.revalidate();
		panelButtons.repaint();
		gameDone = false;
		lblTimer.setText("00:00");
		timer.stop();
	}
	
	public List<CellJButton> getCells(){
		return this.cells;
	}
	
	public Grid getGrid() {
		return this.grid;
	}
	
	public void setActiveNewGame(boolean enabled) {
		btnNewGame.setEnabled(enabled);
	}
	
	public void startGame() {
		closeSettings();
		panelButtons.setVisible(true);
		if (firstGame) {
			addCloseButton();
			lblTimer.setIcon(Icon.TIMER);
			firstGame = false;
		}
		initialiseCells(getRows(), getColumns(), getMinesAmount());
	}

	public void closeSettings() {
		mainPanel.remove(panelSettingsContainer);
		mainPanel.add(panelCellsContainer, BorderLayout.CENTER);
		mainPanel.revalidate();
		mainPanel.repaint();
		if (!gameDone) resumeTimer();
	}
	
	public void openSettings() {
		elapsedSettingsTime = System.currentTimeMillis();
		mainPanel.remove(panelCellsContainer);
		mainPanel.add(panelSettingsContainer, BorderLayout.CENTER);
		mainPanel.revalidate();
		mainPanel.repaint();
		timer.stop();
	}
	
	public void startTimer() {
		startTime = System.currentTimeMillis();
		timer.start();
	}
	
	public void resumeTimer() {
		elapsedSettingsTime = System.currentTimeMillis() - elapsedSettingsTime;
		startTime += elapsedSettingsTime;
		timer.start();
	}
	
	public void gameDone(String text) {
		timer.stop();
		gameDone = true;
		
		JLayeredPane panelLayered = new JLayeredPane();
		panelLayered.setPreferredSize(panelCells.getPreferredSize());

		panelLayered.add(panelCells, JLayeredPane.DEFAULT_LAYER);
		panelCells.setBounds(0, 0, panelCells.getPreferredSize().width, panelCells.getPreferredSize().height);

		JPanel labelHolder = new JPanel();
		labelHolder.setOpaque(false);
		labelHolder.setLayout(null);
		
		ShadowJLabel label = new ShadowJLabel(text);
		label.setFont(new Font("Arial", Font.BOLD, 24));
		label.setForeground(CustomColor.YELLOW);
		label.setShadowSize(10);
		label.setBorder(new EmptyBorder(60, 60, 60, 60));
		
		int labelWidth = label.getPreferredSize().width;
		int labelHeight = label.getPreferredSize().height;
		int centerX = (panelCells.getWidth() - labelWidth) / 2;
		int centerY = (panelCells.getHeight() - labelHeight) / 2;
		
		label.setBounds(centerX, centerY, labelWidth, labelHeight);
		labelHolder.add(label);

		panelLayered.add(labelHolder, JLayeredPane.PALETTE_LAYER);
		labelHolder.setBounds(0, 0, panelCells.getPreferredSize().width, panelCells.getPreferredSize().height);

		panelCellsContainer.add(panelLayered);
		panelCellsContainer.revalidate();
		panelCellsContainer.repaint();
	}
	
	public void addCloseButton() {
		JButton btnCloseSettings = CustomComponent.createButton("Close settings", controller);
		panelSettingsButtons.add(btnCloseSettings);
	}
	
	public int getRows() {
		return sliderRows.getValue();
	}
	
	public int getColumns() {
		return sliderColumns.getValue();
	}
	
	public int getMinesAmount() {
		return sliderMinesAmount.getValue();
	}
	
	public void setSpinnerValue(Setting slider, int value) {
		switch (slider) {
			case ROW_SLIDER:
				spinnerRows.setValue(value);
				break;
			case COLUMN_SLIDER:
				spinnerColumns.setValue(value);
				break;
			case MINE_SLIDER:
				spinnerMinesAmount.setValue(value);
				break;
			default:
		}
		setMaxMinesAmount();
	}
	
	public void setSliderValue(Setting spinner, int value) {
		switch (spinner) {
			case ROW_SPINNER:
				sliderRows.setValue(value);
				break;
			case COLUMN_SPINNER:
				sliderColumns.setValue(value);
				break;
			case MINE_SPINNER:
				sliderMinesAmount.setValue(value);
				break;
			default:
		}
		setMaxMinesAmount();
	}
	
	public void setMaxMinesAmount() {
		int maxAmount = (int) (sliderRows.getValue() * sliderColumns.getValue() * 0.5f);
		spinnerMinesAmount.setModel(new SpinnerNumberModel(Math.min(sliderMinesAmount.getValue(), maxAmount), 5, maxAmount, 1));
		JTextField spinnerField = (JTextField) spinnerMinesAmount.getEditor().getComponent(0);
		spinnerField.setFont(CustomFont.SETTING);
		spinnerField.setBackground(CustomColor.DARK_GRAY);
		spinnerField.setForeground(CustomColor.YELLOW);
		spinnerField.setBorder(new CompoundBorder(CustomComponent.PURPLE_BORDER, new EmptyBorder(0, 0, 0, 5)));
		sliderMinesAmount.setMaximum(maxAmount);
	}
}
