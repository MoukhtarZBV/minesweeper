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
		
		panelCellsContainer = new JPanel();
		panelCellsContainer.setMaximumSize(new Dimension(15, 15));
		contentPane.add(panelCellsContainer, BorderLayout.CENTER);
		panelCellsContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelCellsContainer.setBackground(Color.decode("#1B1B1B"));
		
		panelCells = new JPanel();
		panelCellsContainer.add(panelCells);
		
		panelButtonsContainer = new JPanel();
		panelButtonsContainer.setBackground(Color.decode("#1B1B1B"));
		contentPane.add(panelButtonsContainer, BorderLayout.NORTH);
		
		panelButtons = new JPanel();
		panelButtons.setPreferredSize(new Dimension(360, 36));
		panelButtons.setBackground(Color.decode("#1B1B1B"));
		FlowLayout flowLayout = (FlowLayout) panelButtons.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelButtonsContainer.add(panelButtons);
		
		btnSettings = new JButton("<html><body style='padding: 5px 10px;'>Settings</body></html>");
		btnSettings.setName("Settings");
		btnSettings.setForeground(Color.decode("#C4C38A"));
		btnSettings.setBackground(Color.decode("#1B1B1B"));
		btnSettings.setBorder(new LineBorder(new Color(85, 53, 85)));
		panelButtons.add(btnSettings);
		
		btnNewGame = new JButton("<html><body style='padding: 5px 10px;'>New game</body></html>");
		btnNewGame.setName("New Game");
		btnNewGame.setForeground(Color.decode("#C4C38A"));
		btnNewGame.setBackground(Color.decode("#1B1B1B"));
		btnNewGame.setBorder(new LineBorder(new Color(85, 53, 85)));
		btnNewGame.addActionListener(controller);
		panelButtons.add(btnNewGame);
		
		initialiseCells(12, 12, controller);
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
