package RegistrationAndLogin.BusinessLayer;

import DatabaseLayer.DatabaseFactory;

public class Registration {
	private String Username;
	private String DOB;
	private String CNIC;
	private String Contact;
	private String Passport;
	private String Password;
	private String Gender;

	public Registration()
	{
		this.Username = null;
		this.DOB = null;
		this.CNIC = null;
		this.Contact = null;
		this.Passport = null;
		this.Password = null;
		this.Gender = null;
	}

	public void SetUsername(String Username)
	{
		this.Username = Username; 
	}

	public void SetDOB(String DOB)
	{
		this.DOB = DOB; 
	}

	public void SetCNIC(String CNIC)
	{
		this.CNIC = CNIC; 
	}

	public void SetContact(String Contact)
	{
		this.Contact = Contact; 
	}

	public void SetPassport(String Passport)
	{
		this.Passport = Passport; 
	}

	public void SetPassword(String Password)
	{
		this.Password = Password; 
	}

	public void SetGender(String Gender)
	{
		this.Gender = Gender; 
	}

	public String getUsername()
	{
		return this.Username; 
	}

	public String getDOB()
	{
		return this.DOB; 
	}

	public String getCNIC()
	{
		return this.CNIC; 
	}

	public String getContact()
	{
		return this.Contact; 
	}

	public String getPassport()
	{
		return this.Passport; 
	}

	public String getPassword()
	{
		return this.Password; 
	}

	public String getGender()
	{
		return this.Gender; 
	}

	public boolean InsertIntoDB()
	{
		boolean flag = DatabaseFactory.getInstance().InsertUser(this);
		
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

