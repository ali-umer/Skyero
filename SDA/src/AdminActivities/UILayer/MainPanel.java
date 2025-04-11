package AdminActivities.UILayer;

import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import RegistrationAndLogin.UILayer.MainFrame;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public MainPanel(MainFrame frame,String Username) {

		setBackground(SystemColor.window);
		setPreferredSize(new Dimension(900, 520));
		setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(SystemColor.window);
		tabbedPane.setBounds(0, 0, 900, 520);
		add(tabbedPane);

		DashboardPanel panel1 = new DashboardPanel(frame,Username);
		AddAndRemoveFlightPanel panel2 = new AddAndRemoveFlightPanel(); 
		FlightReschedulingPanel panel3 = new FlightReschedulingPanel();

		tabbedPane.addTab("Home", null, panel1, null);
		tabbedPane.addTab("Add/Remove Flights", null, panel2, null);
		tabbedPane.addTab("Reschedule Flights", null, panel3, null);		
	}

}
