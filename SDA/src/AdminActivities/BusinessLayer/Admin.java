package AdminActivities.BusinessLayer;

import DatabaseLayer.DatabaseFactory;
import PassengerActivities.BusinessLayer.User;

public class Admin extends User{

	public Admin()
	{
		this.Username = null;
		this.DOB = null;
		this.CNIC = null;
		this.Contact = null;
		this.Gender = null;
	}

	public void InteractWithDB(String Username)// function to interact with Database
	{
		User obj = DatabaseFactory.getInstance().getAdmin(Username);
		this.Username = obj.getUsername();
		this.DOB = obj.getDOB();
		this.CNIC = obj.getCNIC();
		this.Gender = obj.getGender();
		this.Contact = obj.getContact();
	}
}
