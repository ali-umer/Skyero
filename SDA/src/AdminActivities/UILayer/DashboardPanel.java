package AdminActivities.UILayer;

import Customisations.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import RegistrationAndLogin.UILayer.MainFrame;
import PassengerActivities.BusinessLayer.User;
import PassengerActivities.BusinessLayer.UserFactory;

public class DashboardPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private User AdminObject;
	private JLabel NameLabel;
	private JLabel GenderLabel;
	private JLabel DOBLabel;
	private JLabel ContactLabel;
	private JLabel CNICLabel;

	public DashboardPanel(MainFrame frame,String Username) {
		setBackground(SystemColor.window);
		setPreferredSize(new Dimension(900, 520));
		setLayout(null);

		AdminObject = UserFactory.getInstance().createUser("Admin");

		JLabel HeadingLabel = new JLabel("HOME");
		HeadingLabel.setForeground(Color.BLACK);
		HeadingLabel.setIcon(new ImageIcon("C:\\Users\\alium\\Desktop\\home.png"));
		HeadingLabel.setFont(new Font("Arial Black", Font.BOLD, 35));
		HeadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		HeadingLabel.setBounds(349, -5, 193, 56);
		add(HeadingLabel);

		NameLabel = new JLabel("New label");
		NameLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
		NameLabel.setForeground(SystemColor.windowBorder);
		NameLabel.setBounds(24, 144, 200, 28);
		add(NameLabel);

		GenderLabel = new JLabel("New label");
		GenderLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
		GenderLabel.setForeground(SystemColor.windowBorder);
		GenderLabel.setBounds(24, 208, 200, 28);
		add(GenderLabel);

		DOBLabel = new JLabel("New label");
		DOBLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
		DOBLabel.setForeground(SystemColor.windowBorder);
		DOBLabel.setBounds(650, 144, 200, 28);
		add(DOBLabel);

		ContactLabel = new JLabel("New label");
		ContactLabel.setForeground(SystemColor.windowBorder);
		ContactLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
		ContactLabel.setBounds(650, 208, 200, 28);
		add(ContactLabel);

		CNICLabel = new JLabel("New label");
		CNICLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
		CNICLabel.setForeground(SystemColor.windowBorder);
		CNICLabel.setBounds(363, 176, 200, 28);
		add(CNICLabel);


		JLabel backLabel = new JLabel("");
		backLabel.setBounds(10, 54, 861, 219);
		add(backLabel);

		Border border = BorderFactory.createLineBorder(Color.WHITE, 5);      
		backLabel.setBorder(border);

		AdminObject.InteractWithDB(Username);
		setLabels();

		CustomisedButton LogOutButton = new CustomisedButton("LOG OUT");
		LogOutButton.setForeground(Color.WHITE);
		LogOutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(
						frame,
						"Are you sure you want to log out?",
						"Log Out Confirmation",
						JOptionPane.YES_NO_OPTION
						);

				if (result == JOptionPane.YES_OPTION) {
					frame.switchToPanelA();

				}

			}
		});
		LogOutButton.addMouseListener(new CustomisedMouseListener(LogOutButton));
		LogOutButton.setBackground(Color.LIGHT_GRAY);
		LogOutButton.setFont(new Font("Arial Black", Font.BOLD, 18));
		LogOutButton.setBounds(741, 12, 130, 37);
		add(LogOutButton);


		JLabel lblNewLabel_2 = new JLabel("PERSONAL INFORMATION");
		lblNewLabel_2.setForeground(SystemColor.windowBorder);
		lblNewLabel_2.setFont(new Font("Cambria Math", Font.BOLD, 22));
		lblNewLabel_2.setBounds(24, 63, 290, 33);
		add(lblNewLabel_2);

		CustomisedButton EditProfileButton = new CustomisedButton("EDIT PROFILE");
		EditProfileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditProfileFrame Frame = new EditProfileFrame(DashboardPanel.this,Username);
				Frame.setVisible(true);	
			}
		});
		EditProfileButton.setBackground(Color.LIGHT_GRAY);
		EditProfileButton.setForeground(Color.WHITE);
		EditProfileButton.setFont(new Font("Arial Black", Font.BOLD, 17));
		EditProfileButton.setBounds(367, 351, 180, 45);
		EditProfileButton.addMouseListener(new CustomisedMouseListener(EditProfileButton));
		add(EditProfileButton);

		JLabel backgroundLabel = new JLabel("");
		backgroundLabel.setIcon(new ImageIcon("C:\\Users\\alium\\Desktop\\87cefa.png"));
		backgroundLabel.setBounds(0, 0, 900, 520);
		add(backgroundLabel);

	}

	public User getAdminObject()
	{
		return this.AdminObject;
	}

	public void setLabels()
	{
		NameLabel.setText("Name: " + AdminObject.getUsername());
		GenderLabel.setText("Gender: " + AdminObject.getGender());
		CNICLabel.setText("CNIC: " + AdminObject.getCNIC());
		ContactLabel.setText("Contact: " + AdminObject.getContact());
		DOBLabel.setText("Date of Birth: " + AdminObject.getDOB());


	}
}
