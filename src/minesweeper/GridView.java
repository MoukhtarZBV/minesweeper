package minesweeper;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
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
				CellJButton button = new CellJButton(i, j);
				button.addMouseListener(controller);
				cells.add(button);
				button.setPreferredSize(new Dimension(20,20));
				panelCells.add(button);
			}
		}
		this.grid = new Grid(rows, columns, 50);
	}

	public void setFlag(int x, int y) {
		CellJButton cell = getCellAt(x, y);
		if (cell != null) {
			cell.setState(State.FLAG);
		}
	}
	
	public CellJButton getCellAt(int x, int y) {
		for (CellJButton cell : cells) {
			if (cell.getRow() == x && cell.getColumn() == y) {
				return cell;
			}
		}
		return null;
	}
}
