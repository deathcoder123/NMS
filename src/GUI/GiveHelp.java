package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import BackEnd.*;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;

public class GiveHelp extends JPanel {

	private static JTable table;
	private static DefaultTableModel model;

	private class MyDefaultTableModel extends DefaultTableModel {

		public MyDefaultTableModel() { // constructor
			super();
		}

		@Override
		public boolean isCellEditable(int row, int column) { // custom isCellEditable function
			return false;
		}
	}
	private JButton btnHelp;
	private JLabel lblGiveHelp;
	private JPanel panel;
	public GiveHelp() {
		model= new MyDefaultTableModel();
		model.addColumn("ID");
		model.addColumn("BOOKS");
		model.addColumn("BAGS");
		model.addColumn("DRESS");
		model.addColumn("SHOES");
		model.addColumn("FEES");

		try{
			java.sql.Statement st = Login.getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from helped;");
			while(rs.next()){
				Vector row = new Vector();
				row.add(rs.getString("id"));
				row.add(rs.getInt("books"));
				row.add(rs.getInt("bags"));
				row.add(rs.getInt("dress"));
				row.add(rs.getInt("shoes"));
				row.add(rs.getInt("fees"));
				model.addRow(row);
			}
	}catch(Exception e){
		e.printStackTrace();
	}
	setLayout(null);
	setBackground(Main.VERY_LIGHT_CYAN);
	
	panel = new JPanel();
	panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	panel.setBounds(31, 93, 621, 368);
	add(panel);
	panel.setLayout(null);
	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(6, 18, 609, 344);
	panel.add(scrollPane);
	table = new JTable(model);

	scrollPane.setViewportView(table);
	
	btnHelp = new JButton("Help");
	btnHelp.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try{
				int r=table.getSelectedRow();
				if(r<0){JOptionPane.showMessageDialog(Main.getPane(), "Invalid Action!");return;}
				java.sql.Statement st = Login.getConnection().createStatement();
				st.executeUpdate("delete from helped where id = \""+(String)table.getValueAt(r, 0)+"\";");
				((DefaultTableModel)table.getModel()).removeRow(r);
			}catch(Exception e){
				JOptionPane.showMessageDialog(Main.getPane(), "Unknown Error");
				e.printStackTrace();
			}
		}
	});
	btnHelp.setBounds(658, 244, 117, 45);
	loadImage();
	add(btnHelp);
	
	lblGiveHelp = new JLabel("GIVE HELP TO STUDENTS");
	lblGiveHelp.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
	lblGiveHelp.setHorizontalAlignment(SwingConstants.CENTER);
	lblGiveHelp.setBounds(0, 0, 783, 56);
	add(lblGiveHelp);
}
private void loadImage(){
		
		BufferedImage displayImage= new ImgUtils().scaleImage((int)(1.2*btnHelp.getWidth()), btnHelp.getHeight(),getClass().getResource("/resource/help.png"));
		
		btnHelp.setIcon(new ImageIcon((Image)displayImage));
}
public void reload(){
	try{
		java.sql.Statement st = Login.getConnection().createStatement();
		ResultSet rs = st.executeQuery("select * from helped order by id;");
		model.setRowCount(0);
		while(rs.next()){
			Vector row = new Vector();
			row.add(rs.getString("id"));
			row.add(rs.getInt("books"));
			row.add(rs.getInt("bags"));
			row.add(rs.getInt("dress"));
			row.add(rs.getInt("shoes"));
			row.add(rs.getInt("fees"));
			model.addRow(row);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
}
}
