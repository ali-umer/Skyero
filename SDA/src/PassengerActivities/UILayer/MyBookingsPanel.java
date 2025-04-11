package PassengerActivities.UILayer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import Customisations.*;

public class MyBookingsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	
	
	public MyBookingsPanel(MyBookingsFrame frame,String Username) {

		setBackground(SystemColor.window);
		setPreferredSize(new Dimension(900, 520));
		setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 94, 880, 235);
		add(scrollPane);
		
		LoadData(Username);
		

		JLabel HeadingLabel = new JLabel("MY BOOKINGS");
		HeadingLabel.setForeground(SystemColor.windowBorder);
		HeadingLabel.setFont(new Font("Arial Black", Font.BOLD, 35));
		HeadingLabel.setBounds(309, 11, 319, 62);
		add(HeadingLabel);
		
		CustomisedButton CloseButton = new CustomisedButton("BACK");
		CloseButton.setForeground(Color.WHITE);
		CloseButton.setBackground(Color.LIGHT_GRAY);
		CloseButton.addMouseListener(new CustomisedMouseListener(CloseButton));
		CloseButton.setBounds(10, 22, 130, 40);
		CloseButton.setFont(new Font("Arial Black", Font.BOLD, 18));
		CloseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		add(CloseButton);
		
		
		CustomisedButton CancelFlightButton = new CustomisedButton("CANCEL FLIGHT");
		CancelFlightButton.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
					  CancelFlight(frame,Username);
			}
		});
		CancelFlightButton.setFont(new Font("Arial Black", Font.BOLD, 18));
		CancelFlightButton.setBackground(Color.LIGHT_GRAY);
		CancelFlightButton.addMouseListener(new CustomisedMouseListener(CancelFlightButton));
		CancelFlightButton.setForeground(Color.WHITE);
		CancelFlightButton.setBounds(586, 360, 209, 50);
		add(CancelFlightButton);
		
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
				 String query = "select F.flightId,F.departure,F.destination,F.date,F.time from attends A  join flight F on F.flightId = A.flightId where A.userID = '" + UserName +"'";

	             PreparedStatement preparedStatement = connection.prepareStatement(query);

	             ResultSet resultSet = preparedStatement.executeQuery();

	             String[] columnNames = {"Flight ID", "Departure", "Destination", "Date", "Time"};
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
	 
	 public void CancelFlight(MyBookingsFrame frame,String Username)
	 {
		 int selectedRow = table.getSelectedRow();
         if (selectedRow != -1) {
         int result = JOptionPane.showConfirmDialog(
                 frame,
                 "Are you sure you want to cancel this flight?",
                 "Flight Cancellation Confirmation",
                 JOptionPane.YES_NO_OPTION
         );

         if (result == JOptionPane.YES_OPTION) {
       	
       
                String DateString = table.getValueAt(selectedRow, 3).toString();
                String FlightID = table.getValueAt(selectedRow, 0).toString();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate Date = LocalDate.parse(DateString, formatter);

                LocalDate CurrentDate = LocalDate.now();
                
                if(CurrentDate.isBefore( Date))
                {
             		String url = "jdbc:mysql://localhost:3306/sdaproject";
         	        String username = "root";
         	        String password = "123212";
         	        
         	        
         	        Connection connection;
         				
         	        try {
         	        	
         				connection = DriverManager.getConnection(url, username, password);
         				 String query = "Delete from attends where userID = '" + Username + "' AND FlightID = '" + FlightID + "'";
         				 String query3 = "Dete from Booking where User ID ='" + Username + "' AND FlightID = '" + FlightID + "'";
         				 String query2 = "UPDATE SeatData SET SeatsAvailable = SeatsAvailable + 1 WHERE FlightID = '" + FlightID + "'";

         	             PreparedStatement preparedStatement = connection.prepareStatement(query);  
         	             preparedStatement.executeUpdate();
         	             
         	             PreparedStatement preparedStatement3 = connection.prepareStatement(query3);  
        	             preparedStatement3.executeUpdate();

         	             PreparedStatement preparedStatement2 = connection.prepareStatement(query2);  
         	             preparedStatement2.executeUpdate();

         	             showMessageDialog("Flight Cancelled successfully");
         	             LoadData(Username);
         	          			} 
         	        catch (SQLException e1) {
         				e1.printStackTrace();
         				}
         			
                }
                else
                {
             	   showMessageDialog("Flight Cancellation time has ended");  
                }
             }
         }
	 }
		private static void showMessageDialog(String message) {
		    JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
		}
		
}
