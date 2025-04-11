package PassengerActivities.BusinessLayer;

import AdminActivities.BusinessLayer.Admin;

public class UserFactory {

	private static UserFactory uniqueIns;

	private UserFactory() {
	}

	public static UserFactory getInstance()
	{
		if(uniqueIns == null)
		{
			uniqueIns = new UserFactory();
		}
		return uniqueIns;
	}

	public User createUser(String usertype) {
		User user = null;

		if(usertype == "Admin")
		{
			user = new Admin();
		}
		else if(usertype == "Passenger")
		{
			user = new Passenger();
		}

		return user;
	}
}
