package RegistrationAndLogin.UILayer;

import java.awt.EventQueue;
import java.awt.SystemColor;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private LoginPanel panelA ;
	private RegistrationPanel panelB ;
	private PassengerActivities.UILayer.MainPanel panelC;
	private AdminActivities.UILayer.MainPanel panelD;
	private String Username;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {
		setBackground(SystemColor.window);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 520);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		panelA = new LoginPanel(this);
		panelB = new RegistrationPanel(this);

		setContentPane(panelA);
	}

	public void switchToPanelA() {
		panelA.resetCredentials();
		setContentPane(panelA);
		revalidate(); 
		repaint();
	}

	public void switchToPanelB() {
		setContentPane(panelB);
		revalidate(); 
		repaint();
	}

	public void switchToPanelC() {
		Username = panelA.getUsernameField();
		panelC = new PassengerActivities.UILayer.MainPanel(this,Username);
		setContentPane(panelC);
		revalidate(); 
		repaint();
	}

	public void switchToPanelD() {
		Username = panelA.getUsernameField();
		panelD= new AdminActivities.UILayer.MainPanel(this,Username);
		setContentPane(panelD);
		revalidate(); 
		repaint();
	}

}
