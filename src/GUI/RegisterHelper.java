//register helper panel
 
package GUI;
 
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
 
import BackEnd.Contact;
import BackEnd.Helper;
import BackEnd.Password;
 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
 
public class RegisterHelper extends JPanel {
	private JTextField nameTextField;
	private JTextField addressTextField;
	private JTextField phoneTextField;
	private JTextField passwordTextField;
	private JLabel lblNewLabel;
 
	/**
	 * Create the panel.
	 */
 
	public RegisterHelper() {
		setLayout(null);
		setBackground(new java.awt.Color(255, 255, 255));
		//setBackground(new java.awt.Color(159, 0, 0));
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(64, 124, 123, 16);
		add(lblName);
 
		nameTextField = new JTextField();
		nameTextField.setBounds(288, 114, 157, 26);
		add(nameTextField);
		nameTextField.setColumns(10);
 
		addressTextField = new JTextField();
		addressTextField.setColumns(10);
		addressTextField.setBounds(288, 192, 157, 26);
		add(addressTextField);
 
		JLabel lblContact = new JLabel("Address");
		lblContact.setBounds(64, 202, 123, 16);
		add(lblContact);
 
		phoneTextField = new JTextField();
		phoneTextField.setColumns(10);
		phoneTextField.setBounds(288, 264, 157, 26);
		add(phoneTextField);
 
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(64, 274, 123, 16);
		add(lblPhoneNumber);
 
		passwordTextField = new JTextField();
		passwordTextField.setColumns(10);
		passwordTextField.setBounds(288, 332, 157, 26);
		add(passwordTextField);
 
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(64, 342, 123, 16);
		add(lblPassword);
 
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=nameTextField.getText();
				String address=addressTextField.getText();
				String phone=phoneTextField.getText();
				String password=passwordTextField.getText();
				if(name.length()==0 || address.length()==0 || phone.length()==0 || password.length()==0 ){
					JOptionPane.showMessageDialog(getParent().getParent(), "Invalid Or Empty Entry!");
					return;
				}
				ResultSet rs;
				String Id;
				try {
					java.sql.Statement st =Login.getConnection().createStatement(); 
					rs = st.executeQuery("select * from ids;");
					rs.next();
					int h=rs.getInt("helpers");
					Id = new Integer(h).toString();
					Id="H"+Id;
					st.executeUpdate("update ids set helpers="+(h+1));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(getParent().getParent(), "Unknown error!");
					return;
				}
				Helper helper=new Helper(name,Id,new Contact(address,phone),new Password(password));
				helper.registerHelper();
				JOptionPane.showMessageDialog(Main.getPane(), "Helper Registered\n ID : "+Id);
				reload();
			}
		});
		btnSubmit.setBounds(273, 422, 136, 43);
		add(btnSubmit);
		
		JLabel lblR = new JLabel("REGISTER HELPER");
		lblR.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblR.setHorizontalAlignment(SwingConstants.CENTER);
		lblR.setBounds(-19, 0, 825, 56);
		add(lblR);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(514, 124, 231, 235);
		loadImage();
		add(lblNewLabel);
 
	}
private void loadImage(){
		
		BufferedImage displayImage= new ImgUtils().scaleImage(lblNewLabel.getWidth(), lblNewLabel.getHeight(),getClass().getResource("/resource/regHelper.jpg"));
		lblNewLabel.setIcon(new ImageIcon((Image)displayImage));
	}
	public void reload(){
		nameTextField.setText("");
		addressTextField.setText("");
		phoneTextField.setText("");
		passwordTextField.setText("");
	}
 
}