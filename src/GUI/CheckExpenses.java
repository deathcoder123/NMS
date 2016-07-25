package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import BackEnd.*;
import java.sql.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import java.awt.Font;

public class CheckExpenses extends JPanel {

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
	public CheckExpenses() {
		model= new MyDefaultTableModel();
		model.addColumn("NAME");
		model.addColumn("BOOKS");
		model.addColumn("BAGS");
		model.addColumn("DRESS");
		model.addColumn("SHOES");
		model.addColumn("FEES");

		try{
			java.sql.Statement st = Login.getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from expenses;");
			while(rs.next()){
				Vector row = new Vector();
				row.add(rs.getString("name"));
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
	//setBackground(new java.awt.Color(255, 255, 255));
	setBackground(Main.VERY_LIGHT_CYAN);
	
	JPanel panel = new JPanel();
	panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	panel.setBounds(80, 120, 621, 368);
	add(panel);
	panel.setLayout(null);
	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(6, 18, 609, 344);
	panel.add(scrollPane);
	table = new JTable(model);

	scrollPane.setViewportView(table);
	
	JLabel lblExpenses = new JLabel("EXPENSES");
	lblExpenses.setHorizontalAlignment(SwingConstants.CENTER);
	lblExpenses.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
	lblExpenses.setBounds(-19, 0, 824, 71);
	add(lblExpenses);
}

public void reload(){
	try{
		java.sql.Statement st = Login.getConnection().createStatement();
		ResultSet rs = st.executeQuery("select * from expenses;");
		model.setRowCount(0);
		while(rs.next()){
			Vector row = new Vector();
			row.add(rs.getString("name"));
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
