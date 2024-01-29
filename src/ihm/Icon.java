package ihm;

import java.awt.Image;


import javax.swing.ImageIcon;

import components.CellJButton;
import minesweeper.GridView;

public class Icon {
	
	public static final ImageIcon UNDISCOVERED = Icon.getIcon("undiscovered");
	public static final ImageIcon FLAG = Icon.getIcon("flag");
	public static final ImageIcon MINE = Icon.getIcon("mine");
	public static final ImageIcon EMPTY = Icon.getIcon("empty");
	public static final ImageIcon ONE = Icon.getIcon("one");
	public static final ImageIcon TWO = Icon.getIcon("two");
	public static final ImageIcon THREE = Icon.getIcon("three");
	public static final ImageIcon FOUR = Icon.getIcon("four");
	public static final ImageIcon FIVE = Icon.getIcon("five");
	public static final ImageIcon SIX = Icon.getIcon("six");
	public static final ImageIcon SEVEN = Icon.getIcon("seven");
	public static final ImageIcon EIGHT = Icon.getIcon("eight");
	public static final ImageIcon TIMER = Icon.getIcon("timer", 25, 25);
	public static final ImageIcon APP_ICON = Icon.getIcon("appIcon");
	
	private static ImageIcon getIcon(String type) {
        ImageIcon originalIcon = new ImageIcon(CellJButton.class.getResource("/images/" + type + ".png"));
        return new ImageIcon(originalIcon.getImage().getScaledInstance(GridView.CELL_SIZE, GridView.CELL_SIZE, Image.SCALE_SMOOTH));
    }
	
	private static ImageIcon getIcon(String type, int xSize, int ySize) {
        ImageIcon originalIcon = new ImageIcon(CellJButton.class.getResource("/images/" + type + ".png"));
        return new ImageIcon(originalIcon.getImage().getScaledInstance(xSize, ySize, Image.SCALE_SMOOTH));
    }
}
