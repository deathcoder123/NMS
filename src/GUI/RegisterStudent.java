package GUI;

import javax.swing.JPanel;
import javax.swing.JTextField;



import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Image;
import java.awt.event.ActionEvent;

public class RegisterStudent extends JPanel {
	private JTextField nameField;
	private JTextField dobField;
	private JTextField classField;
	private JTextField schoolField;
	private JTextField booksField;
	private JTextField bagsField;
	private JTextField shoesField;
	private JTextField dressField;
	private JTextField feesField;
	private JTextField incomeField;
	private JTextField marksField;
	private JLabel label_1;

	/**
	 * Create the panel.
	 */
	public RegisterStudent() {
		setLayout(null);
		setBackground(new java.awt.Color(255, 255, 255));;
		
		nameField = new JTextField();
		nameField.setBounds(285, 40, 229, 32);
		add(nameField);
		nameField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(37, 40, 101, 25);
		add(lblNewLabel);

		dobField = new JTextField();
		dobField.setBounds(285, 81, 229, 32);
		add(dobField);
		dobField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Date of Birth");
		lblNewLabel_1.setBounds(37, 90, 105, 15);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Class");
		lblNewLabel_2.setBounds(37, 125, 84, 25);
		add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("School");
		lblNewLabel_3.setBounds(37, 166, 70, 15);
		add(lblNewLabel_3);

		classField = new JTextField();
		classField.setBounds(285, 124, 229, 32);
		add(classField);
		classField.setColumns(10);

		schoolField = new JTextField();
		schoolField.setBounds(285, 160, 229, 32);
		add(schoolField);
		schoolField.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Gender");
		lblNewLabel_4.setBounds(37, 284, 70, 15);
		add(lblNewLabel_4);

		final JComboBox genderComboBox = new JComboBox();
		genderComboBox.setBounds(285, 280, 181, 25);
		add(genderComboBox);

		genderComboBox.addItem("MALE");genderComboBox.addItem("FEMALE");
		JLabel lblNewLabel_5 = new JLabel("BOOKS");
		lblNewLabel_5.setBounds(37, 390, 84, 25);
		add(lblNewLabel_5);
		JLabel lblNewLabel_6 = new JLabel("BAGS");
		lblNewLabel_6.setBounds(37, 420, 70, 15);
		add(lblNewLabel_6);
		JLabel lblNewLabel_7 = new JLabel("SHOES");
		lblNewLabel_7.setBounds(37, 447, 70, 15);
		add(lblNewLabel_7);
		JLabel lblNewLabel_8 = new JLabel("DRESS");
		lblNewLabel_8.setBounds(37, 474, 70, 15);
		add(lblNewLabel_8);
		JLabel lblNewLabel_9 = new JLabel("FEES");
		lblNewLabel_9.setBounds(37, 501, 70, 15);
		add(lblNewLabel_9);
		booksField = new JTextField();
		booksField.setBounds(285, 390, 232, 19);
		add(booksField);
		booksField.setColumns(10);
		bagsField = new JTextField();
		bagsField.setBounds(285, 415, 232, 19);
		add(bagsField);
		bagsField.setColumns(10);
		shoesField = new JTextField();
		shoesField.setBounds(285, 440, 232, 19);
		add(shoesField);
		shoesField.setColumns(10);
		dressField = new JTextField();
		dressField.setText("");
		dressField.setBounds(285, 469, 232, 19);
		add(dressField);
		dressField.setColumns(10);
		feesField = new JTextField();
		feesField.setBounds(285, 496, 232, 19);
		add(feesField);
		feesField.setColumns(10);
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name,dob,school;
				int Class, marks, income, books, bags, shoes, dress, fees;
				boolean gender;
				try{
					name = nameField.getText();
					dob= dobField.getText();
					DateFormat df = new SimpleDateFormat("dd-mm-yyyy");
					df.parse(dobField.getText());
					school = schoolField.getText();
					if(name.length()==0 || school.length()==0) throw new Exception();
					Class = Integer.parseInt(classField.getText());
					marks = Integer.parseInt(marksField.getText());
					income = Integer.parseInt(incomeField.getText());
					books = Integer.parseInt(booksField.getText());
					bags =  Integer.parseInt(bagsField.getText());
					shoes =  Integer.parseInt(shoesField.getText());
					dress =  Integer.parseInt(dressField.getText());
					fees =  Integer.parseInt(feesField.getText());
					gender = (genderComboBox.getSelectedIndex()==1);
					 
				}catch(Exception e){
					JOptionPane.showMessageDialog(Main.getPane(), "Invalid data!");
					return;
				}
				try {
					java.sql.Statement st =Login.getConnection().createStatement(); 
					ResultSet rs = st.executeQuery("select * from ids;");
					rs.next();
					int s=rs.getInt("students");
					String Id = new Integer(s).toString();
					Id="S"+Id;
					st.executeUpdate("update ids set students="+(s+1));

					int g=0;if(gender)g=1;
					String val = "\""+Id+"\","+"\""+name+"\","+"\""+dob+"\",\""+school+"\","+classField.getText()+","+marksField.getText()+","+incomeField.getText()+","+booksField.getText()+","+bagsField.getText()+","+
							shoesField.getText()+","+dressField.getText()+","+feesField.getText()+","+new Integer(g).toString();
					
					st.executeUpdate("insert into students (id,name,dob,school,class,marks,income,books,bags,shoes,dress,fees,gender)"
							+ "VALUES (" + val + ");");
					JOptionPane.showMessageDialog(Main.getPane(), "Successfully Registered!\n ID :"+Id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				reload();
				
			}
		});
		btnRegister.setBounds(546, 426, 212, 59);
		add(btnRegister);
		
		JLabel lblNewLabel_10 = new JLabel("Family Income");
		lblNewLabel_10.setBounds(37, 245, 114, 15);
		add(lblNewLabel_10);
		
		incomeField = new JTextField();
		incomeField.setBounds(285, 236, 229, 32);
		add(incomeField);
		incomeField.setColumns(10);
		
		JLabel label = new JLabel("Marks");
		label.setBounds(37, 207, 70, 15);
		add(label);
		
		marksField = new JTextField();
		marksField.setColumns(10);
		marksField.setBounds(285, 199, 229, 32);
		add(marksField);
		
		label_1 = new JLabel("");
		label_1.setBounds(526, 48, 247, 267);
		add(label_1);
		loadImage();

	}
	private void loadImage(){
		
		BufferedImage displayImage= new ImgUtils().scaleImage((int)(1*label_1.getWidth()), (int)(1.3*label_1.getHeight()),getClass().getResource("/resource/regS.png"));
		label_1.setIcon(new ImageIcon((Image)displayImage));
	}
	public void reload(){
		 nameField.setText("");;
		 dobField.setText("");
		 classField.setText("");
		 schoolField.setText("");
		 booksField.setText("");
		 bagsField.setText("");
		 shoesField.setText("");
		 dressField.setText("");
		 feesField.setText("");
		 incomeField.setText("");
		 marksField.setText("");

	}
}
