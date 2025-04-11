package AdminActivities.UILayer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import Customisations.*;
import PassengerActivities.BusinessLayer.Flight;

public class RescheduleFlightPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField DateField;
	private JTextField TimeField;
	private Flight FlightObject;

	public RescheduleFlightPanel(FlightReschedulingPanel object,RescheduleFlightFrame frame,String FlightID) {

		setBackground(SystemColor.window);
		setPreferredSize(new Dimension(900, 520));
		setLayout(null);

		FlightObject = new Flight();

		JLabel HeadingLabel = new JLabel("RESCHEDULE FLIGHTS");
		HeadingLabel.setIcon(new ImageIcon("C:\\Users\\alium\\Desktop\\rescheduling.png"));
		HeadingLabel.setForeground(SystemColor.windowBorder);
		HeadingLabel.setFont(new Font("Arial Black", Font.BOLD, 32));
		HeadingLabel.setBounds(238, 11, 483, 65);
		add(HeadingLabel);

		JLabel firstLabel = new JLabel("Enter new date:");
		firstLabel.setForeground(SystemColor.windowBorder);
		firstLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		firstLabel.setBounds(238, 159, 114, 23);
		add(firstLabel);

		JLabel secondLabel = new JLabel("Enter new time:");
		secondLabel.setForeground(SystemColor.windowBorder);
		secondLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		secondLabel.setBounds(238, 242, 114, 23);
		add(secondLabel);

		DateField = new JTextField();
		DateField.setBounds(377, 157, 294, 25);
		add(DateField);
		DateField.setColumns(10);

		TimeField = new JTextField();
		TimeField.setBounds(377, 240, 294, 25);
		add(TimeField);
		TimeField.setColumns(10);

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
		CloseButton.setBounds(377, 375, 130, 45);
		add(CloseButton);

		CustomisedButton DoneButton = new CustomisedButton("DONE");
		DoneButton.setForeground(Color.WHITE);
		DoneButton.setBackground(Color.LIGHT_GRAY);
		DoneButton.addMouseListener(new CustomisedMouseListener(DoneButton));
		DoneButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(
						frame,
						"Are you sure you want to Reschedule this flight?",
						"Flight Rescheduling Confirmation",
						JOptionPane.YES_NO_OPTION
						);

				if (result == JOptionPane.YES_OPTION) {

					boolean DateFlag = false;

					if ((isValidDateFormat(DateField.getText()))) {
						if ((isValidDate(DateField.getText()))) {
							DateFlag = true;
						}
					}

					if (DateFlag == false)
					{
						showMessageDialog("Wrong date or  format!\n Use yyyy-mm-dd format");		

					}

					if(DateFlag == true)
					{

						FlightObject.setFlightID(FlightID);
						FlightObject.setDate(DateField.getText());
						FlightObject.setTime(TimeField.getText());

						boolean flag = FlightObject.UpdateFlight();

						if(flag == true)
						{
							showMessageDialog("Flight Rescheduling Successful!");
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
			}
		});
		DoneButton.setFont(new Font("Arial Black", Font.BOLD, 18));
		DoneButton.setBounds(541, 375, 130, 45);
		add(DoneButton);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\alium\\Desktop\\87cefa.png"));
		lblNewLabel.setBounds(0, 0, 900, 520);
		add(lblNewLabel);



	}

	private static void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
	}

	private static boolean isValidDateFormat(String input) {
		String dateFormatPattern = "^\\d{4}-\\d{2}-\\d{2}$";
		Pattern pattern = Pattern.compile(dateFormatPattern);
		Matcher matcher = pattern.matcher(input);

		return matcher.matches();
	}

	private static boolean isValidDate(String input) {
		int month = Integer.parseInt(input.substring(5, 7));
		int day = Integer.parseInt(input.substring(8, 10));

		return month >= 1 && month <= 12 && day >= 1 && day <= 31;
	}

}
