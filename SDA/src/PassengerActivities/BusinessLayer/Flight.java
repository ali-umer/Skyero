package PassengerActivities.BusinessLayer;

import DatabaseLayer.DatabaseFactory;

public class Flight {

	private String FlightID;
	private String Destination;
	private String Departure;
	private String Date;
	private String Time;
	private String RatePerSeat;
	private int NumberOfSeats;

	public Flight()
	{
		this.FlightID = null;
		this.Destination = null;
		this.Departure = null;
		this.Date = null;
		this.Time = null;
		this.RatePerSeat = null;
	}

	public void setFlightID(String FlightID) 
	{
		this.FlightID = FlightID;
	}

	public void setDestination(String Destination)
	{
		this.Destination = Destination;
	}

	public void setDeparture(String Departure)
	{
		this.Departure = Departure;
	}

	public void setDate(String Date)
	{
		this.Date = Date;
	}

	public void setTime(String Time)
	{
		this.Time = Time;
	}

	public void setRatePerSeat(String rate)
	{
		this.RatePerSeat = rate;
	}

	public void setNumberOfSeats(int num)
	{
		this.NumberOfSeats = num;
	}


	public String getFlightID() 
	{
		return this.FlightID;
	}

	public String getDestination()
	{
		return this.Destination;
	}

	public String getDeparture()
	{
		return this.Departure;
	}

	public String getDate()
	{
		return this.Date;
	}

	public String getTime()
	{
		return this.Time;
	}

	public String getRatePerSeat()
	{
		return this.RatePerSeat;
	}

	public int getNumberOfSeats()
	{
		return this.NumberOfSeats;
	}

	public boolean InsertIntoDB()
	{

		boolean Flag = DatabaseFactory.getInstance().AddFlight(this);
		if(Flag == true)
		{
			return true;
		}

		else
		{
			return false;
		}
	}

	public boolean RemoveFlight()
	{
		boolean Flag = DatabaseFactory.getInstance().RemoveFlight(this);

		if(Flag == true)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}

	public boolean UpdateFlight()

	{
		boolean Flag = DatabaseFactory.getInstance().UpdateFlight(this);

		if(Flag == true)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
}
