package DatabaseLayer;

import java.sql.*;
import java.time.LocalDate;
import PassengerActivities.BusinessLayer.*;
import RegistrationAndLogin.BusinessLayer.Login;
import RegistrationAndLogin.BusinessLayer.Registration;

public class DatabaseFactory {

	public static DatabaseFactory UniqueIns;

	private DatabaseFactory(){
	}

	public static DatabaseFactory getInstance()
	{
		if(UniqueIns == null)
		{
			UniqueIns = new DatabaseFactory();
		}
		return UniqueIns;
	}

	public User getAdmin(String UserID)
	{
		User AdminObject = UserFactory.getInstance().createUser("Admin");

		String url = "jdbc:mysql://localhost:3306/sdaproject";
		String username = "root";
		String password = "123212";


		Connection connection;

		try {

			connection = DriverManager.getConnection(url, username, password);
			String query1 = "select u.userId,U.DOB,U.CNIC,U.gender,U.Contact  from users U Join Admin A on A.userId = U.userId where U.userId = ?";

			PreparedStatement preparedStatement = connection.prepareStatement(query1);

			preparedStatement.setString(1, UserID);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				AdminObject.setUsername(resultSet.getString("userId"));
				AdminObject.setDOB(resultSet.getString("DOB"));
				AdminObject.setCNIC(resultSet.getString("CNIC"));
				AdminObject.setGender(resultSet.getString("gender"));
				AdminObject.setContact(resultSet.getString("Contact"));
			}

			connection.close(); 

		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

		return AdminObject;

	}

	public boolean UpdateAdmin(User obj)
	{
		String url = "jdbc:mysql://localhost:3306/sdaproject";
		String username = "root";
		String password = "123212";

		Connection connection;

		try {

			connection = DriverManager.getConnection(url, username, password);
			String query = "update users set contact = ?, DOB = ?, Gender = ?, CNIC = ? where UserID = ?";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, obj.getContact()); 
			preparedStatement.setString(2, obj.getDOB()); 
			preparedStatement.setString(3, obj.getGender()); 
			preparedStatement.setString(4, obj.getCNIC()); 
			preparedStatement.setString(5, obj.getUsername());

			preparedStatement.executeUpdate();

			connection.close();
			return true;
		}
		catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}

	public boolean UpdatePassenger(User obj)
	{
		String url = "jdbc:mysql://localhost:3306/sdaproject";
		String username = "root";
		String password = "123212";

		Connection connection;

		try {

			connection = DriverManager.getConnection(url, username, password);
			String query = "update users set contact = ?, DOB = ?, Gender = ?, CNIC = ? where UserID = ?";
			String query2 = "update passenger set passportNum = ? where UserID = ?";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, obj.getContact()); 
			preparedStatement.setString(2, obj.getDOB()); 
			preparedStatement.setString(3, obj.getGender()); 
			preparedStatement.setString(4, obj.getCNIC()); 
			preparedStatement.setString(5, obj.getUsername());

			PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
			preparedStatement2.setString(1, obj.getPassport()); 
			preparedStatement2.setString(2, obj.getUsername());

			preparedStatement.executeUpdate();
			preparedStatement2.executeUpdate();
			connection.close();
			return true;
		}
		catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}

	public User getPassenger(String UserID)
	{
		User PassengerObject = UserFactory.getInstance().createUser("Passenger");

		String url = "jdbc:mysql://localhost:3306/sdaproject";
		String username = "root";
		String password = "123212";

		Connection connection;

		try {

			connection = DriverManager.getConnection(url, username, password);
			String query1 = "select u.userId,U.DOB,U.CNIC,U.gender,U.Contact,P.passportNum  from users u Join Passenger p on p.userId = u.userId where U.userId = ?";

			PreparedStatement preparedStatement = connection.prepareStatement(query1);

			preparedStatement.setString(1, UserID);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				PassengerObject.setUsername(resultSet.getString("userId"));
				PassengerObject.setDOB(resultSet.getString("DOB"));
				PassengerObject.setCNIC(resultSet.getString("CNIC"));
				PassengerObject.setGender(resultSet.getString("gender"));
				PassengerObject.setContact(resultSet.getString("Contact"));
				PassengerObject.setPassport(resultSet.getString("passportNum"));
			}

			connection.close(); 

		} 
		catch (SQLException e1) {

			e1.printStackTrace();
		}

		return PassengerObject;
	}

	public boolean MakeBooking(Booking obj) throws SQLException
	{
		String url = "jdbc:mysql://localhost:3306/sdaproject";
		String username = "root";
		String password = "123212";

		Connection connection;

		try {
			connection = DriverManager.getConnection(url, username, password);

			String query1 = "INSERT INTO Booking values ('" + obj.getUserID() +"','" + obj.getFlightID() + "')" ;
			String query2 = "INSERT INTO Attends VALUES ('" + obj.getUserID() + "','" + obj.getFlightID() + "','" + obj.getStatus() +"')";
			String query3 = "UPDATE SeatData SET SeatsAvailable = SeatsAvailable - 1 WHERE FlightID = '" + obj.getFlightID() + "'";

			PreparedStatement preparedStatement = connection.prepareStatement(query1);
			preparedStatement.executeUpdate();

			PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
			preparedStatement2.executeUpdate();

			PreparedStatement preparedStatement3 = connection.prepareStatement(query3);
			preparedStatement3.executeUpdate();

			connection.close(); 
			return true;
		}
		catch(SQLException e)
		{
			return false;
		}
	}

	public String InsertFeedback(Feedback obj)
	{
		String url = "jdbc:mysql://localhost:3306/sdaproject";
		String username = "root";
		String password = "123212";

		try {
			Connection connection = DriverManager.getConnection(url, username, password);

			String query1 = "SELECT status FROM attends WHERE flightId = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query1);
			preparedStatement.setString(1, obj.getFlightID()); 

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String status = resultSet.getString("status");

				if (status.equals("Attended")) {
					String query2 = "INSERT INTO feedback (UserID, FlightID, Description, Score) VALUES (?, ?, ?, ?)";
					PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
					preparedStatement2.setString(1, obj.getUserID()); 
					preparedStatement2.setString(2, obj.getFlightID()); 
					preparedStatement2.setString(3, obj.getComment()); 
					preparedStatement2.setInt(4, obj.getRating()); 
					preparedStatement2.executeUpdate();

					return "Attended";

				} else {

					return "Not Attended";
				}
			}

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error";
		}
		return "Error";
	}


	public boolean AddFlight(Flight obj)
	{
		String url = "jdbc:mysql://localhost:3306/sdaproject";
		String username = "root";
		String password = "123212";


		Connection connection;

		try {

			connection = DriverManager.getConnection(url, username, password);
			String query = "INSERT INTO Flight (FlightID, Departure, Destination, RatePerSeat, Date, Time) VALUES ('" + obj.getFlightID() + "', '" + obj.getDeparture() + "', '" + obj.getDestination() + "', '" + obj.getRatePerSeat() + "', '" + obj.getDate() + "', '" + obj.getTime() + "')";
			String query2 = "INSERT INTO seatData VALUES ('" + obj.getFlightID() + "', '" + obj.getNumberOfSeats() + "')";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();

			PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
			preparedStatement2.executeUpdate();
			connection.close(); 

			return true;

		} 
		catch (SQLException e1) {

			e1.printStackTrace();
			return false;
		}
	}

	public boolean RemoveFlight(Flight obj)
	{
		String url = "jdbc:mysql://localhost:3306/sdaproject";
		String username = "root";
		String password = "123212";


		Connection connection;

		try {

			connection = DriverManager.getConnection(url, username, password);
			String query1 = "DELETE from Flight where FlightId = '" + obj.getFlightID() + "'" ;

			PreparedStatement preparedStatement = connection.prepareStatement(query1);
			preparedStatement.executeUpdate();
			connection.close(); 

			return true;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	
	}

	public boolean UpdateFlight(Flight obj)
	{
		String url = "jdbc:mysql://localhost:3306/sdaproject";
		String username = "root";
		String password = "123212";

		Connection connection;

		try {
			connection = DriverManager.getConnection(url, username, password);
			String query1 = "update flight set date = '" + obj.getDate() + "', time = '" + obj.getTime() +"' where flightId = '" + obj.getFlightID() + "'";              

			PreparedStatement preparedStatement = connection.prepareStatement(query1);
			preparedStatement.executeUpdate();

			connection.close(); 
			return true;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean InsertPayment(Payment obj)
	{
		String url = "jdbc:mysql://localhost:3306/sdaproject";
		String username = "root";
		String password = "123212";


		Connection connection;

		try {

			connection = DriverManager.getConnection(url, username, password);
			String query = "INSERT INTO PaymentInformation (UserID, CardNum, CVV, expiryDate) VALUES (?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, obj.getUserID());
			preparedStatement.setString(2, obj.getCardNum());
			preparedStatement.setString(3, obj.getCVV());
			preparedStatement.setString(4, obj.getExpDate());

			preparedStatement.executeUpdate();
			connection.close();
			return true;

		} 
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	
	}

	public boolean RemovePayment(Payment obj)
	{
		String url = "jdbc:mysql://localhost:3306/sdaproject";
		String username = "root";
		String password = "123212";


		Connection connection;

		try {

			connection = DriverManager.getConnection(url, username, password);
			String query = "delete from paymentInformation where userId = '" + obj.getUserID() + "' AND cardNum = '" + obj.getCardNum() + "'";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
			connection.close(); 

			return true;
		} 
		catch (SQLException e1)
		{
			e1.printStackTrace();
			return false;
		}
	}

	public boolean RecordTransaction(Transaction obj,int rate)
	{
		String url = "jdbc:mysql://localhost:3306/sdaproject";
		String username = "root";
		String password = "123212";

		int balance = 0;
		int TID = 0;
		Connection connection;

		try {

			connection = DriverManager.getConnection(url, username, password);
			String query = "SELECT balance FROM transactions ORDER BY TID DESC LIMIT 1";
			String query2 = "SELECT TID FROM transactions ORDER BY TID DESC LIMIT 1" ;

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			PreparedStatement preparedStatement2 = connection.prepareStatement(query2);

			ResultSet resultSet = preparedStatement.executeQuery();
			ResultSet resultSet2 = preparedStatement2.executeQuery();

			if (resultSet.next())
			{
				balance = resultSet.getInt("balance");
			}

			if (resultSet2.next())
			{
				TID = resultSet2.getInt("TID");

			}
			TID +=1;
			balance += rate;

			LocalDate currentDate = LocalDate.now();
			String insertQuery = "insert into transactions values(?,?,?,?,?,?)";
			PreparedStatement insertPreparedStatement = connection.prepareStatement(insertQuery);

			insertPreparedStatement.setInt(1, TID);
			insertPreparedStatement.setString(2, obj.getUserID());
			insertPreparedStatement.setString(3,obj.getCardNum());
			insertPreparedStatement.setInt(4, rate);
			insertPreparedStatement.setString(5, currentDate.toString());
			insertPreparedStatement.setInt(6, balance);

			insertPreparedStatement.executeUpdate();

			connection.close();
			return true;
		}
		catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}

	public boolean InsertUser(Registration object)
	{
		String url = "jdbc:mysql://localhost:3306/sdaproject";
		String username = "root";
		String password = "123212";

		Connection connection;

		try {

			connection = DriverManager.getConnection(url, username, password);
			String InsertIntoUserQuery = "INSERT INTO users (UserID, Password, Role, DOB, Gender, CNIC, Contact) VALUES ('" + object.getUsername() + "', '" + object.getPassword() + "', 'Passenger', '" + object.getDOB() + "', '" + object.getGender() + "', '" + object.getCNIC() + "', '" + object.getContact() + "')";
			String InsertIntoPassengerQuery = "INSERT INTO passenger (UserID,passportNum) VALUES ('" + object.getUsername() + "', '" + object.getPassport() + "')";

			PreparedStatement preparedStatement = connection.prepareStatement(InsertIntoUserQuery);
			preparedStatement.executeUpdate();

			PreparedStatement preparedStatement2 = connection.prepareStatement(InsertIntoPassengerQuery);
			preparedStatement2.executeUpdate();

			connection.close(); 
			return true;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String AuthenticateUser(Login object)
	{
		String role = "";
		String url = "jdbc:mysql://localhost:3306/sdaproject";
		String username = "root";
		String password = "123212";

		Connection connection;
		try {

			connection = DriverManager.getConnection(url, username, password);

			String sql = "SELECT * FROM users WHERE userId = ? AND password = ?";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, object.getUsername());
			String passwordField = new String(object.getPassword());
			preparedStatement.setString(2, passwordField);


			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
			{
				role = resultSet.getString("role");
			}

			else
			{
				return " ";
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();

		}

		if("Passenger".equals(role))
		{
			return "Passenger";
		}

		else if("Admin".equals(role))
		{
			return "Admin";
		}

		else
		{
			return " ";
		}
	}

}
