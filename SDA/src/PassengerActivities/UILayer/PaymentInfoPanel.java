package PassengerActivities.UILayer;

import Customisations.*;
import PassengerActivities.BusinessLayer.Payment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;


public class PaymentInfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private Payment PaymentObject;

	public PaymentInfoPanel(PaymentInfoFrame frame,String Username) {
	
		setBackground(SystemColor.window);
		setPreferredSize(new Dimension(900, 520));
		setLayout(null);

		PaymentObject = new Payment();

		
		JLabel HeadingLabel = new JLabel("PAYMENT INFORMATION");
		HeadingLabel.setForeground(SystemColor.windowBorder);
		HeadingLabel.setFont(new Font("Arial Black", Font.BOLD, 35));
		HeadingLabel.setBounds(195, 11, 515, 55);
		add(HeadingLabel);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 101, 880, 249);
		add(scrollPane);
		LoadData(Username);

		CustomisedButton AddMethodButton = new CustomisedButton("ADD METHOD");
		AddMethodButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				                	  AddPaymentFrame frame = new AddPaymentFrame(PaymentInfoPanel.this,Username);
				                      frame.setVisible(true);
			}
		});
		AddMethodButton.setBackground(Color.LIGHT_GRAY);
		AddMethodButton.setForeground(Color.WHITE);
		AddMethodButton.setFont(new Font("Arial Black", Font.BOLD, 17));
		AddMethodButton.addMouseListener(new CustomisedMouseListener(AddMethodButton));
		AddMethodButton.setBounds(388, 407, 200, 55);
		add(AddMethodButton);
		
		CustomisedButton CloseButton = new CustomisedButton("BACK");
		CloseButton.setBackground(Color.LIGHT_GRAY);
		CloseButton.setForeground(Color.WHITE);
		CloseButton.setFont(new Font("Arial Black", Font.BOLD, 18));
		CloseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		CloseButton.setBounds(10, 15, 130, 45);
		CloseButton.addMouseListener(new CustomisedMouseListener(CloseButton));

		add(CloseButton);


		CustomisedButton RemoveMethodButton = new CustomisedButton("REMOVE METHOD");
		RemoveMethodButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String CardNum = "";
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					CardNum = table.getValueAt(selectedRow, 0).toString();

					int result = JOptionPane.showConfirmDialog(
							frame,
							"Are you sure you want to remove this method of payment?",
							"Method Removal Confirmation",
							JOptionPane.YES_NO_OPTION
							);

					if (result == JOptionPane.YES_OPTION) {
						PaymentObject.setUserID(Username);
						PaymentObject.setCardNum(CardNum);
						boolean flag = PaymentObject.RemovePayment();
						
						if(flag == true)
						{
							showMessageDialog("Method Removed Successfully!");
							LoadData(Username); 
						}
						else
						{
							showMessageDialog("An error occured!");  
						}
					}
				}
			}
		});
		RemoveMethodButton.setBackground(Color.LIGHT_GRAY);
		RemoveMethodButton.setFont(new Font("Arial Black", Font.BOLD, 16));
		RemoveMethodButton.setForeground(Color.WHITE);
		RemoveMethodButton.addMouseListener(new CustomisedMouseListener(RemoveMethodButton));
		RemoveMethodButton.setBounds(654, 407, 200, 55);
		add(RemoveMethodButton);
		
		JLabel BackgroundLabel = new JLabel("");
		BackgroundLabel.setIcon(new ImageIcon("C:\\Users\\alium\\Desktop\\87cefa.png"));
		BackgroundLabel.setBounds(0, 0, 900, 520);
		add(BackgroundLabel);
		



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
