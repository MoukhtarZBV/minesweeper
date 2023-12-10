package minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GridController implements ActionListener, MouseListener {

	private GridView view;
	private GridModel model;
	
	public GridController(GridView view) {
		this.view = view;
		this.model = new GridModel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CellJButton cell = (CellJButton) e.getSource();
		if (cell.getState() == State.UNDISCOVERED) {
			System.out.println(cell.getWidth() + " - " + cell.getHeight());
			State cellState = view.discoverCell(cell.getRow(), cell.getColumn());
			if (cellState == State.EMPTY) {
				view.discoverSurroundingEmptys(cell.getRow(), cell.getColumn());
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			CellJButton cell = (CellJButton) e.getSource();
			if (cell.getState() == State.UNDISCOVERED) {
				cell.setState(State.FLAG);
			} else if (cell.getState() == State.FLAG) {
				cell.setState(State.UNDISCOVERED);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
