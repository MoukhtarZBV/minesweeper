package minesweeper;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;

public class GridView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JPanel panelCells;
	private JPanel panelButtons;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	
	private List<CellJButton> cells;
	
	private Grid grid;

	public static final int CELL_SIZE = 30;
	
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
		
		JPanel panelCellsContainer = new JPanel();
		panelCellsContainer.setMaximumSize(new Dimension(15, 15));
		contentPane.add(panelCellsContainer, BorderLayout.CENTER);
		panelCellsContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelCellsContainer.setBackground(Color.decode("#1B1B1B"));

		
		panelCells = new JPanel();
		panelCells.setMaximumSize(new Dimension(15, 15));
		panelCellsContainer.add(panelCells);
		
		panelButtons = new JPanel();
		panelButtons.setBackground(Color.decode("#1B1B1B"));
		contentPane.add(panelButtons, BorderLayout.NORTH);
		
		btnNewButton = new JButton("New button");
		panelButtons.add(btnNewButton);
		
		btnNewButton_1 = new JButton("New button");
		panelButtons.add(btnNewButton_1);
		initialiseCells(12, 12, controller);
	}
	
	public void initialiseCells(int rows, int columns, GridController controller) {
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
		this.grid = new Grid(rows, columns, 20);
		System.out.println(grid);
	}
	
	public List<CellJButton> getCells(){
		return this.cells;
	}
	
	public Grid getGrid() {
		return this.grid;
	}
}
