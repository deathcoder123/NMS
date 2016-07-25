package GUI;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RemoveHelper extends JPanel {
	private JTextField idField;

	
	public RemoveHelper() {
		setLayout(null);
		
		idField = new JTextField();
		idField.setBounds(118, 73, 549, 35);
		add(idField);
		idField.setColumns(10);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = idField.getText();
				try{
					java.sql.Statement st = Login.getConnection().createStatement();
					java.sql.ResultSet rs = st.executeQuery("select * from helpers where id = \""+idField.getText()+"\";");
					if(rs.next()){
						int n = JOptionPane.showConfirmDialog(Main.getPane(),"Are you sure you want to delete?");
						if(n!=JOptionPane.YES_OPTION){
							reload();
							return;
						}
						st.executeUpdate("delete from helpers where id = \""+idField.getText()+"\";");
						JOptionPane.showMessageDialog(Main.getPane(), "Successfully Deleted !");
						reload();
						return;
					}
					else
					{
						JOptionPane.showMessageDialog(Main.getPane(), "Invalid Id !");
						reload();
						return;
					}
				}catch(Exception e1){
					e1.printStackTrace();
					JOptionPane.showMessageDialog(Main.getPane(), "Unknown Error !");
					reload();return;
				}
				
			}
		});
		btnNewButton.setBounds(323, 190, 117, 25);
		add(btnNewButton);

	}
	public void reload(){
		idField.setText("");
	}
}
