package PassengerActivities.UILayer;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import RegistrationAndLogin.UILayer.MainFrame;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	
	public MainPanel(MainFrame frame,String Username) {
	
		setPreferredSize(new Dimension(900, 520));
		setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 900, 520);
		
		
		DashboardPanel panel1 = new DashboardPanel(frame,Username);
        FlightBookingPanel panel2 = new FlightBookingPanel(frame,Username); 
        FeedbackPanel panel3 = new FeedbackPanel(frame,Username);

        tabbedPane.addTab("Home", null, panel1, "");
        tabbedPane.addTab("Flight Booking", null, panel2, "");
        tabbedPane.addTab("Feedback", null, panel3, "");

        add(tabbedPane);

	}

}
