package GUI;
 
import javax.swing.JPanel;
 
import BackEnd.Helper;
 
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
 
public class HelperPanel extends JPanel {
 
	private static Helper helper;
	private static RegisterStudent registerStudent;
	private static UpdateStudent updateStudent;
	private static MinorDonation minorDonation;
	private static SetPrice setPrice;
	private static GiveHelp giveHelp;
	private static UpdateSelf updateSelf;
 
	private static JPanel contentPane;
 
	private static CardLayout card;
	private JMenuItem mntmMinorDonation;
	private JMenuItem mntmLogout;
 
 
 
	public HelperPanel() {
		setLayout(null);
 
		contentPane = new JPanel();
		registerStudent = new RegisterStudent();
		updateStudent = new UpdateStudent();
		minorDonation = new MinorDonation();
		setPrice =new SetPrice();
		giveHelp = new GiveHelp();
		updateSelf = new UpdateSelf();
 
		contentPane.setBounds(0, 30, 816, 515);
		add(contentPane);
		card = new CardLayout(0, 0);
		contentPane.setLayout(card);
 
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 816, 32);
		add(menuBar);
 
		JMenuItem mntmRegisterStudent = new JMenuItem("Register Student");
		mntmRegisterStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registerStudent.reload();
				card.show(contentPane, "RegisterStudent");
 
			}
		});
		
		mntmLogout = new JMenuItem("LogOut");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.reload();
				Main.getCard().show(Main.getPane(), "Login");
			}
		});
		menuBar.add(mntmLogout);
 
		menuBar.add(mntmRegisterStudent);
 
		mntmMinorDonation = new JMenuItem("Give Alms");
		mntmMinorDonation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MinorDonation.reload();
				card.show(contentPane, "MinorDonation");
			}
		});
		
		JMenuItem mntmSetPrice = new JMenuItem("Set Price");
		mntmSetPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				card.show(contentPane,"SetPrice");
			}
		});
		menuBar.add(mntmSetPrice);
		menuBar.add(mntmMinorDonation);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Give Help");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				giveHelp.reload();
				card.show(contentPane, "GiveHelp");
			}
		});
		menuBar.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Update Profile");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateSelf.reload();
				card.show(contentPane, "UpdateSelf");
			}
		});
		menuBar.add(mntmNewMenuItem_1);
 
		contentPane.add(registerStudent,"RegisterStudent");
		contentPane.add(updateStudent,"UpdateStudent");
		contentPane.add(minorDonation,"MinorDonation");
		contentPane.add(setPrice, "SetPrice");
		contentPane.add(giveHelp, "GiveHelp");
		contentPane.add(updateSelf, "UpdateSelf");
 
 
 
	}
	public static void setHelper(Helper helper){
		HelperPanel.helper = helper;
	}
	public static Helper getHelper(){
		return helper;
	}
}