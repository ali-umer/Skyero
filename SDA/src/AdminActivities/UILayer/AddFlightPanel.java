package AdminActivities.UILayer;

import Customisations.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import PassengerActivities.BusinessLayer.Flight;

public class AddFlightPanel extends JPanel {

	private Flight FlightObject;
	private static final long serialVersionUID = 1L;

	private JTextField FlightIdField;
	private JTextField DepartureField;
	private JTextField DestinationField;
	private JTextField DateField;
	private JTextField TimeField;
	private JTextField RateField;
	private JTextField SeatsField;

	private JLabel firstLabel;
	private JLabel secondLabel;
	private JLabel thirdLabel;
	private JLabel fourthLabel;
	private JLabel fifthLabel;
	private JLabel sixthLabel;
	private JLabel seventhLabel;
	private JLabel HeadingLabel;

	private CustomisedButton CloseButton;
	private CustomisedButton DoneButton;
	private JLabel BackgroundLabel;

	public AddFlightPanel(AddAndRemoveFlightPanel object,AddFlightFrame frame) {

		setBackground(SystemColor.window);
		setPreferredSize(new Dimension(900, 520));
		setLayout(null);

		FlightObject = new Flight();

		HeadingLabel = new JLabel("ADD FLIGHT");
		HeadingLabel.setForeground(SystemColor.windowBorder);
		HeadingLabel.setFont(new Font("Arial Black", Font.BOLD, 35));
		HeadingLabel.setBounds(335, 11, 269, 60);
		add(HeadingLabel);


		firstLabel = new JLabel("Flight ID:");
		firstLabel.setForeground(SystemColor.windowBorder);
		firstLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		firstLabel.setBounds(66, 109, 90, 20);
		add(firstLabel);

		secondLabel = new JLabel("Departure:");
		secondLabel.setForeground(SystemColor.windowBorder);
		secondLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		secondLabel.setBounds(467, 109, 90, 20);
		add(secondLabel);

		thirdLabel = new JLabel("Destination:");
		thirdLabel.setForeground(SystemColor.windowBorder);
		thirdLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		thirdLabel.setBounds(66, 160, 90, 20);
		add(thirdLabel);

		fourthLabel = new JLabel("Date:");
		fourthLabel.setForeground(SystemColor.windowBorder);
		fourthLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		fourthLabel.setBounds(467, 160, 90, 20);
		add(fourthLabel);

		fifthLabel = new JLabel("Time:");
		fifthLabel.setForeground(SystemColor.windowBorder);
		fifthLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		fifthLabel.setBounds(66, 212, 90, 20);
		add(fifthLabel);

		sixthLabel = new JLabel("Rate per Seat:");
		sixthLabel.setForeground(SystemColor.windowBorder);
		sixthLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		sixthLabel.setBounds(457, 212, 100, 20);
		add(sixthLabel);

		seventhLabel = new JLabel("Number of Seats:");
		seventhLabel.setForeground(SystemColor.windowBorder);
		seventhLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		seventhLabel.setBounds(220, 295, 121, 20);
		add(seventhLabel);

		RateField = new JTextField();
		RateField.setBounds(567, 212, 217, 20);
		add(RateField);
		RateField.setColumns(10);

		SeatsField = new JTextField();
		SeatsField.setBounds(362, 296, 217, 20);
		add(SeatsField);
		SeatsField.setColumns(10);

		FlightIdField = new JTextField();
		FlightIdField.setBounds(166, 110, 217, 20);
		add(FlightIdField);
		FlightIdField.setColumns(10);

		DepartureField = new JTextField();
		DepartureField.setColumns(10);
		DepartureField.setBounds(567, 110, 217, 20);
		add(DepartureField);

		DestinationField = new JTextField();
		DestinationField.setColumns(10);
		DestinationField.setBounds(166, 161, 217, 20);
		add(DestinationField);

		DateField = new JTextField();
		DateField.setColumns(10);
		DateField.setBounds(567, 161, 217, 20);
		add(DateField);

		TimeField = new JTextField();
		TimeField.setColumns(10);
		TimeField.setBounds(166, 212, 217, 20);
		add(TimeField);

		DoneButton = new CustomisedButton("DONE");
		DoneButton.setForeground(Color.WHITE);
		DoneButton.setBackground(Color.LIGHT_GRAY);
		DoneButton.setFont(new Font("Arial Black", Font.BOLD, 18));
		DoneButton.addMouseListener(new CustomisedMouseListener(DoneButton));

		DoneButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				boolean DateFlag = false;

				//check if date is correct to be saved in database and in correct format
				if ((isValidDateFormat(DateField.getText()))) {
					if ((isValidDate(DateField.getText()))) {
						DateFlag = true;
					}
				}

				if(DateFlag == false)
				{
					showMessageDialog("Wrong date or  format!\n Use yyyy-mm-dd format");		

				}

				if(DateFlag == true)
				{
					FlightObject.setFlightID(FlightIdField.getText());
					FlightObject.setDestination(DestinationField.getText());
					FlightObject.setDeparture(DepartureField.getText());
					FlightObject.setDate(DateField.getText());
					FlightObject.setTime(TimeField.getText());
					FlightObject.setNumberOfSeats(Integer.parseInt(SeatsField.getText()));
					FlightObject.setRatePerSeat(RateField.getText());

					boolean flag = FlightObject.InsertIntoDB();

					if (flag == true)
					{
						showMessageDialog("Flight Addition Successful!");
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
		DoneButton.setBounds(462, 394, 130, 45);
		add(DoneButton);



		CloseButton = new CustomisedButton("CLOSE");
		CloseButton.setForeground(Color.WHITE);
		CloseButton.addMouseListener(new CustomisedMouseListener(CloseButton));
		CloseButton.setBackground(Color.LIGHT_GRAY);
		CloseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		CloseButton.setFont(new Font("Arial Black", Font.BOLD, 18));
		CloseButton.setBounds(322, 394, 130, 45);
		add(CloseButton);

		BackgroundLabel = new JLabel("");
		BackgroundLabel.setIcon(new ImageIcon("C:\\Users\\alium\\Desktop\\87cefa.png"));
		BackgroundLabel.setBounds(0, 0, 900, 520);
		add(BackgroundLabel);
	}

	private static void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
	}

	private static boolean isValidDateFormat(String input) {//validate date format as yyyy-mm-dd
		String dateFormatPattern = "^\\d{4}-\\d{2}-\\d{2}$";
		Pattern pattern = Pattern.compile(dateFormatPattern);
		Matcher matcher = pattern.matcher(input);

		return matcher.matches();
	}

	private static boolean isValidDate(String input) { //validate that date entered is correct 
		int month = Integer.parseInt(input.substring(5, 7));
		int day = Integer.parseInt(input.substring(8, 10));

		return month >= 1 && month <= 12 && day >= 1 && day <= 31;
	}

}
