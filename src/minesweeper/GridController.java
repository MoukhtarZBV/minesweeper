package minesweeper;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import minesweeper.GridView.Setting;

public class GridController implements ActionListener, MouseListener, ChangeListener {

	private enum ControllerState {
		GAME_SETTINGS, GAME_START, GAME_ONGOING, GAME_FINISHED
	}
	
	private GridView view;
	private GridModel model;
	private ControllerState state;
	
	public GridController(GridView view) {
		this.view = view;
		this.model = new GridModel();
		this.state = ControllerState.GAME_SETTINGS;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (this.state) {
		case GAME_SETTINGS:
			JButton button = (JButton) e.getSource();
			if (button.getName().equals("Start game")) {
				view.startGame();
				view.setActiveNewGame(false);
				this.state = ControllerState.GAME_START;
			}
			break;
		case GAME_START:
			if (e.getSource() instanceof CellJButton) {
				CellJButton cell = (CellJButton) e.getSource();
				if (cell.getState() == State.UNDISCOVERED) {
					State cellState = model.discoverCell(cell.getRow(), cell.getColumn(), view.getCells(), view.getGrid());
					if (cellState == State.EMPTY) {
						model.discoverSurroundingEmptys(cell.getRow(), cell.getColumn(), view.getCells(), view.getGrid());
					} else if (cellState == State.MINE) {
						model.changeMinePosition(cell.getRow(), cell.getColumn(), view.getGrid());
						model.discoverSurroundingEmptys(cell.getRow(), cell.getColumn(), view.getCells(), view.getGrid());
					}
					view.setActiveNewGame(true);
					this.state = ControllerState.GAME_ONGOING;
				}
			} 
			break;
		case GAME_ONGOING:
			if (e.getSource() instanceof CellJButton) {
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
			} else {
				JButton button3 = (JButton) e.getSource();
				if (button3.getName().equals("New Game")) {
					view.initialiseCells(view.getRows(), view.getColumns(), view.getMinesAmount());
					view.setActiveNewGame(false);
					this.state = ControllerState.GAME_START;
				}
			}
			break;
		case GAME_FINISHED:
			if (!(e.getSource() instanceof CellJButton)) {
				JButton button4 = (JButton) e.getSource();
				if (button4.getName().equals("New Game")) {
					view.initialiseCells(view.getRows(), view.getColumns(), view.getMinesAmount());
					view.setActiveNewGame(false);
					this.state = ControllerState.GAME_START;
				}
			}
			break;
		}
		
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() instanceof JSlider) {
			JSlider slider = (JSlider) e.getSource();
			view.setSpinnerValue(Setting.getSetting(slider.getName()), slider.getValue());
		} else if (e.getSource() instanceof JSpinner) {
			JSpinner spinner = (JSpinner) e.getSource();
			view.setSliderValue(Setting.getSetting(spinner.getName()), (int) spinner.getValue());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
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
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
