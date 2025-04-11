package PassengerActivities.UILayer;

import Customisations.*;
import PassengerActivities.BusinessLayer.Payment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;


public class AddPaymentPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField CardField;
	private JTextField CVVField;
	private JTextField ExpiryField;
	private Payment PaymentObject;

	public AddPaymentPanel(PaymentInfoPanel object,AddPaymentFrame frame,String Username) {

		setBackground(SystemColor.window);
		setPreferredSize(new Dimension(900, 520));
		setLayout(null);

		PaymentObject = new Payment();

		CustomisedButton BackButton = new CustomisedButton("BACK");
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		BackButton.setForeground(Color.WHITE);
		BackButton.setFont(new Font("Arial Black", Font.BOLD, 18));
		BackButton.setBackground(Color.LIGHT_GRAY);
		BackButton.setBounds(21, 25, 130, 45);
		BackButton.addMouseListener(new CustomisedMouseListener(BackButton));
		add(BackButton);

		JLabel lblNewLabel = new JLabel("ADD PAYMENT");
		lblNewLabel.setForeground(SystemColor.windowBorder);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 35));
		lblNewLabel.setBounds(297, 25, 392, 55);
		add(lblNewLabel);

		JLabel FirstLabel = new JLabel("Enter Card Number:");
		FirstLabel.setForeground(SystemColor.windowBorder);
		FirstLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
		FirstLabel.setBounds(146, 170, 157, 21);
		add(FirstLabel);

		JLabel SecondLabel = new JLabel("Enter CVV:");
		SecondLabel.setForeground(SystemColor.windowBorder);
		SecondLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
		SecondLabel.setBounds(146, 230, 157, 21);
		add(SecondLabel);

		JLabel ThirdLabel = new JLabel("Enter Expiry Date:");
		ThirdLabel.setForeground(SystemColor.windowBorder);
		ThirdLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
		ThirdLabel.setBounds(146, 290, 157, 21);
		add(ThirdLabel);

		CardField = new JTextField();
		CardField.setBounds(333, 172, 300, 25);
		add(CardField);
		CardField.setColumns(10);

		CVVField = new JTextField();
		CVVField.setBounds(333, 232, 300, 25);
		add(CVVField);
		CVVField.setColumns(10);

		ExpiryField = new JTextField();
		ExpiryField.setBounds(333, 292, 300, 25);
		add(ExpiryField);
		ExpiryField.setColumns(10);

		CustomisedButton ConfirmButton = new CustomisedButton("CONFIRM");
		ConfirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean DateFlag = false;
				boolean temp = true;
				
				if(CardField.getText().length() != 16)
				{
					temp = false;
					showMessageDialog("Card Number should be of 16 characters");		

				}
				
				if(CVVField.getText().length() != 3)
				{
					temp = false;
					showMessageDialog("CVV should be of 3 characters ");		
				}
				
				
				if ((isValidDateFormat(ExpiryField.getText()))) {
					if ((isValidDate(ExpiryField.getText()))) {
						DateFlag = true;
					}
				}
				
				if(DateFlag == false)
				{
					showMessageDialog("Wrong date or  format!\n Use yyyy-mm-dd format");		

				}
				
				if(DateFlag == true && temp == true)
				{

					PaymentObject.setUserID(Username);
					PaymentObject.setCVV(CVVField.getText());
					PaymentObject.setCardNum(CardField.getText());
					PaymentObject.setExpDate(ExpiryField.getText());

					boolean flag = PaymentObject.InsertIntoDB();

					if(flag == true)
					{
						showMessageDialog("METHOD ADDITION SUCCESSFUL!");
						object.LoadData(Username);
						frame.dispose();
					}

					else
					{
						showMessageDialog("An error occured!");
					}

				}
			}
		});
		ConfirmButton.setForeground(Color.WHITE);
		ConfirmButton.setFont(new Font("Arial Black", Font.BOLD, 18));
		ConfirmButton.setBackground(Color.LIGHT_GRAY);
		ConfirmButton.setBounds(503, 357, 140, 45);
		ConfirmButton.addMouseListener(new CustomisedMouseListener(ConfirmButton));

		add(ConfirmButton);

		JLabel BackgroundLabel = new JLabel("");
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
