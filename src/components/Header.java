package components;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ihm.CustomColor;
import ihm.Icon;
import ihm.Screen;


public class Header extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private int xDrag;
	private int yDrag;
	
	private boolean fullScreen;
	
	private JLabel titre;
	
	public Header(JFrame parent) {
		
		fullScreen = false;
		
		setBounds(0, 0, 1280, 30);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setLayout(new BorderLayout(0, 0));
		setBackground(CustomColor.GRAY);
		
		
		// ============================== //
		// [Left side (Logo + title) ]
		JPanel leading = new JPanel();
		leading.setLayout(new BorderLayout(10, 0));
		leading.setBorder(new EmptyBorder(2, 2, 2, 2));
		leading.setOpaque(false);
		add(leading, BorderLayout.CENTER);
		
		JLabel logo = new JLabel("");
		logo.setIcon(Icon.APP_ICON);
		logo.setOpaque(false);
		leading.add(logo, BorderLayout.WEST);
		
		titre = new JLabel("Title");
		titre.setFont(new Font("Arial", Font.PLAIN, 18));
		titre.setForeground(CustomColor.WHITE);
		titre.setOpaque(false);
		leading.add(titre, BorderLayout.CENTER);
		
		
		// ============================== //
		// [Right side (Buttons) ]
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1, 2));
		buttons.setOpaque(false);
		add(buttons, BorderLayout.EAST);
		
		JButton btnReduce = new JButton("─");
		btnReduce.setBackground(CustomColor.GRAY);
		btnReduce.setForeground(CustomColor.WHITE);
		btnReduce.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnReduce.setPreferredSize(new Dimension(50, 25));
		btnReduce.setFocusable(false);
		btnReduce.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.setState(JFrame.ICONIFIED);
			}
		});
		btnReduce.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(MouseEvent e) {
				JButton button = (JButton) e.getSource();
				button.setBackground(CustomColor.LIGHT_GRAY);
				button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				JButton button = (JButton) e.getSource();
				button.setBackground(CustomColor.GRAY);
				button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			public void mouseClicked(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		buttons.add(btnReduce);
		
		JButton btnFullScreen = new JButton("■");
		btnFullScreen.setBackground(CustomColor.LIGHT_GRAY);
		btnFullScreen.setForeground(CustomColor.WHITE);
		btnFullScreen.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnFullScreen.setPreferredSize(new Dimension(50, 25));
		btnFullScreen.setFocusable(false);
		btnFullScreen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (fullScreen) {
					parent.setBounds(Screen.posX, Screen.posY, Screen.tailleX, Screen.tailleY);
					fullScreen = false;
				} else {
					parent.setExtendedState(JFrame.MAXIMIZED_BOTH);
					fullScreen = true;
				}
			}
		});
		btnFullScreen.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(MouseEvent e) {
				JButton button = (JButton) e.getSource();
				button.setBackground(CustomColor.LIGHT_GRAY.brighter());
				button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				JButton button = (JButton) e.getSource();
				button.setBackground(CustomColor.LIGHT_GRAY);
				button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			public void mouseClicked(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		buttons.add(btnFullScreen);
		
		JButton btnClose = new JButton("✕");
		btnClose.setBackground(CustomColor.DARK_GRAY);
		btnClose.setForeground(CustomColor.WHITE);
		btnClose.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnClose.setPreferredSize(new Dimension(50, 25));
		btnClose.setFocusable(false);
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.dispose();
			}
		});
		btnClose.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(MouseEvent e) {
				JButton button = (JButton) e.getSource();
				button.setBackground(CustomColor.RED.darker());
				button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				JButton button = (JButton) e.getSource();
				button.setBackground(CustomColor.DARK_GRAY);
				button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			public void mouseClicked(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		buttons.add(btnClose);
		
		 
		// ============================== //
		// [Dragging the header]
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xDrag = e.getXOnScreen() - parent.getX();
			    yDrag = e.getYOnScreen() - parent.getY();
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				parent.setLocation(e.getXOnScreen()-xDrag, e.getYOnScreen()-yDrag);
			}
		});
		
	}
	
	public void setTitle(String titre) {
		this.titre.setText(titre);
	}
	
}