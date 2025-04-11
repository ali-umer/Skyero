package AdminActivities.UILayer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Customisations.*;

import java.sql.*;

public class FlightReschedulingPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private JLabel backgroundLabel;

	public FlightReschedulingPanel() {

		setBackground(SystemColor.window);
		setPreferredSize(new Dimension(900, 520));
		setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 87, 868, 232);
		add(scrollPane);

		LoadData();

		JLabel HeadingLabel = new JLabel("RESCHEDULE FLIGHTS");
		HeadingLabel.setIcon(new ImageIcon("C:\\Users\\alium\\Desktop\\rescheduling.png"));
		HeadingLabel.setForeground(SystemColor.windowBorder);
		HeadingLabel.setFont(new Font("Arial Black", Font.BOLD, 32));
		HeadingLabel.setBounds(232, 11, 483, 65);
		add(HeadingLabel);

		CustomisedButton RescheduleButton = new CustomisedButton("RESCHEDULE");
		RescheduleButton.setForeground(Color.WHITE);
		RescheduleButton.setBackground(Color.LIGHT_GRAY);
		RescheduleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					String FlightID = table.getValueAt(selectedRow, 0).toString();
					RescheduleFlightFrame frame = new RescheduleFlightFrame(FlightReschedulingPanel.this,FlightID);
					frame.setVisible(true);
				}

			}
		});
		RescheduleButton.setFont(new Font("Arial Black", Font.BOLD, 15));
		RescheduleButton.setBounds(637, 372, 160, 55);
		RescheduleButton.addMouseListener(new CustomisedMouseListener(RescheduleButton));
		add(RescheduleButton);
		
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

		backgroundLabel = new JLabel("");
		backgroundLabel.setIcon(new ImageIcon("C:\\Users\\alium\\Desktop\\87cefa.png"));
		backgroundLabel.setBounds(0, 0, 900, 520);
		add(backgroundLabel);
	}

	public void LoadData()
	{
		String url = "jdbc:mysql://localhost:3306/sdaproject";
		String username = "root";
		String password = "123212";

		Connection connection;

		try {

			connection = DriverManager.getConnection(url, username, password);
			String query = "SELECT flightID, Departure, Destination,RatePerSeat, Date, Time FROM flight";

			PreparedStatement preparedStatement = connection.prepareStatement(query);

			ResultSet resultSet = preparedStatement.executeQuery();

			String[] columnNames = {"Flight ID", "Departure", "Destination", "Rate Per Seat" , "Date", "Time"};
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
}
