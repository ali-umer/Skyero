package Customisations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomisedMouseListener extends MouseAdapter {

	 private final JButton button;

	    public CustomisedMouseListener(JButton button) {
	        this.button = button;
	    }

	    @Override
	    public void mouseEntered(MouseEvent e) {
	        button.setBackground(Color.darkGray);
	    }

	    @Override
	    public void mouseExited(MouseEvent e) {
	        button.setBackground(Color.LIGHT_GRAY);
	    }
}
