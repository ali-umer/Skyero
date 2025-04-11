package PassengerActivities.UILayer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Customisations.CustomisedButton;
import Customisations.CustomisedMouseListener;
import DatabaseLayer.DatabaseFactory;
import PassengerActivities.BusinessLayer.User;
import PassengerActivities.BusinessLayer.UserFactory;

public class EditProfilePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private CustomisedButton CloseButton;
	private CustomisedButton UpdateButton;
	private JLabel BackgroundLabel;
	private JTextField DOBField;
	private JTextField GenderField;
	private JTextField CNICField;
	private JTextField ContactField;
	private User PassengerObject;
	private JLabel PassportLabel;
	private JTextField PassportField;

	public EditProfilePanel(DashboardPanel object,EditProfileFrame frame,String UserID) {

		setBackground(SystemColor.window);
		setPreferredSize(new Dimension(900, 520));
		setLayout(null);

		PassengerObject = UserFactory.getInstance().createUser("Passenger");
		PassengerObject.InteractWithDB(UserID);

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
		CloseButton.setBounds(297, 407, 130, 45);
		add(CloseButton);

		UpdateButton = new CustomisedButton("DONE");
		UpdateButton.setText("UPDATE");
		UpdateButton.setForeground(Color.WHITE);
		UpdateButton.setBackground(Color.LIGHT_GRAY);
		UpdateButton.setFont(new Font("Arial Black", Font.BOLD, 18));
		UpdateButton.addMouseListener(new CustomisedMouseListener(UpdateButton));

		UpdateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean temp = true;
				boolean GenderFlag = false;
				boolean DateFlag = false;

				if ((isValidDateFormat(DOBField.getText()))) {
					if ((isValidDate(DOBField.getText()))) {
						DateFlag = true;
					}
				}

				if(DateFlag == false)
				{
					showMessageDialog("Wrong date or  format!\n Use yyyy-mm-dd format");		

				}

				if(DateFlag == true)
				{
					LocalDate dob = LocalDate.parse(DOBField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

					if(LocalDate.now().isBefore(dob))
					{
						temp = false;
						showMessageDialog("Wrong date entered!");
					}
				}

				if ("Female".equalsIgnoreCase(GenderField.getText()) || "Male".equalsIgnoreCase(GenderField.getText())) {
					GenderFlag = true;
				}

				if(GenderFlag == false)
				{
					showMessageDialog("Wrong Gender entered!");

				}

				if(CNICField.getText().length() != 13)
				{
					temp = false;
					showMessageDialog("CNIC should be of 13 characters!");
				}

				if(ContactField.getText().length() != 11)
				{
					temp = false;
					showMessageDialog("Phone number should be of 11 characters!");
				}

				if(PassportField.getText().length() != 9)
				{
					temp = false;
					showMessageDialog("Passport number should be of 9 characters!");
				}

				if(temp == true && DateFlag == true && GenderFlag == true)
				{

					PassengerObject.setCNIC(CNICField.getText());
					PassengerObject.setContact(ContactField.getText());
					PassengerObject.setDOB(DOBField.getText());
					PassengerObject.setGender(GenderField.getText());
					PassengerObject.setUsername(UserID);
					PassengerObject.setPassport(PassportField.getText());

					boolean flag = DatabaseFactory.getInstance().UpdatePassenger(PassengerObject);
					if(flag == true)
					{
						object.getPassengerObject().InteractWithDB(UserID);
						object.setLabels();
						showMessageDialog("Updated Successfully!");
						frame.dispose();
					}

					else
					{
						showMessageDialog("An error occured");
					}
				}
			}
		});
		UpdateButton.setBounds(462, 407, 130, 45);
		add(UpdateButton);

		JLabel HeadingLabel = new JLabel("EDIT PROFILE");
		HeadingLabel.setForeground(SystemColor.windowBorder);
		HeadingLabel.setFont(new Font("Arial Black", Font.BOLD, 35));
		HeadingLabel.setBounds(309, 0, 306, 60);
		add(HeadingLabel);

		JLabel DOBLabel = new JLabel("DOB:");
		DOBLabel.setForeground(SystemColor.windowBorder);
		DOBLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
		DOBLabel.setBounds(36, 192, 85, 20);
		add(DOBLabel);

		JLabel GenderLabel = new JLabel("Gender:");
		GenderLabel.setForeground(SystemColor.windowBorder);
		GenderLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
		GenderLabel.setBounds(36, 270, 85, 20);
		add(GenderLabel);

		JLabel CNICLabel = new JLabel("CNIC:");
		CNICLabel.setForeground(SystemColor.windowBorder);
		CNICLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
		CNICLabel.setBounds(465, 192, 85, 20);
		add(CNICLabel);

		JLabel ContactLabel = new JLabel("Contact:");
		ContactLabel.setForeground(SystemColor.windowBorder);
		ContactLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
		ContactLabel.setBounds(465, 270, 85, 20);
		add(ContactLabel);

		DOBField = new JTextField();
		DOBField.setBounds(129, 189, 200, 20);
		add(DOBField);
		DOBField.setColumns(10);

		GenderField = new JTextField();
		GenderField.setBounds(129, 267, 200, 20);
		add(GenderField);
		GenderField.setColumns(10);

		CNICField = new JTextField();
		CNICField.setBounds(554, 189, 200, 20);
		add(CNICField);
		CNICField.setColumns(10);

		ContactField = new JTextField();
		ContactField.setBounds(554, 267, 200, 20);
		add(ContactField);
		ContactField.setColumns(10);

		PassportLabel = new JLabel("Passport:");
		PassportLabel.setForeground(SystemColor.windowBorder);
		PassportLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
		PassportLabel.setBounds(297, 343, 85, 20);
		add(PassportLabel);

		PassportField = new JTextField();
		PassportField.setBounds(392, 345, 200, 20);
		add(PassportField);
		PassportField.setColumns(10);

		CNICField.setText(PassengerObject.getCNIC());
		ContactField.setText(PassengerObject.getContact());
		GenderField.setText(PassengerObject.getGender());
		DOBField.setText(PassengerObject.getDOB());
		PassportField.setText(PassengerObject.getPassport());

		BackgroundLabel = new JLabel("");
		BackgroundLabel.setIcon(new ImageIcon("C:\\Users\\alium\\Desktop\\87cefa.png"));
		BackgroundLabel.setBounds(0, 0, 900, 520);
		add(BackgroundLabel);



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
