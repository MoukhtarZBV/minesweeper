package components;

import java.awt.Color;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import ihm.Icon;
import minesweeper.State;

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
	
	public State getState() {
		return this.state;
	}
	
	public void setState(State state) {
		this.state = state;
		switch (state) {
		case UNDISCOVERED:
			this.setIcon(Icon.UNDISCOVERED);
			break;
		case FLAG:
			this.setIcon(Icon.FLAG);
			break;
		case MINE:
			this.setIcon(Icon.MINE);
			break;
		case EMPTY:
			this.setIcon(Icon.EMPTY);
			break;
		case ONE:
			this.setIcon(Icon.ONE);
			break;
		case TWO:
			this.setIcon(Icon.TWO);
			break;
		case THREE:
			this.setIcon(Icon.THREE);
			break;
		case FOUR:
			this.setIcon(Icon.FOUR);
			break;
		case FIVE:
			this.setIcon(Icon.FIVE);
			break;
		case SIX:
			this.setIcon(Icon.SIX);
			break;
		case SEVEN:
			this.setIcon(Icon.SEVEN);
			break;
		case EIGHT:
			this.setIcon(Icon.EIGHT);
			break;
		}
		
	}
}
