package minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GridController implements ActionListener, MouseListener {

	private enum ControllerState {
		GAME_SETTINGS, GAME_ONGOING, GAME_FINISHED
	}
	
	private GridView view;
	private GridModel model;
	private ControllerState state;
	
	public GridController(GridView view) {
		this.view = view;
		this.model = new GridModel();
		this.state = ControllerState.GAME_ONGOING;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (this.state) {
		case GAME_SETTINGS:
			break;
		case GAME_ONGOING:
			CellJButton cell = (CellJButton) e.getSource();
			if (cell.getState() == State.UNDISCOVERED) {
				State cellState = model.discoverCell(cell.getRow(), cell.getColumn(), view.getCells(), view.getGrid());
				if (cellState == State.EMPTY) {
					model.discoverSurroundingEmptys(cell.getRow(), cell.getColumn(), view.getCells(), view.getGrid());
				} else if (cellState == State.MINE) {
					model.gameOver(view.getCells(), view.getGrid());
					this.state = ControllerState.GAME_FINISHED;
				}
			}
			break;
		case GAME_FINISHED:
			break;
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3 && this.state == ControllerState.GAME_ONGOING) {
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
