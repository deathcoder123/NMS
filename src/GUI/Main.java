package GUI;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.sql.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BackEnd.Organization;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;

public class Main extends JFrame {
	
	/** A very dark red color. */
	   public static final Color VERY_DARK_RED = new Color(0x80, 0x00, 0x00);

    /** A dark red color. */
    public static final Color DARK_RED = new Color(0xc0, 0x00, 0x00);

	   /** A light red color. */
	   public static final Color LIGHT_RED = new Color(0xFF, 0x40, 0x40);
	
	 /** A very light red color. */
	   public static final Color VERY_LIGHT_RED = new Color(0xFF, 0x80, 0x80);
	
	    /** A very dark yellow color. */
	    public static final Color VERY_DARK_YELLOW = new Color(0x80, 0x80, 0x00);
	
	   /** A dark yellow color. */
	    public static final Color DARK_YELLOW = new Color(0xC0, 0xC0, 0x00);
	
	    /** A light yellow color. */
	    public static final Color LIGHT_YELLOW = new Color(0xFF, 0xFF, 0x40);
	
	    /** A very light yellow color. */
	    public static final Color VERY_LIGHT_YELLOW = new Color(0xFF, 0xFF, 0x80);
	
	    /** A very dark green color. */
	    public static final Color VERY_DARK_GREEN = new Color(0x00, 0x80, 0x00);
	
	    /** A dark green color. */
	    public static final Color DARK_GREEN = new Color(0x00, 0xC0, 0x00);
	
	    public static final Color LIGHT_GREEN = new Color(0x40, 0xFF, 0x40);
	    public static final Color VERY_LIGHT_GREEN = new Color(0x80, 0xFF, 0x80);
	    public static final Color VERY_DARK_CYAN = new Color(0x00, 0x80, 0x80);
	    public static final Color DARK_CYAN = new Color(0x00, 0xC0, 0xC0);
	    public static final Color LIGHT_CYAN = new Color(0x40, 0xFF, 0xFF);
	    public static final Color VERY_LIGHT_CYAN = new Color(0x80, 0xFF, 0xFF);
	    public static final Color VERY_DARK_BLUE = new Color(0x00, 0x00, 0x80);
	    public static final Color DARK_BLUE = new Color(0x00, 0x00, 0xC0);
		public static final Color LIGHT_BLUE = new Color(0x40, 0x40, 0xFF);
		public static final Color VERY_LIGHT_BLUE = new Color(0x80, 0x80, 0xFF);
		public static final Color VERY_DARK_MAGENTA = new Color(0x80, 0x00, 0x80);
	    public static final Color DARK_MAGENTA = new Color(0xC0, 0x00, 0xC0);
	    public static final Color LIGHT_MAGENTA = new Color(0xFF, 0x40, 0xFF);
	    public static final Color VERY_LIGHT_MAGENTA = new Color(0xFF, 0x80, 0xFF);

	private static JPanel contentPane;
	private static CardLayout card;
	private static Login login;
	private static HelperPanel helperPanel;
	private static ManagerPanel managerPanel;
	private static DonorPanel donorPanel;
	private static RegisterDonor registerDonor;
	private static Organization ngo;

	private static String  driver="jdbc:mysql://localhost:3306/";
	private static String database="testdb";
	private static String username="root";
	private static String pass="qwerty";
	private java.sql.Connection mycon=null;
	private ResultSet myrst=null;


	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	private  void establishConnection(){
		try {
			mycon=DriverManager.getConnection(driver+database,username,pass);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	private void createTables() throws SQLException{
		try{
			Statement myst = mycon.createStatement();
			myst.executeUpdate(
					"create table students(id varchar(10) primary key, dob varchar(50), name varchar(50), address varchar(50), phone varchar(50),"
							+ " class integer, school varchar(50), income integer, marks integer, gender boolean,"
							+ "books integer, bags integer, shoes integer, dress integer, fees integer);"
					);
		}
		catch(Exception e){}

		try{
			Statement myst = mycon.createStatement();
			myst.executeUpdate(
					"create table ids(helpers integer, students integer, donors integer);"
					);
			myst.executeUpdate("insert into ids (helpers,students,donors) values (0,0,0);");
		}
		catch(Exception e){}

		try{
			Statement myst = mycon.createStatement();
			myst.executeUpdate(
					"create table donors(id varchar(10) primary key, name varchar(50), password varchar(50), address varchar(50), phone varchar(50),amount integer, plan integer, donated integer, notified integer);"
					);
		}
		catch(Exception e){}


		try{
			Statement myst = mycon.createStatement();
			myst.executeUpdate(
					"create table price(class integer primary key,books integer, bags integer, shoes integer, dress integer);"
					);
			for(int i=1;i<=12;++i)
				myst.executeUpdate("insert into price values ("+i+",1,1,1,1)");

		}
		catch(Exception e){}

		try{
			Statement myst = mycon.createStatement();
			myst.executeUpdate(
					"create table helped(id varchar(50) primary key, books integer, bags integer, shoes integer, dress integer, fees integer);"
					);
		}
		catch(Exception e){}
		
		try{
			Statement myst = mycon.createStatement();
			myst.executeUpdate(
					"create table expenses(name varchar(10), books integer, bags integer, shoes integer, dress integer, fees integer);"
					);
		}
		catch(Exception e){}
		
		try{
			Statement myst = mycon.createStatement();
			myst.executeUpdate(
					"create table finance(funds integer);"
					);
			myst.executeUpdate("insert into finance (funds) values (0);");

		}
		catch(Exception e){}

		try{
			Statement myst = mycon.createStatement();
			myst.executeUpdate(
					"create table helpers(id varchar(10) primary key, name varchar(50), password varchar(50), address varchar(50), phone varchar(50));"
					);
		}
		catch(Exception e){}
		try{
			Statement myst = mycon.createStatement();
			myst.executeUpdate(
					"create table manager(id varchar(10) primary key, name varchar(50), password varchar(50), address varchar(50), phone varchar(50));"
					);
			myst.executeUpdate("insert into manager (id,name,password,address,phone) values (\"M0\",\"Jack\",\"qwerty\",\"none\",\"none\");");
		}
		catch(Exception e){}
		try{
			Statement myst = mycon.createStatement();
			myst.executeUpdate(
					"create table inventory(class INT(11) primary key ,books INT(11), bags INT(11), shoes INT(11), dress INT(11));"
					);
			for(int i=1;i<=12;++i){
				myst.executeUpdate(
						"insert into inventory (class ,books, bags , shoes , dress ) VALUES(\""+i+"\",\""+0+"\",\""+0+"\",\""+0+"\",\""+0+"\");");
				;
			}
		}
		catch(Exception e){}
		try{
			
			Statement myst = mycon.createStatement();
			myst.executeUpdate(
					"create table datee (year int primary key );"
					);
			myst.executeUpdate("insert into datee values("+(new java.util.Date().getYear())+");");
		}
		catch(Exception e){}
	}
	private void cleanUp(){
		try {
			Statement myst = mycon.createStatement();
			myst.executeUpdate("delete from students;");
			myst.executeUpdate("update donors set donated = 0;");
			myst.executeUpdate("delete from expenses;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Main() throws SQLException {
		setTitle("NGO Management\n");
		
		
		establishConnection();

		createTables();
		try {
			Statement myst = mycon.createStatement();
			ResultSet rst=myst.executeQuery("select * from datee;");
			rst.next();
			int year = rst.getInt("year");
			int currYear=new java.util.Date().getYear();
			if(currYear>year) cleanUp();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
 		card = new CardLayout(0,0);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 564);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(card);

		login = new Login();
		contentPane.add(login, "Login");

		helperPanel = new HelperPanel();
		contentPane.add(helperPanel, "Helper");

		managerPanel = new ManagerPanel();
		contentPane.add(managerPanel, "Manager");

		donorPanel = new DonorPanel();
		contentPane.add(donorPanel, "Donor");

		registerDonor = new RegisterDonor();
		contentPane.add(registerDonor, "RegisterDonor");

		card.show(contentPane, "Login");
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/resource/ngo.png")));
		
	}

	public static CardLayout getCard(){
		return card;
	}
	public static JPanel getPane(){
		return contentPane;
	}
	public static HelperPanel getHelperPanel() {
		return helperPanel;
	}
	public static Organization getOrganization(){
		return ngo;
	}
	
}
class ImgUtils {

    public BufferedImage scaleImage(int WIDTH, int HEIGHT, URL filename) {
        BufferedImage bi = null;
        try {
            ImageIcon ii = new ImageIcon(filename);//path to image
            bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = (Graphics2D) bi.createGraphics();
            g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
            g2d.drawImage(ii.getImage(), 0, 0, WIDTH, HEIGHT, null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return bi;
    }
 
}