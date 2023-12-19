package minesweeper;

import java.awt.Point;
import java.util.List;
import java.util.Random;

import javax.swing.SwingWorker;

public class GridModel {

	public void gameOver(List<CellJButton> cells, Grid grid) {
		SwingWorker<Void, Point> worker = new SwingWorker<Void, Point>() {
            @Override
            protected Void doInBackground() {
                for (Point point : grid.getCoordinatesOfMines()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    publish(point);
                }
                return null;
            }

            @Override
            protected void process(List<Point> chunks) {
                for (Point point : chunks) {
                    discoverCell(point.x, point.y, cells, grid);
                }
            }
        };
        worker.execute();
	}
	
	public CellJButton getCellAt(int x, int y, List<CellJButton> cells) {
		for (CellJButton cell : cells) {
			if (cell.getRow() == x && cell.getColumn() == y) {
				return cell;
			}
		}
		return null;
	}
	
	public State discoverCell(int x, int y, List<CellJButton> cells, Grid grid) {
		CellJButton cell = getCellAt(x, y, cells);
		if (grid.getValueAt(x, y) == 0) {
			State state = State.getState(grid.numberOfAdjacentMines(x, y));
			cell.setState(state);
			return state;
		}
		cell.setState(State.MINE);
		return State.MINE;
	}
	
	public void changeMinePosition(int x, int y, Grid grid) {
		int newX, newY;
		do {
			newX = new Random().nextInt(grid.getRows());
			newY = new Random().nextInt(grid.getColumns());
		} while (grid.getValueAt(newX, newY) == 1);
		grid.setValueAt(newX, newY, 1);
		grid.setValueAt(x,  y, 0);
	}
	
	public void discoverSurroundingEmptys(int x, int y, List<CellJButton> cells, Grid grid) {
		if (discoverCell(x, y, cells, grid) == State.EMPTY) {
			for (int i = x - 1; i <= x + 1; i++) {
				for (int j = y - 1; j <= y + 1; j++) {
					if (i >= 0 && i < grid.getRows() &&
						j >= 0 && j < grid.getColumns() &&
						!(i == x && j == y)) {
							if (getCellAt(i, j, cells).getState() == State.UNDISCOVERED ||
								getCellAt(i, j, cells).getState() == State.FLAG) {
								discoverSurroundingEmptys(i, j, cells, grid);
							}
					}
				}
			}
		}
	}
}
