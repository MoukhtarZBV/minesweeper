package components;

import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import ihm.CustomColor;
import minesweeper.GridController;

public class CustomComponent {

	// ============================== //
	// [Borders]
	public static final LineBorder PURPLE_BORDER = new LineBorder(CustomColor.PURPLE);
	
	public static final LineBorder YELLOW_BORDER = new LineBorder(CustomColor.YELLOW);
	
	public static final EmptyBorder PADDING_BORDER = new EmptyBorder(5, 10, 5, 10);
	
	public static final CompoundBorder BUTTON_BORDER = new CompoundBorder(PURPLE_BORDER, PADDING_BORDER);
	
	// ============================== //
	// [Components]
	public static JButton createButton(String name, GridController controller) {
		JButton button = new JButton(name);
		button.setForeground(CustomColor.YELLOW);
		button.setBackground(CustomColor.DARK_GRAY.darker());
		button.setBorder(CustomComponent.BUTTON_BORDER);
		button.setFocusable(false);
		button.addActionListener(controller);
		button.addMouseListener(controller);
		return button;
	}
	
	public static JSlider createJSlider(String name, int minimum, int maximum, int value, GridController controller) {
		JSlider slider = new JSlider() {
			private static final long serialVersionUID = 1L;

			@Override
            public void updateUI() {
                setUI(new CustomJSliderUI(this));
            }
        };
        slider.setName(name);
        slider.setMinimum(minimum);
        slider.setMaximum(maximum);
        slider.setValue(value);
        slider.setBackground(CustomColor.DARK_GRAY);
        slider.addChangeListener(controller);
        return slider;
	}
	
	public static JSpinner createJSpinner(String name, int minimum, int maximum, int value, GridController controller) {
		JSpinner spinner = new JSpinner();
		spinner.setName(name);
		spinner.setModel(new SpinnerNumberModel(value, minimum, maximum, 1));
		spinner.setBorder(CustomComponent.PURPLE_BORDER);
		spinner.setBackground(CustomColor.DARK_GRAY);
		spinner.getEditor().setBackground(CustomColor.DARK_GRAY);
		spinner.addChangeListener(controller);
		JTextField spinnerField = (JTextField) spinner.getEditor().getComponent(0);
		spinnerField.setBackground(CustomColor.DARK_GRAY);
		spinnerField.setForeground(CustomColor.YELLOW);
		spinnerField.setBorder(CustomComponent.PURPLE_BORDER);
		spinnerField.setBorder(new CompoundBorder(CustomComponent.PURPLE_BORDER, new EmptyBorder(0, 0, 0, 5)));
		JButton spinnerButton = (JButton) spinner.getComponent(0);
		spinnerButton.setBorder(CustomComponent.PURPLE_BORDER);
		spinnerButton.setBackground(CustomColor.DARK_GRAY);
		spinnerButton = (JButton) spinner.getComponent(1);
		spinnerButton.setBorder(CustomComponent.PURPLE_BORDER);
		spinnerButton.setBackground(CustomColor.DARK_GRAY);
		return spinner;
	}
}
