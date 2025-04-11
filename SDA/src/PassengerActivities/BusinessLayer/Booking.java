package PassengerActivities.BusinessLayer;

import java.sql.*;

import DatabaseLayer.DatabaseFactory;

public class Booking {

	private String UserID;
	private String FlightID;
	private String Status;

	public Booking()
	{
		this.UserID = null;
		this.FlightID = null;
		this.Status = null;
	}

	public void setUserID(String UserID)
	{
		this.UserID = UserID;
	}

	public void setFlightID(String FlightID)
	{
		this.FlightID = FlightID;
	}

	public void setStatus(String Status)
	{
		this.Status = Status;
	}

	public String getUserID()
	{
		return this.UserID;
	}

	public String getFlightID()
	{
		return this.FlightID;
	}

	public String getStatus()
	{
		return this.Status;
	}

	public boolean MakeBooking() 
	{
		boolean flag;
		try {
			flag = DatabaseFactory.getInstance().MakeBooking(this);

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		if(flag == true)
		{
			return true;
		}

		else
		{
			return false;
		}
	}
}
