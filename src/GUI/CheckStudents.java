package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.sql.*;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;

public class CheckStudents extends JPanel {

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
	public CheckStudents() {
		model= new MyDefaultTableModel();
		model.addColumn("ID");
		model.addColumn("NAME");
		model.addColumn("CLASS");
		model.addColumn("FEES");
		model.addColumn("BOOKS");
		model.addColumn("BAGS");
		model.addColumn("SHOES");
		model.addColumn("DRESS");
		try{
			java.sql.Statement st = Login.getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from students order by name;");
			while(rs.next()){
				Vector row = new Vector();
				row.add(rs.getString("id"));
				row.add(rs.getString("name"));
				row.add(rs.getInt("class"));
				row.add(rs.getInt("fees"));
				row.add(rs.getInt("books"));
				row.add(rs.getInt("bags"));
				row.add(rs.getInt("shoes"));
				row.add(rs.getInt("dress"));
				model.addRow(row);
			}
	}catch(Exception e){
		e.printStackTrace();
	}
	setLayout(null);
	setBackground(Main.VERY_LIGHT_CYAN);
	//		setBackground(new java.awt.Color(204, 204, 255));
	JPanel panel = new JPanel();
	panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	panel.setBounds(162, 107, 466, 428);
	add(panel);
	panel.setLayout(null);
	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(6, 18, 454, 404);
	panel.add(scrollPane);
	table = new JTable(model);

	scrollPane.setViewportView(table);
	
	JLabel lblStudents = new JLabel("STUDENTS");
	lblStudents.setHorizontalAlignment(SwingConstants.CENTER);
	lblStudents.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
	lblStudents.setBounds(0, 0, 790, 60);
	add(lblStudents);
}

public void reload(){
	try{
		java.sql.Statement st = Login.getConnection().createStatement();
		ResultSet rs;
		rs=st.executeQuery("select * from students order by name;");
		model.setRowCount(0);
		while(rs.next()){
			Vector row = new Vector();
			row.add(rs.getString("id"));
			row.add(rs.getString("name"));
			row.add(rs.getInt("class"));
			row.add(rs.getInt("fees"));
			row.add(rs.getInt("books"));
			row.add(rs.getInt("bags"));
			row.add(rs.getInt("shoes"));
			row.add(rs.getInt("dress"));
			model.addRow(row);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
}
}
