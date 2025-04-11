package RegistrationAndLogin.BusinessLayer;

import DatabaseLayer.DatabaseFactory;

public class Login {
	private String Username;
	private String Password;

	public Login()
	{
		this.Username = null;
		this.Password = null;
	}

	public void setUsername(String Username)
	{
		this.Username = Username;
	}

	public void setPassword(String Password)
	{
		this.Password = Password;
	}
	
	public String getUsername()
	{
		return this.Username;
	}

	public String getPassword()
	{
		return this.Password;
	}

	public String InteractWithDB()
	{
		String flag = DatabaseFactory.getInstance().AuthenticateUser(this);
		
		if(flag == "Passenger")
		{
			return "Passenger";
		}
		
		else if (flag == "Admin")
		{
			return "Admin";
		}
		
		else 
		{
			return " "; 
		}
	}


}
