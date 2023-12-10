package minesweeper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grid {

	private int[][] grid;
	private int columns;
	private int rows;
	private int numberOfBombs;
	
	public Grid(int rows, int columns, int numberOfBombs) {
		this.grid = new int[rows][columns];
		this.columns = columns;
		this.rows = rows;
		this.numberOfBombs = numberOfBombs;
		this.generateGrid();
	}
	
	public int getRows() {
		return this.rows;
	}
	
	public int getColumns() {
		return this.columns;
	}
	
	public int getValueAt(int x, int y) {
		return grid[x][y];
	}
	
	private void setCell(int x, int y, int value) {
		this.grid[x][y] = value;
	}
	
	private void generateGrid() {
		List<Integer> positionOfBombs = new ArrayList<>();
		for (int i = 0; i < this.numberOfBombs; i++) {
			positionOfBombs.add(1);
		}
		for (int i = this.numberOfBombs; i < this.rows * this.columns; i++) {
			positionOfBombs.add(0);
		}
		Collections.shuffle(positionOfBombs);
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				this.setCell(i, j, positionOfBombs.get((i*this.columns) + j));
			}
		}
	}
	
	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < this.rows; i++) {
			str += "\n| ";
			for (int j = 0; j < this.columns; j++) {
				str += this.grid[i][j] + " | ";
			}
			str += "\n";
		}
		return str;
	}
}
