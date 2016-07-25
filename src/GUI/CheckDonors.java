package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import BackEnd.*;
import java.sql.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import java.awt.Font;

public class CheckDonors extends JPanel {

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
	public CheckDonors() {
		model= new MyDefaultTableModel();
		model.addColumn("ID");
		model.addColumn("NAME");
		model.addColumn("ADDRESS");
		model.addColumn("PHONE");
		model.addColumn("AMOUNT");
		model.addColumn("PLAN");
		model.addColumn("PASSWORD");
		model.addColumn("NOTIFIED");
		try{
			java.sql.Statement st = Login.getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from donors order by name;");
			while(rs.next()){
				Vector row = new Vector();
				row.add(rs.getString("id"));
				row.add(rs.getString("name"));
				row.add(rs.getString("address"));
				row.add(rs.getString("phone"));
				row.add(rs.getInt("amount"));
				row.add(rs.getInt("plan")==0?DonationPlan.ANNUALLY.toString():DonationPlan.SEMI_ANNUALLY.toString());
				row.add(rs.getString("password"));
				row.add(rs.getInt("notified")==0?"No":"Yes");
				model.addRow(row);
			}
	}catch(Exception e){
		e.printStackTrace();
	}
	setLayout(null);
	//setBackground(new java.awt.Color(255, 255, 255));
	setBackground(Main.VERY_LIGHT_CYAN);
	JPanel panel = new JPanel();
	panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	panel.setBounds(95, 83, 629, 375);
	add(panel);
	panel.setLayout(null);
	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(6, 19, 609, 344);
	panel.add(scrollPane);
	table = new JTable(model);

	scrollPane.setViewportView(table);
	
	JLabel lblNewLabel = new JLabel("DONORS");
	lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel.setBounds(0, 0, 816, 50);
	add(lblNewLabel);
}

public void reload(){
	try{
		java.sql.Statement st = Login.getConnection().createStatement();
		ResultSet rs = st.executeQuery("select * from donors order by name;");
		model.setRowCount(0);
		while(rs.next()){
			Vector row = new Vector();
			row.add(rs.getString("id"));
			row.add(rs.getString("name"));
			row.add(rs.getString("address"));
			row.add(rs.getString("phone"));
			row.add(rs.getInt("amount"));
			row.add(rs.getInt("plan")==0?DonationPlan.ANNUALLY.toString():DonationPlan.SEMI_ANNUALLY.toString());
			row.add(rs.getString("password"));
			row.add(rs.getInt("notified")==0?"No":"Yes");
			model.addRow(row);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
}
}
