package AdminActivities.UILayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Customisations.*;
import PassengerActivities.BusinessLayer.Flight;

public class RemoveFlightPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Flight FlightObject;

	public RemoveFlightPanel(AddAndRemoveFlightPanel object,RemoveFlightFrame frame) {

		setBackground(SystemColor.window);
		setPreferredSize(new Dimension(900, 520));
		setLayout(null);

		FlightObject = new Flight();

		JLabel HeadingLabel = new JLabel("REMOVE FLIGHT");
		HeadingLabel.setForeground(SystemColor.windowBorder);
		HeadingLabel.setFont(new Font("Arial Black", Font.BOLD, 35));
		HeadingLabel.setBounds(301, 11, 362, 64);
		add(HeadingLabel);

		JLabel firstLabel = new JLabel("Enter ID of Flight to remove:");
		firstLabel.setForeground(SystemColor.windowBorder);
		firstLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		firstLabel.setBounds(167, 234, 197, 30);
		add(firstLabel);

		JTextField FlightIDField = new JTextField();
		FlightIDField.setBounds(401, 238, 296, 25);
		add(FlightIDField);
		FlightIDField.setColumns(10);

		CustomisedButton DoneButton = new CustomisedButton("DONE");
		DoneButton.setForeground(Color.WHITE);
		DoneButton.setBackground(Color.LIGHT_GRAY);
		DoneButton.setFont(new Font("Arial Black", Font.BOLD, 18));
		DoneButton.addMouseListener(new CustomisedMouseListener(DoneButton));

		DoneButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(
						frame,
						"Are you sure you want to Remove this flight?",
						"Flight Removal Confirmation",
						JOptionPane.YES_NO_OPTION
						);

				if (result == JOptionPane.YES_OPTION) {

					FlightObject.setFlightID(FlightIDField.getText());

					boolean flag = FlightObject.RemoveFlight();

					if(flag == true)
					{
						showMessageDialog("Flight Removal Successful!");
						object.LoadData();
						frame.dispose();
					}
					else
					{
						showMessageDialog("An error occured!");
						frame.dispose();
					}
				}


			}
		});
		DoneButton.setBounds(557, 393, 130, 45);
		add(DoneButton);

		CustomisedButton CloseButton = new CustomisedButton("CLOSE");
		CloseButton.setForeground(Color.WHITE);
		CloseButton.setBackground(Color.LIGHT_GRAY);
		CloseButton.addMouseListener(new CustomisedMouseListener(CloseButton));
		CloseButton.setFont(new Font("Arial Black", Font.BOLD, 18));
		CloseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		CloseButton.setBounds(338, 393, 130, 45);
		add(CloseButton);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\alium\\Desktop\\87cefa.png"));
		lblNewLabel_2.setBounds(0, 0, 900, 520);
		add(lblNewLabel_2);

	}
	private static void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
	}

}
