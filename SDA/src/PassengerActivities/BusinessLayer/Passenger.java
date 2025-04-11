package PassengerActivities.BusinessLayer;

import DatabaseLayer.DatabaseFactory;

public class Passenger extends User {

	public Passenger()
	{
		this.Username = null;
		this.DOB = null;
		this.CNIC = null;
		this.Contact = null;
		this.Passport = null;
		this.Gender = null;
	}

	public String getPassport()
	{
		return this.Passport;
	}

	public void setPassport(String passport)
	{
		this.Passport = passport;
	}

	public void InteractWithDB(String UserID)
	{
		User obj = DatabaseFactory.getInstance().getPassenger(UserID);
		this.Username = obj.getUsername();
		this.DOB = obj.getDOB();
		this.CNIC = obj.getCNIC();
		this.Gender = obj.getGender();
		this.Contact = obj.getContact();
		this.Passport = obj.getPassport();
	}

}
