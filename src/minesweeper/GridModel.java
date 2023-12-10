package minesweeper;

public class GridModel {

	public int numberOfAdjacentMines(Grid grid, int x, int y) {
		int number = 0;
		for (int i = x - 1; i < x + 1; i++) {
			for (int j = y - 1; j < y + 1; j++) {
				if (i > 0 && i < grid.getRows() &&
					j > 0 && j < grid.getColumns() &&
					i != x && y != y)	{
					number += grid.getValueAt(i, j);
				}
			}
		}
		return number;
	}
}
