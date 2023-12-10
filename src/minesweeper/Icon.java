package minesweeper;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Icon {
	
	public static final ImageIcon FLAG = Icon.getIcon("flag");
	public static final ImageIcon MINE = Icon.getIcon("mine");
	
	private static ImageIcon getIcon(String type) {
		ImageIcon icon = new ImageIcon(CellJButton.class.getResource("/images/" + type + ".png"));
		Image img = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		return new ImageIcon(img);
	}
}
