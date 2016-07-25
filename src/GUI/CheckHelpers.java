package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import BackEnd.*;
import java.sql.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import java.awt.Font;

public class CheckHelpers extends JPanel {

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
	public CheckHelpers() {
		setLayout(null);
		model= new MyDefaultTableModel();
		model.addColumn("ID");
		model.addColumn("NAME");
		model.addColumn("ADDRESS");
		model.addColumn("PHONE");
		model.addColumn("PASSWORD");
		try{
			java.sql.Statement st = Login.getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from helpers order by name;");
			while(rs.next()){
				Vector row = new Vector();
				row.add(rs.getString("id"));
				row.add(rs.getString("name"));
				row.add(rs.getString("address"));
				row.add(rs.getString("phone"));
				row.add(rs.getString("password"));
				model.addRow(row);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//setBackground(new java.awt.Color(255, 255, 255));
		setBackground(Main.VERY_LIGHT_CYAN);
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(103, 105, 621, 368);
		add(panel);
		panel.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 18, 609, 344);
		panel.add(scrollPane);
		table = new JTable(model);

		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("HELPERS");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(-16, 0, 819, 58);
		add(lblNewLabel);
	}

	public void reload(){
		try{
			java.sql.Statement st = Login.getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from helpers order by name;");
			int i=0;
			while(rs.next()){
				model.setValueAt(rs.getString("id"), i, 0);
				model.setValueAt(rs.getString("name"), i, 1);
				model.setValueAt(rs.getString("address"), i, 2);
				model.setValueAt(rs.getString("phone"), i, 3);
				model.setValueAt(rs.getString("password"), i, 4);
				++i;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
