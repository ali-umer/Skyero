package PassengerActivities.BusinessLayer;

public class User {
	protected String Username;
	protected String DOB;
	protected String CNIC;
	protected String Contact;
	protected String Gender;
	protected String Passport;
	
	public User()
	{
		this.Username = null;
		this.DOB = null;
		this.CNIC = null;
		this.Contact = null;
		this.Gender = null;
	}

	public String getUsername()
	{
		return this.Username;
	}

	public String getContact()
	{
		return this.Contact;
	}

	public String getGender()
	{
		return this.Gender;
	}

	public String getCNIC()
	{
		return this.CNIC;
	}

	public String getDOB()
	{
		return this.DOB;
	}
	
	
	public String getPassport()
	{
		return "";
	}
	
	public void InteractWithDB(String Username)
	{
		
	}
	
	public void setUsername(String Username)
	{
		this.Username = Username;
	}

	public void setContact(String Contact)
	{
		this.Contact = Contact;
	}

	public void setGender(String Gender)
	{
		this.Gender = Gender;
	}

	public void setCNIC(String CNIC)
	{
		this.CNIC = CNIC;
	}

	public void setDOB(String DOB)
	{
		 this.DOB = DOB;
	}
	
	public void setPassport(String passport)
	{
		this.Passport = passport;
	}
	
	
	
}
