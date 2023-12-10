package minesweeper;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Icon {
	
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
	
	private static ImageIcon getIcon(String type) {
		ImageIcon icon = new ImageIcon(CellJButton.class.getResource("/images/" + type + ".png"));
		Image img = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		return new ImageIcon(img);
	}
}
