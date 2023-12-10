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
	private JPanel panel;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	
	private List<CellJButton> cells;
	
	private Grid grid;

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
		
		JPanel panelCellsContainer = new JPanel();
		panelCellsContainer.setMaximumSize(new Dimension(15, 15));
		contentPane.add(panelCellsContainer, BorderLayout.CENTER);
		panelCellsContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		
		panelCells = new JPanel();
		panelCells.setMaximumSize(new Dimension(15, 15));
		panelCellsContainer.add(panelCells);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		btnNewButton = new JButton("New button");
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("New button");
		panel.add(btnNewButton_1);
		initialiseCells(18, 12, controller);
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
				cell.setBackground(Color.WHITE);
				cell.setPreferredSize(new Dimension(20,20));
				cells.add(cell);
				panelCells.add(cell);
			}
		}
		this.grid = new Grid(rows, columns, 20);
		System.out.println(grid);
	}
	
	public State undiscoverCell(int x, int y) {
		CellJButton cell = getCellAt(x, y);
		if (this.grid.getValueAt(x, y) == 0) {
			State state = State.getState(this.grid.numberOfAdjacentMines(x, y));
			cell.setState(state);
			return state;
		}
		cell.setState(State.MINE);
		return State.MINE;
	}
	
	public CellJButton getCellAt(int x, int y) {
		for (CellJButton cell : cells) {
			if (cell.getRow() == x && cell.getColumn() == y) {
				return cell;
			}
		}
		return null;
	}
	
	public void undiscoverSurroundingEmptys(int x, int y) {
		if (undiscoverCell(x, y) == State.EMPTY) {
			for (int i = x - 1; i <= x + 1; i++) {
				for (int j = y - 1; j <= y + 1; j++) {
					if (i >= 0 && i < this.grid.getRows() &&
						j >= 0 && j < this.grid.getColumns() &&
						!(i == x && j == y)) {
							if (getCellAt(i, j).getState() == State.UNDISCOVERED) {
								undiscoverSurroundingEmptys(i, j);
							}
					}
				}
			}
		}
	}
}
