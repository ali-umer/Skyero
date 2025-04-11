package PassengerActivities.UILayer;

import RegistrationAndLogin.UILayer.MainFrame;
import Customisations.*;
import PassengerActivities.BusinessLayer.User;
import PassengerActivities.BusinessLayer.UserFactory;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DashboardPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private User PassengerObject;
	private JLabel NameLabel;
	private JLabel DOBLabel;
	private JLabel ContactLabel;
	private JLabel CNICLabel;
	private JLabel PassportLabel;
	private JLabel GenderLabel;
	
	
	public DashboardPanel(MainFrame frame,String Username) {
		setBackground(SystemColor.window);
		setPreferredSize(new Dimension(900, 520));
		setLayout(null);

		PassengerObject = UserFactory.getInstance().createUser("Passenger");
		
		JLabel HomeLabel = new JLabel("HOME");
		HomeLabel.setForeground(Color.BLACK);
		HomeLabel.setIcon(new ImageIcon("C:\\Users\\alium\\Desktop\\home.png"));
		HomeLabel.setFont(new Font("Arial Black", Font.BOLD, 35));
		HomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		HomeLabel.setBounds(360, 0, 176, 56);
		add(HomeLabel);

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
		CNICLabel.setBounds(360, 144, 200, 28);
		add(CNICLabel);

		PassportLabel = new JLabel("New label");
		PassportLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
		PassportLabel.setForeground(SystemColor.windowBorder);
		PassportLabel.setBounds(360, 208, 200, 28);
		add(PassportLabel);


		JLabel BorderLabel = new JLabel("");
		BorderLabel.setBounds(10, 54, 861, 219);
		add(BorderLabel);

		Border border = BorderFactory.createLineBorder(Color.WHITE, 5);       
		BorderLabel.setBorder(border);


		JLabel PersonalInformationLabel = new JLabel("PERSONAL INFORMATION");
		PersonalInformationLabel.setForeground(SystemColor.windowBorder);
		PersonalInformationLabel.setFont(new Font("Cambria Math", Font.BOLD, 22));
		PersonalInformationLabel.setBounds(24, 63, 290, 33);
		add(PersonalInformationLabel);


		PassengerObject.InteractWithDB(Username);
		setLabels();

		CustomisedButton LogOutButton = new CustomisedButton("LOG OUT");
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
		LogOutButton.setForeground(Color.WHITE);
		LogOutButton.addMouseListener(new CustomisedMouseListener(LogOutButton));
		LogOutButton.setBackground(Color.LIGHT_GRAY);
		LogOutButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		LogOutButton.setBounds(741, 12, 130, 37);
		add(LogOutButton);


		CustomisedButton MyBookingsButton = new CustomisedButton("MY BOOKINGS");
		MyBookingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyBookingsFrame Frame = new MyBookingsFrame(Username);
				Frame.setVisible(true);
			}
		});
		MyBookingsButton.setFont(new Font("Arial Black", Font.BOLD, 13));
		MyBookingsButton.setForeground(Color.WHITE);
		MyBookingsButton.setBackground(Color.LIGHT_GRAY);
		MyBookingsButton.setBounds(104, 351, 210, 50);
		MyBookingsButton.addMouseListener(new CustomisedMouseListener(MyBookingsButton));
		add(MyBookingsButton);


		CustomisedButton PaymentInformationLabel = new CustomisedButton("PAYMENT INFORMATION");
		PaymentInformationLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaymentInfoFrame Frame = new PaymentInfoFrame(Username);
				Frame.setVisible(true);
			}
		});
		PaymentInformationLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		PaymentInformationLabel.setForeground(Color.WHITE);
		PaymentInformationLabel.setBackground(Color.LIGHT_GRAY);
		PaymentInformationLabel.setBounds(570, 352, 210, 50);
		PaymentInformationLabel.addMouseListener(new CustomisedMouseListener(PaymentInformationLabel));
		add(PaymentInformationLabel);
		
		CustomisedButton EditProfileButton = new CustomisedButton("EDIT PROFILE");
		EditProfileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditProfileFrame Frame = new EditProfileFrame(DashboardPanel.this,Username);
				Frame.setVisible(true);	
			}
		});
		EditProfileButton.setForeground(Color.WHITE);
		EditProfileButton.setFont(new Font("Arial Black", Font.BOLD, 17));
		EditProfileButton.setBackground(Color.LIGHT_GRAY);
		EditProfileButton.setBounds(338, 350, 210, 50);
		EditProfileButton.addMouseListener(new CustomisedMouseListener(EditProfileButton));
		add(EditProfileButton);

		JLabel BackgroundLabel = new JLabel("");
		BackgroundLabel.setIcon(new ImageIcon("C:\\Users\\alium\\Desktop\\87cefa.png"));
		BackgroundLabel.setBounds(0, 0, 900, 520);
		add(BackgroundLabel);
	}
	
	public User getPassengerObject()
	{
		return this.PassengerObject;
	}
	
	public void setLabels()
	{
		NameLabel.setText("Name: " + PassengerObject.getUsername());
		ContactLabel.setText("Contact: " + PassengerObject.getContact());
		DOBLabel.setText("Date of Birth: " + PassengerObject.getDOB());
		GenderLabel.setText("Gender: " + PassengerObject.getGender());
		PassportLabel.setText("Passport: " + PassengerObject.getPassport());
		CNICLabel.setText("CNIC: " + PassengerObject.getCNIC());

	}
	
	
}
