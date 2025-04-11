package PassengerActivities.UILayer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import Customisations.*;
import RegistrationAndLogin.UILayer.MainFrame;

public class FlightBookingPanel extends JPanel {

	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private static final long serialVersionUID = 1L;
	private CustomisedButton BookButton;
	private JLabel BackgroundLabel;
	private JTextField SearchField;
	private JComboBox<String> SortComboBox;	
	private String mainQuery = "SELECT F.flightID, Departure, Destination, RatePerSeat, Date, Time,S.SeatsAvailable FROM flight F join seatData S on s.flightID = F.flightID where Date > Current_Date()";

	public FlightBookingPanel(MainFrame frame,String Username) {

		setBackground(SystemColor.window);
		setPreferredSize(new Dimension(900, 520));
		setLayout(null);

		
		JLabel HeadingLabel = new JLabel("FLIGHT BOOKING");
		HeadingLabel.setIcon(new ImageIcon("C:\\Users\\alium\\Desktop\\booking.png"));
		HeadingLabel.setForeground(SystemColor.windowBorder);
		HeadingLabel.setFont(new Font("Arial Black", Font.BOLD, 35));
		HeadingLabel.setBounds(261, 0, 408, 65);
		add(HeadingLabel);

		SearchField = new JTextField();
		SearchField.setBounds(10, 88, 182, 20);
		add(SearchField);
		SearchField.setColumns(10);
		addPlaceholder(SearchField, "Search by Departure...");


		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 140, 860, 222);
		add(scrollPane);
		
		LoadData(mainQuery);


		SortComboBox = new JComboBox<String>();
		SortComboBox.setBounds(725, 87, 140, 22);
		SortComboBox.addItem("Sort By");
		SortComboBox.addItem("Price");
		SortComboBox.addItem("Date");

		SortComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String selectedOption = (String) SortComboBox.getSelectedItem();
				if (selectedOption.equals("Price"))
				{
					String query = "SELECT F.flightID, Departure, Destination, RatePerSeat, Date, Time,S.SeatsAvailable FROM flight F join seatData S on s.flightID = F.flightID  order by RatePerSeat where Date > Current_Date()";
					LoadData(query);
				}

				else if (selectedOption.equals("Date"))
				{
					String query = "SELECT F.flightID, Departure, Destination, RatePerSeat, Date, Time,S.SeatsAvailable FROM flight F join seatData S on s.flightID = F.flightID  order by date where Date > Current_Date()";
					LoadData(query);
				}
				else
				{
					LoadData(mainQuery);
				}
			}
		});
		add(SortComboBox);

		BookButton = new CustomisedButton("BOOK FLIGHT");
		BookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					String FlightID = table.getValueAt(selectedRow, 0).toString();
					int rate = Integer.parseInt((String) table.getValueAt(selectedRow, 3));

					int result = JOptionPane.showConfirmDialog(
							frame,
							"Are you sure you want to Book a seat in this flight?",
							"Flight Booking Confirmation",
							JOptionPane.YES_NO_OPTION
							);

					if (result == JOptionPane.YES_OPTION) {

						int seatsAvailable = 0;

						try {
							String url = "jdbc:mysql://localhost:3306/sdaproject";
							String username = "root";
							String password = "123212";
							
							Connection connection;
							connection = DriverManager.getConnection(url, username, password);

							String queryForSeats = "Select SeatsAvailable from seatData where FlightID = '" + FlightID + "'";
							PreparedStatement seatspreparedStatement = connection.prepareStatement(queryForSeats);

							ResultSet resultSet = seatspreparedStatement.executeQuery();
							if (resultSet.next()) {
								seatsAvailable = resultSet.getInt("SeatsAvailable");
							}

							if(seatsAvailable > 0)
							{
								MakePaymentFrame Payment = new MakePaymentFrame(FlightBookingPanel.this,Username,rate,FlightID);
								Payment.setVisible(true);
							} 
							else
							{
								showMessageDialog("Not Enough Space!");
							}
						}
						catch (SQLException e1) {
							showMessageDialog("An error occured!");
							e1.printStackTrace();
						}
					}
				}
			}
		});
		BookButton.setBackground(Color.LIGHT_GRAY);
		BookButton.addMouseListener(new CustomisedMouseListener(BookButton));
		BookButton.setFont(new Font("Arial Black", Font.BOLD, 18));
		BookButton.setForeground(Color.WHITE);
		BookButton.setBounds(608, 373, 214, 59);
		add(BookButton);

		CustomisedButton SearchButton = new CustomisedButton("Search");
		SearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.print(SearchField.getText());
				if(SearchField.getText().equals("Search by Departure..."))
				{
					LoadData(mainQuery);
				}
				else
				{
					String searchQuery = "SELECT F.flightID, Departure, Destination, RatePerSeat, Date, Time,S.SeatsAvailable FROM flight F join seatData S on s.flightID = F.flightID where Date > Current_Date() AND Departure = '" + SearchField.getText() + "'";
					LoadData(searchQuery);
				}
			}
		});
		SearchButton.setForeground(Color.WHITE);
		SearchButton.addMouseListener(new CustomisedMouseListener(SearchButton));
		SearchButton.setFont(new Font("Arial Black", Font.BOLD, 12));
		SearchButton.setBackground(Color.LIGHT_GRAY);
		SearchButton.setBounds(202, 87, 89, 23);
		add(SearchButton);

		BackgroundLabel = new JLabel("");
		BackgroundLabel.setIcon(new ImageIcon("C:\\Users\\alium\\Desktop\\87cefa.png"));
		BackgroundLabel.setBounds(0, 0, 900, 520);
		add(BackgroundLabel);

	}


	public void LoadData(String query)
	{
		String url = "jdbc:mysql://localhost:3306/sdaproject";
		String username = "root";
		String password = "123212";

		Connection connection;

		try {
			connection = DriverManager.getConnection(url, username, password);

			PreparedStatement preparedStatement = connection.prepareStatement(query);

			ResultSet resultSet = preparedStatement.executeQuery();

			String[] columnNames = {"Flight ID", "Departure", "Destination","Rate Per Seat", "Seats Available" ,  "Date", "Time"};
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
						resultSet.getString("flightID"),
						resultSet.getString("Departure"),
						resultSet.getString("Destination"),
						resultSet.getString("RatePerSeat"),
						resultSet.getString("SeatsAvailable"),
						resultSet.getString("Date"),
						resultSet.getString("Time")
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

	private static void addPlaceholder(JTextField textField, String placeholder) {
		textField.setText(placeholder);
		textField.setForeground(UIManager.getColor("TextField.inactiveForeground"));

		textField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textField.getText().equals(placeholder)) {
					textField.setText("");
					textField.setForeground(UIManager.getColor("TextField.foreground"));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textField.getText().isEmpty()) {
					textField.setText(placeholder);
					textField.setForeground(UIManager.getColor("TextField.inactiveForeground"));
				}
			}
		});
	}
}
