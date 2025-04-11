package PassengerActivities.BusinessLayer;

import DatabaseLayer.DatabaseFactory;

public class Payment {

	private String UserID;
	private String CardNum;
	private String CVV;
	private String ExpDate;

	public Payment()
	{
		this.UserID = null;
		this.CardNum = null;
		this.CVV = null;
		this.ExpDate = null;
	}

	public String getUserID()
	{
		return this.UserID;
	}

	public String getCardNum()
	{
		return this.CardNum;
	}

	public String getCVV()
	{
		return this.CVV;
	}

	public String getExpDate() 
	{
		return this.ExpDate;
	}

	public void setUserID(String userID) 
	{
		this.UserID = userID;
	}

	public void setCardNum(String CardNum)
	{
		this.CardNum = CardNum;
	}

	public void setCVV(String CVV)
	{
		this.CVV = CVV;
	}

	public void setExpDate(String ExpDate) 
	{
		this.ExpDate = ExpDate;
	}

	public boolean RemovePayment()
	{
		boolean Flag = DatabaseFactory.getInstance().RemovePayment(this);

		if(Flag == true)
		{
			return true;
		}

		else 
		{
			return false;
		}
	}

	public boolean InsertIntoDB()
	{
		boolean Flag = DatabaseFactory.getInstance().InsertPayment(this);

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


