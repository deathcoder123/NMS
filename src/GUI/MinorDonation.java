package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

//import com.mysql.jdbc.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

public class MinorDonation extends JPanel {
	private static JTextField booksTextField;
	private static JTextField bagsTextField;
	private static JTextField shoesTextField;
	private static JTextField dressTextField;
	private static JTextField moneyTextField;
	private static JTextField bookClassTextField;
	private static JTextField bagClassTextField;
	private static JTextField shoeClassTextField;
	private static JTextField dressClassTextField;
	private JLabel lblMinorDonation;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel label_4;

	/**
	 * Create the panel.
	 * @throws Exception 
	 */
	private int convertToInt(JTextField field) throws Exception{
		if(field.getText().length()==0){
			Exception e=new Exception("Empty Field");
			throw e;
		}
		int value;
		try{
			value=Integer.parseInt(field.getText());
		}catch (Exception e){
			e=new Exception("Not A Valid Input");
			throw e;
		}
		return value; 
	}
	public MinorDonation() {
		setLayout(null);
		setBackground(new java.awt.Color(255, 255, 255));;
		JLabel label = new JLabel("BOOKS");
		label.setBounds(40, 163, 84, 25);
		add(label);
		
		JLabel label_1 = new JLabel("BAGS");
		label_1.setBounds(40, 227, 70, 15);
		add(label_1);
		
		JLabel label_2 = new JLabel("SHOES");
		label_2.setBounds(40, 283, 70, 15);
		add(label_2);
		
		JLabel label_3 = new JLabel("DRESS");
		label_3.setBounds(40, 336, 70, 15);
		add(label_3);
		
		JLabel lblMoney = new JLabel("MONEY");
		lblMoney.setBounds(40, 392, 70, 15);
		add(lblMoney);
		
		booksTextField = new JTextField();
		booksTextField.setColumns(10);
		booksTextField.setBounds(155, 164, 114, 19);
		add(booksTextField);
		
		bagsTextField = new JTextField();
		bagsTextField.setColumns(10);
		bagsTextField.setBounds(155, 225, 114, 19);
		add(bagsTextField);
		
		shoesTextField = new JTextField();
		shoesTextField.setColumns(10);
		shoesTextField.setBounds(155, 279, 114, 19);
		add(shoesTextField);
		
		dressTextField = new JTextField();
		dressTextField.setText("");
		dressTextField.setColumns(10);
		dressTextField.setBounds(155, 334, 114, 19);
		add(dressTextField);
		
		moneyTextField = new JTextField();
		moneyTextField.setColumns(10);
		moneyTextField.setBounds(155, 390, 114, 19);
		add(moneyTextField);
		bookClassTextField = new JTextField();
		bookClassTextField.setBounds(327, 162, 130, 25);
		add(bookClassTextField);
		bookClassTextField.setColumns(10);
		
		bagClassTextField = new JTextField();
		bagClassTextField.setColumns(10);
		bagClassTextField.setBounds(327, 221, 130, 25);
		add(bagClassTextField);
		
		shoeClassTextField = new JTextField();
		shoeClassTextField.setColumns(10);
		shoeClassTextField.setBounds(327, 277, 130, 25);
		add(shoeClassTextField);
		
		dressClassTextField = new JTextField();
		dressClassTextField.setColumns(10);
		dressClassTextField.setBounds(327, 330, 130, 25);
		add(dressClassTextField);
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int books,bags,shoes,dress,money,bookClass,bagClass,shoeClass,dressClass;
				try{
					books=convertToInt(booksTextField);
					bags=convertToInt(bagsTextField);
					shoes=convertToInt(shoesTextField);
					dress=convertToInt(dressTextField);
					money=convertToInt(moneyTextField);
					bookClass=convertToInt(bookClassTextField);
					bagClass=convertToInt(bagClassTextField);
					shoeClass=convertToInt(shoeClassTextField);
					dressClass=convertToInt(dressClassTextField);
					if(books<0 || bags<0 || shoes<0 || dress<0 || money<0 || bookClass>12 || bagClass>12 || shoeClass>12 || dressClass>12||bookClass<=0 || bagClass<=0 || shoeClass<=0 || dressClass<=0){
						Exception e=new Exception("Invalid Class Input: No such Class Exists");
						throw e;
					}
				}catch(Exception e){
					JOptionPane.showMessageDialog(Main.getPane(), e.getMessage());
					return;
				}
				try {
					java.sql.Statement mystmt=Login.getConnection().createStatement();
					
					java.sql.Statement mystmtt=Login.getConnection().createStatement();
					
					ResultSet myrst=mystmt.executeQuery("select * from inventory");myrst.next();
					for(int i=1;i<=12;++i){        //assuming class are from 1 to 12
						myrst.next();
						if(bookClass==i){
							mystmtt.executeUpdate("Update inventory set books=\""+(books+myrst.getInt("books"))+"\" where class=\""+i+"\";");
						}
						if(bagClass==i){
							mystmtt.executeUpdate("Update inventory set bags=\""+(bags+myrst.getInt("bags"))+"\" where class=\""+i+"\";");
						}
						if(shoeClass==i){
							mystmtt.executeUpdate("Update inventory set shoes=\""+(shoes+myrst.getInt("shoes"))+"\" where class=\""+i+"\";");
						}
						if(bookClass==i){
							mystmtt.executeUpdate("Update inventory set dress=\""+(dress+myrst.getInt("dress"))+"\" where class=\""+i+"\";");
						}
					}
					myrst=mystmt.executeQuery("Select * from finance");myrst.next();
					mystmtt.executeUpdate("Update finance set funds=\""+(money+myrst.getInt("funds"))+"\";");
					JOptionPane.showMessageDialog(Main.getPane(), "Donation Recorded");		
					reload();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
				
				
			}
		});
		btnAccept.setBounds(260, 447, 215, 53);
		add(btnAccept);
		
		lblMinorDonation = new JLabel("MINOR DONATION");
		lblMinorDonation.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblMinorDonation.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinorDonation.setBounds(0, 0, 724, 53);
		add(lblMinorDonation);
		
		lblNewLabel = new JLabel("Frequency");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(155, 112, 114, 16);
		add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Class");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(327, 112, 130, 16);
		add(lblNewLabel_1);
		
		label_4 = new JLabel("");
		label_4.setBounds(479, 112, 276, 255);
		loadImage();
		add(label_4);
		
		

	}
	private void loadImage(){
		
		BufferedImage displayImage= new ImgUtils().scaleImage(label_4.getWidth(), label_4.getHeight(),getClass().getResource("/resource/donation2.jpg"));
		label_4.setIcon(new ImageIcon((Image)displayImage));
	}
	public static void reload(){
		booksTextField.setText("0");
		bagsTextField.setText("0");
		shoesTextField.setText("0");
		dressTextField.setText("0");
		moneyTextField.setText("0");
		bookClassTextField.setText("1");
		bagClassTextField.setText("1");
		shoeClassTextField.setText("1");
		dressClassTextField.setText("1");
	}

}
