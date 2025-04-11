package RegistrationAndLogin.UILayer;

import javax.swing.*;
import java.awt.*;
import Customisations.*;
import RegistrationAndLogin.BusinessLayer.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField UsernameField;
	private JPasswordField PasswordField;
	private Login LoginObject;

	public LoginPanel(MainFrame frame) {

		setBackground(SystemColor.window);
		setPreferredSize(new Dimension(900, 520));
		setLayout(null);

		this.LoginObject = new Login();

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\alium\\Desktop\\book.png"));
		lblNewLabel.setBounds(644, 73, 64, 64);
		add(lblNewLabel);

		JLabel SkyeroLabel = new JLabel("SKYERO");
		SkyeroLabel.setForeground(Color.WHITE);
		SkyeroLabel.setFont(new Font("Arial Black", Font.BOLD, 40));
		SkyeroLabel.setHorizontalAlignment(SwingConstants.CENTER);
		SkyeroLabel.setBounds(557, 152, 236, 55);
		add(SkyeroLabel);

		JLabel lblNewLabel_2 = new JLabel("YOUR FLIGHTS, OUR PRIORITIES");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblNewLabel_2.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(560, 218, 225, 14);
		add(lblNewLabel_2);

		JLabel LoginLabel = new JLabel("Login");
		LoginLabel.setForeground(SystemColor.windowBorder);
		LoginLabel.setFont(new Font("Arial Black", Font.BOLD, 15));
		LoginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LoginLabel.setBounds(624, 243, 110, 38);
		add(LoginLabel);

		JLabel UsernameLabel = new JLabel("Username:");
		UsernameLabel.setForeground(SystemColor.windowBorder);
		UsernameLabel.setFont(new Font("Arial Black", Font.PLAIN, 13));
		UsernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		UsernameLabel.setBounds(463, 288, 81, 25);
		add(UsernameLabel);

		JLabel PasswordLabel = new JLabel("Password:");
		PasswordLabel.setForeground(SystemColor.windowBorder);
		PasswordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PasswordLabel.setFont(new Font("Arial Black", Font.PLAIN, 13));
		PasswordLabel.setBounds(463, 337, 81, 14);
		add(PasswordLabel);

		UsernameField = new JTextField();
		UsernameField.setBounds(554, 292, 236, 20);
		add(UsernameField);
		UsernameField.setColumns(10);

		PasswordField = new JPasswordField();
		PasswordField.setBounds(554, 336, 236, 20);
		add(PasswordField);

		CustomisedButton RegisterButton = new CustomisedButton("REGISTER");
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.switchToPanelB();
			}
		});
		RegisterButton.setForeground(Color.WHITE);
		RegisterButton.setBackground(Color.LIGHT_GRAY);
		RegisterButton.addMouseListener(new CustomisedMouseListener(RegisterButton));
		RegisterButton.setFont(new Font("Arial Black", Font.BOLD, 16));
		RegisterButton.setBounds(463, 385, 130, 40);
		add(RegisterButton);


		CustomisedButton LoginButton = new CustomisedButton("LOGIN");
		LoginButton.setForeground(Color.WHITE);
		LoginButton.setBackground(Color.LIGHT_GRAY);
		LoginButton.addMouseListener(new CustomisedMouseListener(LoginButton));

		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginObject.setUsername(UsernameField.getText());
				LoginObject.setPassword(new String(PasswordField.getPassword()));
				String Role = LoginObject.InteractWithDB();

				if(Role.equals("Passenger"))
				{
					showMessageDialog("Login Successful!");
					frame.switchToPanelC();
				}
				else if(Role.equals("Admin"))
				{
					showMessageDialog("Login Successful!");
					frame.switchToPanelD();
				}	
				else
				{
					showMessageDialog("Login Failed!");
				}


			}

		});
		LoginButton.setFont(new Font("Arial Black", Font.BOLD, 16));
		LoginButton.setBounds(663, 385, 130, 40);
		add(LoginButton);

		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\alium\\Desktop\\login_image.png"));
		lblNewLabel_6.setBounds(0, 0, 419, 450);
		add(lblNewLabel_6);

		JLabel SloganLabel = new JLabel("");
		SloganLabel.setIcon(new ImageIcon("C:\\Users\\alium\\Desktop\\87cefa.png"));
		SloganLabel.setBackground(new Color(51, 153, 255));
		SloganLabel.setBounds(429, 0, 471, 520);
		add(SloganLabel);

	}

	public void resetCredentials()
	{
		UsernameField.setText("");
		PasswordField.setText("");
	}

	public String getUsernameField()
	{
		return UsernameField.getText();
	}

	private static void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
	}


}
