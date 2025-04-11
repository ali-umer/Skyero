package AdminActivities.UILayer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Customisations.*;

import java.sql.*;

public class AddAndRemoveFlightPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scrollPane;


	public AddAndRemoveFlightPanel() {
		setBackground(SystemColor.window);
		setPreferredSize(new Dimension(900, 520));
		setLayout(null);

		JLabel HeadingLabel = new JLabel("ADD OR REMOVE FLIGHTS");
		HeadingLabel.setForeground(SystemColor.windowBorder);
		HeadingLabel.setFont(new Font("Arial Black", Font.BOLD, 32));
		HeadingLabel.setBounds(211, 11, 483, 65);
		add(HeadingLabel);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 87, 866, 232);
		add(scrollPane);

		LoadData();

		CustomisedButton RemoveFlightButton = new CustomisedButton("Remove Flight"); //Button to remove a flight
		RemoveFlightButton.setBackground(Color.LIGHT_GRAY);
		RemoveFlightButton.setForeground(Color.WHITE);
		RemoveFlightButton.setFont(new Font("Arial Black", Font.BOLD, 13));
		RemoveFlightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveFlightFrame Frame = new RemoveFlightFrame(AddAndRemoveFlightPanel.this);
				Frame.setVisible(true);
			}
		});
		RemoveFlightButton.setBounds(660, 380, 150, 55);
		RemoveFlightButton.addMouseListener(new CustomisedMouseListener(RemoveFlightButton));
		add(RemoveFlightButton);

		CustomisedButton AddFlightButton = new CustomisedButton("Add Flight"); //Button to Add a flight
		AddFlightButton.setBackground(Color.LIGHT_GRAY);
		AddFlightButton.setForeground(Color.WHITE);
		AddFlightButton.setFont(new Font("Arial Black", Font.BOLD, 14));
		AddFlightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddFlightFrame Frame = new AddFlightFrame(AddAndRemoveFlightPanel.this);
				Frame.setVisible(true);
			}
		});
		AddFlightButton.addMouseListener(new CustomisedMouseListener(AddFlightButton));
		AddFlightButton.setBounds(465, 380, 150, 55);
		add(AddFlightButton);
		
		CustomisedButton RefreshButton = new CustomisedButton("");
		RefreshButton.setBackground(Color.LIGHT_GRAY);
		RefreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadData();
			}
		});
		RefreshButton.setIcon(new ImageIcon("C:\\Users\\alium\\Desktop\\refresh.png"));
		RefreshButton.setBounds(814, 39, 50, 30);
		RefreshButton.addMouseListener(new CustomisedMouseListener(RefreshButton));
		add(RefreshButton);

		JLabel backgroundLabel = new JLabel("");
		backgroundLabel.setIcon(new ImageIcon("C:\\Users\\alium\\Desktop\\87cefa.png"));
		backgroundLabel.setBounds(0, 0, 900, 520);
		add(backgroundLabel);
	
	}

	public void LoadData() //Load data into the table
	{
		String url = "jdbc:mysql://localhost:3306/sdaproject";
		String username = "root";
		String password = "123212";

		Connection connection;

		try {
			connection = DriverManager.getConnection(url, username, password);
			String query = "SELECT F.flightID, Departure, Destination, RatePerSeat, Date, Time,S.SeatsAvailable FROM flight F join seatData S on s.flightID = F.flightID";

			PreparedStatement preparedStatement = connection.prepareStatement(query);

			ResultSet resultSet = preparedStatement.executeQuery();

			String[] columnNames = {"Flight ID", "Departure", "Destination", "Rate Per Seat" , "Date", "Time", "Seats Available"};
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
						resultSet.getString("Date"),
						resultSet.getString("Time"),
						resultSet.getString("SeatsAvailable")
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
}
