package RegistrationAndLogin.UILayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Customisations.*;
import RegistrationAndLogin.BusinessLayer.Registration;

public class RegistrationPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField UsernameField;
	private JTextField DOBField;
	private JTextField CNICField;
	private JTextField ContactField;
	private JTextField PassportField;
	private JTextField PasswordField;
	private JTextField GenderField;
	private Registration RegistrationObject;

	public RegistrationPanel(MainFrame frame) {
		setBackground(SystemColor.window);
		setPreferredSize(new Dimension(900, 520));
		setLayout(null); 

		RegistrationObject = new Registration();

		JLabel HeadingLabel = new JLabel("REGISTRATION FORM");
		HeadingLabel.setForeground(SystemColor.windowBorder);
		HeadingLabel.setFont(new Font("Arial Black", Font.BOLD, 35));
		HeadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		HeadingLabel.setBounds(210, 25, 474, 42);
		add(HeadingLabel);

		CustomisedButton BackButton = new CustomisedButton("BACK");
		BackButton.setFont(new Font("Arial Black", Font.BOLD, 18));
		BackButton.setBackground(Color.LIGHT_GRAY);
		BackButton.setForeground(Color.WHITE);
		BackButton.addMouseListener(new CustomisedMouseListener(BackButton));
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.switchToPanelA();
			}
		});
		BackButton.setBounds(10, 11, 130, 40);
		add(BackButton);

		UsernameField = new JTextField();
		UsernameField.setBounds(136, 120, 250, 20);
		add(UsernameField);
		UsernameField.setColumns(10);

		DOBField = new JTextField();
		DOBField.setBounds(136, 170, 250, 20);
		add(DOBField);
		DOBField.setColumns(10);

		CNICField = new JTextField();
		CNICField.setBounds(136, 220, 250, 20);
		add(CNICField);
		CNICField.setColumns(10);

		ContactField = new JTextField();
		ContactField.setBounds(592, 220, 250, 20);
		add(ContactField);
		ContactField.setColumns(10);

		PassportField = new JTextField();
		PassportField.setBounds(310, 312, 336, 20);
		add(PassportField);
		PassportField.setColumns(10);

		PasswordField = new JTextField();
		PasswordField.setBounds(592, 120, 250, 20);
		add(PasswordField);
		PasswordField.setColumns(10);

		GenderField = new JTextField();
		GenderField.setBounds(592, 170, 250, 20);
		add(GenderField);
		GenderField.setColumns(10);

		JLabel UsernameLabel = new JLabel("Username:");
		UsernameLabel.setForeground(SystemColor.windowBorder);
		UsernameLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		UsernameLabel.setBounds(37, 120, 89, 20);
		add(UsernameLabel);

		JLabel PasswordLabel = new JLabel("Password:");
		PasswordLabel.setForeground(SystemColor.windowBorder);
		PasswordLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		PasswordLabel.setBounds(493, 120, 89, 20);
		add(PasswordLabel);

		JLabel DOBLabel = new JLabel("DOB:");
		DOBLabel.setForeground(SystemColor.windowBorder);
		DOBLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		DOBLabel.setBounds(37, 170, 89, 20);
		add(DOBLabel);

		JLabel GenderLabel = new JLabel("Gender:");
		GenderLabel.setForeground(SystemColor.windowBorder);
		GenderLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		GenderLabel.setBounds(493, 170, 89, 20);
		add(GenderLabel);

		JLabel CNICLabel = new JLabel("CNIC:");
		CNICLabel.setForeground(SystemColor.windowBorder);
		CNICLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		CNICLabel.setBounds(37, 220, 89, 20);
		add(CNICLabel);

		JLabel ContactLabel = new JLabel("Contact:");
		ContactLabel.setForeground(SystemColor.windowBorder);
		ContactLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		ContactLabel.setBounds(493, 220, 89, 20);
		add(ContactLabel);

		JLabel PassportLabel = new JLabel("Passport:");
		PassportLabel.setForeground(SystemColor.windowBorder);
		PassportLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		PassportLabel.setBounds(211, 311, 89, 20);
		add(PassportLabel);

		CustomisedButton RegisterButton = new CustomisedButton("REGISTER");
		RegisterButton.setBackground(Color.LIGHT_GRAY);
		RegisterButton.setForeground(Color.WHITE);
		RegisterButton.addMouseListener(new CustomisedMouseListener(RegisterButton));
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean temp = true;
				boolean GenderFlag = false;
				boolean DateFlag = false;
				
				if(UsernameField.getText().length() == 0) {
					temp = false;
					showMessageDialog("Username cannot be empty!");

				}
				
				if(PasswordField.getText().length() < 8) {
					temp = false;
					showMessageDialog("Password should be greater than 8 characters!");

				}
				
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
					RegistrationObject.SetUsername(UsernameField.getText());
					RegistrationObject.SetContact(ContactField.getText());
					RegistrationObject.SetDOB(DOBField.getText());
					RegistrationObject.SetGender(GenderField.getText());
					RegistrationObject.SetPassport(PassportField.getText());
					RegistrationObject.SetPassword(PasswordField.getText());
					RegistrationObject.SetCNIC(CNICField.getText());

					boolean flag = RegistrationObject.InsertIntoDB();

					if(flag == true)
					{
						showMessageDialog("Registration Successful!");
						frame.switchToPanelA();
					}
					else
					{
						showMessageDialog("An error occured!");
					}
				}

			}
		});
		RegisterButton.setFont(new Font("Arial Black", Font.BOLD, 16));
		RegisterButton.setBounds(372, 394, 170, 45);
		add(RegisterButton);

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
