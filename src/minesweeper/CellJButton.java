package minesweeper;

import java.awt.Color;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CellJButton extends JButton {

	private int row;
	private int column;
	private State state;
	
	public CellJButton(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getColumn() {
		return this.column;
	}
	
	public void setState(State state) {
		this.state = state;
		switch (state) {
		case FLAG:
			ImageIcon icon = new ImageIcon(CellJButton.class.getResource("/images/flag.png"));
			Image img = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
			icon = new ImageIcon(img);
			this.setIcon(icon);
			break;
		case BOMB:
		}
		
	}
}
