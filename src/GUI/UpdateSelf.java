package GUI;

import javax.swing.JPanel;
import javax.swing.JTextField;

import BackEnd.Helper;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class UpdateSelf extends JPanel {
	private JTextField addressField;
	private JTextField phoneField;
	private JPasswordField passwordField;

	public UpdateSelf() {
		setLayout(null);
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(441, 95, 157, 26);
		add(addressField);
		
		JLabel label = new JLabel("Address");
		label.setBounds(217, 105, 123, 16);
		add(label);
		
		phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBounds(441, 167, 157, 26);
		add(phoneField);
		
		JLabel label_1 = new JLabel("Phone Number");
		label_1.setBounds(217, 177, 123, 16);
		add(label_1);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(441, 235, 157, 26);
		add(passwordField);
		
		JLabel label_2 = new JLabel("Password");
		label_2.setBounds(217, 245, 123, 16);
		add(label_2);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(addressField.getText().length()==0 || phoneField.getText().length()==0 || passwordField.getText().length()==0){
					JOptionPane.showMessageDialog(Main.getPane(), "Invalid input!");
					return;
				}
				Helper helper = HelperPanel.getHelper();
				helper.setAddress(addressField.getText());
				helper.setPhone(phoneField.getText());
				helper.setPassword(helper.getPassword(),passwordField.getText());
				try{
					java.sql.Statement st = Login.getConnection().createStatement();
					st.executeUpdate("update helpers set address = \""+addressField.getText()+"\" where id = \""+helper.getId()+"\";");
					st.executeUpdate("update helpers set phone = \""+phoneField.getText()+"\" where id = \""+helper.getId()+"\";");
					st.executeUpdate("update helpers set password = \""+passwordField.getText()+"\" where id = \""+helper.getId()+"\";");
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(Main.getPane(), "Unknown Error!");
					return;
				}
				JOptionPane.showMessageDialog(Main.getPane(), "Updated!");
				reload();
			}
		});
		btnUpdate.setBounds(367, 342, 136, 43);
		add(btnUpdate);
		
	}
	
	public void reload(){
		Helper helper = HelperPanel.getHelper();
		addressField.setText(helper.getAddress());
		phoneField.setText(helper.getPhone());
		passwordField.setText("");
	}
}
