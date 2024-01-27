package minesweeper;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grid {

	private int[][] grid;
	private int columns;
	private int rows;
	private int numberOfMines;
	private List<Point> coordinatesOfMines;
	
	public Grid(int rows, int columns, int numberOfBombs) {
		this.grid = new int[rows][columns];
		this.columns = columns;
		this.rows = rows;
		this.numberOfMines = numberOfBombs;
		this.coordinatesOfMines = new ArrayList<>();
		this.generateGrid();
	}
	
	public int getRows() {
		return this.rows;
	}
	
	public int getColumns() {
		return this.columns;
	}
	
	public int getNumberOfMines() {
		return numberOfMines;
	}
	
	public int getValueAt(int x, int y) {
		return grid[x][y];
	}
	
	public void setValueAt(int x, int y, int value) {
		this.grid[x][y] = value;
	}
	
	private void generateGrid() {
		List<Integer> positionOfBombs = new ArrayList<>();
		for (int i = 0; i < this.numberOfMines; i++) { positionOfBombs.add(1); }
		for (int i = this.numberOfMines; i < this.rows * this.columns; i++) { positionOfBombs.add(0); }
		Collections.shuffle(positionOfBombs);
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				this.setValueAt(i, j, positionOfBombs.get((i*this.columns) + j));
				if (positionOfBombs.get((i*this.columns) + j) == 1) {
					coordinatesOfMines.add(new Point(i, j));
				}
			}
		}
	}
	
	public int numberOfAdjacentMines(int x, int y) {
		int number = 0;
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (i >= 0 && i < this.getRows() &&
					j >= 0 && j < this.getColumns() &&
					!(i == x && j == y))	{
					number += this.getValueAt(i, j);
				}
			}
		}
		return number;
	}
	
	public List<Point> getCoordinatesOfMines(){
		return this.coordinatesOfMines;
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
