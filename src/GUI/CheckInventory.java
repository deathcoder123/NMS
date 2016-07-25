package GUI;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.*;
import java.sql.*;
import java.util.Vector;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;

public class CheckInventory extends JPanel {
	private static JTable table;
	private static DefaultTableModel model;
	private JTextField fundsField;
	/**
	 * Create the panel.
	 */
private class MyDefaultTableModel extends DefaultTableModel {
	    
	    public MyDefaultTableModel() { // constructor
	        super();
	    }

	    @Override
	    public boolean isCellEditable(int row, int column) { // custom isCellEditable function
	        return false;
	    }
	}
	public CheckInventory() {
		setLayout(null);
		model= new MyDefaultTableModel();
		model.addColumn("CLASS");
		model.addColumn("BOOKS");
		model.addColumn("BAGS");
		model.addColumn("SHOES");
		model.addColumn("DRESS");
		try{
			java.sql.Statement st = Login.getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from inventory;");
			for(int i=0;i<=11;++i){
				Vector row = new Vector();
				rs.next();
				row.add(i+1);
				row.add(rs.getInt("books"));
				row.add(rs.getInt("bags"));
				row.add(rs.getInt("shoes"));
				row.add(rs.getInt("dress"));
				model.addRow(row);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//setBackground(new java.awt.Color(255, 255, 255));
		setBackground(Main.VERY_LIGHT_CYAN);
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(92, 131, 621, 368);
		add(panel);
		panel.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 18, 609, 344);
		panel.add(scrollPane);
		table = new JTable(model);
		table.setRowSelectionAllowed(false);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Funds");
		lblNewLabel.setBounds(209, 86, 70, 15);
		add(lblNewLabel);
		
		fundsField = new JTextField();
		fundsField.setBounds(429, 84, 114, 19);
		add(fundsField);
		fundsField.setColumns(10);
		fundsField.setEditable(false);
		
		JLabel lblFunds = new JLabel("FUNDS");
		lblFunds.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblFunds.setHorizontalAlignment(SwingConstants.CENTER);
		lblFunds.setBounds(-27, -19, 838, 78);
		add(lblFunds);
		
	}
	
	public void reload(){
		try{
			java.sql.Statement st = Login.getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from finance;");rs.next();
			fundsField.setText(""+rs.getInt("funds"));
			model.setRowCount(0);
			rs = st.executeQuery("select * from inventory;");
			for(int i=0;i<=11;++i){
				Vector row = new Vector();
				rs.next();
				row.add(i+1);
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
