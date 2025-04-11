package PassengerActivities.BusinessLayer;

import DatabaseLayer.DatabaseFactory;

public class Feedback {

	private String UserID;
	private String FlightID;
	private String Comment;
	private int rating;

	public void setUserID(String UserID)
	{
		this.UserID = UserID;
	}

	public void setFlightID(String FlightID)
	{
		this.FlightID = FlightID;
	}

	public void setComment(String Comment)
	{
		this.Comment = Comment;
	}

	public void setRating(int rating)
	{
		this.rating = rating;
	}


	public String getUserID()
	{
		return this.UserID;
	}

	public String getFlightID()
	{
		return this.FlightID;
	}

	public String getComment()
	{
		return this.Comment;
	}

	public int getRating()
	{
		return this.rating;
	}

	public String InsertFeedback()
	{
		String temp = DatabaseFactory.getInstance().InsertFeedback(this);

		if(temp == "Attended")
		{
			return "Attended";	
		}

		else if(temp == "Not Attended")
		{
			return "Not Attended";
		}

		else
		{
			return "Error";
		}
	}
}