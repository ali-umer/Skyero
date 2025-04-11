package Customisations;

import java.awt.*;
import javax.swing.*;

public class CustomisedButton extends JButton {

	private static final long serialVersionUID = 1L;

	public CustomisedButton(String label) {
		super(label);
		setFocusPainted(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (getModel().isArmed()) {
			g.setColor(Color.lightGray);
		} else {
			g.setColor(getBackground());
		}

		g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 35, 35);

		super.paintComponent(g);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(100, 40); 
	}
}
