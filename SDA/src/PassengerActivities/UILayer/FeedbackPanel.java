package PassengerActivities.UILayer;

import Customisations.*;
import PassengerActivities.BusinessLayer.Feedback;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import RegistrationAndLogin.UILayer.MainFrame;
import java.sql.*;
import java.util.Enumeration;

public class FeedbackPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboBox = new JComboBox<String>();
	private JTextField CommentField;
	private Feedback FeedbackObject;
	
	public FeedbackPanel(MainFrame frame,String Username) {
		
		setBackground(SystemColor.window);
		setPreferredSize(new Dimension(900, 520));
		setLayout(null);
		
		FeedbackObject = new Feedback();

		JLabel HeadingLabel = new JLabel("FEEDBACK");
		HeadingLabel.setIcon(new ImageIcon("C:\\Users\\alium\\Desktop\\feedback.png"));
		HeadingLabel.setForeground(SystemColor.windowBorder);
		HeadingLabel.setFont(new Font("Arial Black", Font.BOLD, 30));
		HeadingLabel.setBounds(346, 11, 239, 63);
		add(HeadingLabel);
		
		JLabel firstLabel = new JLabel("Select Flight:");
		firstLabel.setForeground(SystemColor.windowBorder);
		firstLabel.setHorizontalAlignment(SwingConstants.CENTER);
		firstLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
		firstLabel.setBounds(295, 112, 134, 25);
		add(firstLabel);
		
		JLabel secondLabel = new JLabel("Rating:");
		secondLabel.setForeground(SystemColor.windowBorder);
		secondLabel.setHorizontalAlignment(SwingConstants.CENTER);
		secondLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
		secondLabel.setBounds(334, 198, 73, 23);
		add(secondLabel);


		JLabel thirdLabel = new JLabel("  1    2    3    4    5");
		thirdLabel.setForeground(SystemColor.windowBorder);
		thirdLabel.setFont(new Font("Arial", Font.BOLD, 13));
		thirdLabel.setBounds(432, 184, 113, 14);
		add(thirdLabel);

		JLabel fourthLabel = new JLabel("Comment:");
		fourthLabel.setForeground(SystemColor.windowBorder);
		fourthLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
		fourthLabel.setBounds(180, 291, 102, 22);
		add(fourthLabel);

		comboBox.setBounds(463, 115, 128, 22);
		String url = "jdbc:mysql://localhost:3306/sdaproject";
		String username = "root";
		String password = "123212";

		Connection connection;
		try {
			connection = DriverManager.getConnection(url, username, password); 
			String sql = "SELECT FlightId FROM Attends WHERE userId = ?";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, Username);

				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						String flightId = resultSet.getString("FlightId");
						comboBox.addItem(flightId);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		add(comboBox);


		ButtonGroup buttonGroup = new ButtonGroup();

		JRadioButton radioButton1 = new JRadioButton("1");
		radioButton1.setBounds(432, 199, 21, 23);
		add(radioButton1);

		JRadioButton radioButton2 = new JRadioButton("2");
		radioButton2.setBounds(455, 199, 21, 23);
		add(radioButton2);

		JRadioButton radioButton3 = new JRadioButton("3");
		radioButton3.setBounds(478, 199, 21, 23);
		add(radioButton3);

		JRadioButton radioButton4 = new JRadioButton("4");
		radioButton4.setBounds(501, 199, 21, 23);
		add(radioButton4);

		JRadioButton radioButton5 = new JRadioButton("5");
		radioButton5.setBounds(524, 199, 21, 23);
		add(radioButton5);

		buttonGroup.add(radioButton1);
		buttonGroup.add(radioButton2);
		buttonGroup.add(radioButton3);
		buttonGroup.add(radioButton4);
		buttonGroup.add(radioButton5);
		
		CommentField = new JTextField();
		CommentField.setBounds(309, 269, 340, 69);
		add(CommentField);
		CommentField.setColumns(10);

		CustomisedButton SubmitButton = new CustomisedButton("SUBMIT");
		SubmitButton.setForeground(Color.WHITE);
		SubmitButton.addMouseListener(new CustomisedMouseListener(SubmitButton));
		SubmitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String FlightID = (String) comboBox.getSelectedItem();
				int rating = 0;
				for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
					JRadioButton button = (JRadioButton) buttons.nextElement();
					if (button.isSelected()) {
						rating = Integer.parseInt(button.getText());
					}
				}
				
				FeedbackObject.setFlightID(FlightID);
				FeedbackObject.setRating(rating);
				FeedbackObject.setUserID(Username);
				FeedbackObject.setComment(CommentField.getText());
				
				String flag = FeedbackObject.InsertFeedback();
				
				if(flag.equals("Attended"))
				{
					showMessageDialog("Feedback Recorded Successfully!");
				}
				else if(flag.equals("Not Attended"))
				{
					showMessageDialog("Flight Not Attended!");
				}
				else
				{
					showMessageDialog("An error occurred!");
				}			
			}
		});
		SubmitButton.setBackground(Color.LIGHT_GRAY);
		SubmitButton.setFont(new Font("Arial Black", Font.BOLD, 18));
		SubmitButton.setBounds(519, 377, 130, 45);
		add(SubmitButton);
		
		JLabel backgroundLabel = new JLabel("");
		backgroundLabel.setIcon(new ImageIcon("C:\\Users\\alium\\Desktop\\87cefa.png"));
		backgroundLabel.setBounds(0, 0, 900, 520);
		add(backgroundLabel);
	}

	private static void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
	}

}
