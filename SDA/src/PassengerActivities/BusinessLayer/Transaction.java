package PassengerActivities.BusinessLayer;

import DatabaseLayer.DatabaseFactory;

public class Transaction {

	private String UserID;
	private String CardNum;
	
	public Transaction()
	{
		this.UserID = null;
		this.CardNum = null;
	}
	
	public void setUserID(String UserID)
	{
		this.UserID = UserID;
	}
	
	public void setCardNum(String CardNum)
	{
		this.CardNum = CardNum;
	}
	
	public String getUserID()
	{
		return this.UserID;
	}
	
	public String getCardNum()
	{
		return this.CardNum;
	}
	public boolean RecordTransaction(int rate)
	{
		boolean Flag = DatabaseFactory.getInstance().RecordTransaction(this, rate);
		
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
