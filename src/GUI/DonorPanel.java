package GUI;

import javax.swing.JPanel;

import BackEnd.PledgedDonor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Image;
import java.awt.Font;

public class DonorPanel extends JPanel {

	private static PledgedDonor donor;
	private static JButton btnDonate;
	private static JTextField donationAmountTextField;
	private static JComboBox donationPlanCombo;
	private static String donationPlan[]={"ANNUALLY","SEMI_ANNUALLY"};
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	
	public DonorPanel() {
		setLayout(null);
		setBackground(new java.awt.Color(255, 255, 255));;
		btnDonate = new JButton("Donate");
		btnDonate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					java.sql.Statement mystmt=Login.getConnection().createStatement();
					ResultSet rst=mystmt.executeQuery("select * from finance");
					rst.next();
					mystmt.executeUpdate("update finance set funds=\""+(rst.getInt("funds")+donor.getPledgedAmount())+"\";");
					JOptionPane.showMessageDialog(Main.getPane(), "Thank You For Your Donation");
					mystmt.executeUpdate("update donors set notified = 0 where id = \""+donor.getId()+"\";");
					donor.setNotified(0);
					donor.donate();
					mystmt.executeUpdate("update donors set donated = \""+donor.getDonated()+"\";");
					reload();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
				}

			}
		});
		btnDonate.setBounds(489, 442, 190, 39);
		add(btnDonate);
		
		JLabel lblUpdateDonationInformation = new JLabel("Update Donation Information");
		lblUpdateDonationInformation.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblUpdateDonationInformation.setBounds(55, 147, 312, 16);
		add(lblUpdateDonationInformation);
		
		JLabel lblDonationAmount = new JLabel("Donation Amount");
		lblDonationAmount.setBounds(55, 246, 120, 16);
		add(lblDonationAmount);
		
		JLabel lblDonationPlan = new JLabel("Donation Plan");
		lblDonationPlan.setBounds(55, 349, 120, 16);
		add(lblDonationPlan);
		
		donationAmountTextField=new JTextField();
		donationAmountTextField.setBounds(187, 241, 180, 26);
		add(donationAmountTextField);
		donationAmountTextField.setColumns(10);
		
		donationPlanCombo = new JComboBox(donationPlan);
		donationPlanCombo.setBounds(197, 345, 170, 27);
		add(donationPlanCombo);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int amount=0;
				try{
					amount=Integer.parseInt(donationAmountTextField.getText());
					if(amount<=0){
						Exception e = new Exception() ;
						throw e;
					}
				} catch (Exception e){
					JOptionPane.showMessageDialog(Main.getPane(), "Invalid Amount Value");
					return;
				}
				try {
					java.sql.Statement mystmt=Login.getConnection().createStatement();
					int index=donationPlanCombo.getSelectedIndex();
					mystmt.executeUpdate("update donors set plan="+index+" where id = \""+donor.getId()+"\";");
					mystmt.executeUpdate("update donors set amount="+amount+" where id = \""+donor.getId()+"\";");
					donor.setAmount(amount);
					donor.setDonationPlan(index);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(Main.getPane(), "Updated!");
				
			}
		});
		btnUpdate.setBounds(55, 441, 342, 40);
		add(btnUpdate);
		
		btnNewButton = new JButton("LOG OUT");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.reload();
				Main.getCard().show(Main.getPane(),"Login");
			}
		});
		btnNewButton.setBounds(48, 28, 148, 58);
		add(btnNewButton);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(454, 52, 299, 351);
		add(lblNewLabel);
		
		loadImage();
		loadImageBack();
		
	}
	private void loadImageBack(){
		
		BufferedImage displayImage= new ImgUtils().scaleImage(btnNewButton.getWidth(), btnNewButton.getHeight(),getClass().getResource("/resource/logout.png"));
		btnNewButton.setIcon(new ImageIcon((Image)displayImage));
	}
	private void loadImage(){
		
		BufferedImage displayImage= new ImgUtils().scaleImage(lblNewLabel.getWidth(), lblNewLabel.getHeight(),getClass().getResource("/resource/donate_button.png"));
		lblNewLabel.setIcon(new ImageIcon((Image)displayImage));
	}
	public static void reload(){
		int index=0;
		if(donor.getDonationPlan().equals("SEMI_ANNUALLY")) index=1;
		donationPlanCombo.setSelectedIndex(index);
		donationAmountTextField.setText(""+donor.getPledgedAmount());
		System.out.println(donor.isNotified());
		if(donor.isNotified()==0)
			btnDonate.setVisible(false);
		else
			btnDonate.setVisible(true);
	}
	public static void setPledgedDonor(PledgedDonor donor){
		DonorPanel.donor=donor;
	}
}
