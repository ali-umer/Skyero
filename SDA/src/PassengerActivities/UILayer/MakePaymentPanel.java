package PassengerActivities.UILayer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Customisations.*;
import PassengerActivities.BusinessLayer.Booking;
import PassengerActivities.BusinessLayer.Payment;
import PassengerActivities.BusinessLayer.Transaction;

public class MakePaymentPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private Payment PaymentObject;
	private Booking BookingObject;
	private Transaction TransactionObject;
	private String mainQuery = "SELECT F.flightID, Departure, Destination, RatePerSeat, Date, Time,S.SeatsAvailable FROM flight F join seatData S on s.flightID = F.flightID";

	public MakePaymentPanel(FlightBookingPanel object,MakePaymentFrame frame,String Username,int rate,String FlightID) {

		setBackground(SystemColor.window);
		setPreferredSize(new Dimension(900, 520));
		setLayout(null);
		
		PaymentObject = new Payment();
		BookingObject = new Booking();
		TransactionObject = new Transaction();

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

		JLabel lblNewLabel = new JLabel("PAYMENT");
		lblNewLabel.setForeground(SystemColor.windowBorder);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 35));
		lblNewLabel.setBounds(338, 26, 208, 45);
		add(lblNewLabel);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 103, 859, 177);
		add(scrollPane);

		LoadData(Username);

		CustomisedButton btnNewButton = new CustomisedButton("MAKE PAYMENT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					String CardNum = table.getValueAt(selectedRow, 0).toString();

					int result = JOptionPane.showConfirmDialog(
							frame,
							"Are you sure you want to pay " + rate + " rupees for this flight?",
							"Payment Confirmation",
							JOptionPane.YES_NO_OPTION
							);

					if (result == JOptionPane.YES_OPTION) {
						String Message = "THANK YOU!\nAn amount of " + rate + " rupees will be deducted from your card shortly!";
						showMessageDialog(Message );
						
						PaymentObject.setUserID(Username);
						PaymentObject.setCardNum(CardNum);
						TransactionObject.setUserID(Username);
						TransactionObject.setCardNum(CardNum);
						boolean flag = TransactionObject.RecordTransaction(rate);
						
						if(flag == true)
						{
							BookingObject.setUserID(Username);
							BookingObject.setFlightID(FlightID);
							BookingObject.setStatus("Not Attended");
							boolean Flag = BookingObject.MakeBooking();
							if(Flag == true)
							{
								showMessageDialog("Flight Booking Successful!");
								object.LoadData(mainQuery); 
							}
							else{
								showMessageDialog("An Error Occured!");
							}
							
							
						}
						else
						{
							
						}
						
						frame.dispose();
						
					}
				}
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 16));
		btnNewButton.setBounds(669, 363, 200, 50);
		btnNewButton.addMouseListener(new CustomisedMouseListener(btnNewButton));
		add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\alium\\Desktop\\87cefa.png"));
		lblNewLabel_1.setBounds(0, 0, 900, 520);
		add(lblNewLabel_1);
	}


	public void LoadData(String UserName)
	{
		String url = "jdbc:mysql://localhost:3306/sdaproject";
		String username = "root";
		String password = "123212";

		Connection connection;

		try {
			connection = DriverManager.getConnection(url, username, password);
			String query = "select CardNum,CVV,expiryDate from paymentInformation where userID = '" + UserName +"'";

			PreparedStatement preparedStatement = connection.prepareStatement(query);

			ResultSet resultSet = preparedStatement.executeQuery();

			String[] columnNames = {"Card Number" , "CVV" , "Expiry Date" };

			model = new DefaultTableModel(columnNames,0) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			while (resultSet.next()) {
				Object[] rowData = {

						resultSet.getString("CardNum"),
						resultSet.getString("CVV"),
						resultSet.getString("expiryDate"),

				};
				model.addRow(rowData);
			}
			table = new JTable(model);
			table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			table.setShowGrid(false);
			table.setFont(new Font("Arial", Font.PLAIN, 12));
			scrollPane.setViewportView(table);

		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}

	}
	
	private static void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
	}

}
