//manager iteration 2.0
 
 
package BackEnd;

import java.util.ArrayList;

public class Manager {
	private String name, id, password;
	private Contact contact;
 
	public Manager(String name, String id, String password,Contact contact){
		this.name = name;
		this.id = id;
		this.password = password;
		this.contact=contact;
	}
 
	public Manager(String name, String string2, Contact contact, Password password2) {
		this.name=name;
		this.password=password;
		this.id=id;
		this.contact=contact;
		// TODO Auto-generated constructor stub
	}
	public int checkFunds(){
		return Organization.getOrganization().getFunds();
	}
	public Inventory checkInventory(){
		return Organization.getOrganization().getInventory();
	}
	
	public void registerHelper(Helper helper){
		 Organization.getOrganization().registerHelper(helper);
	}
	public ArrayList<Helper> checkHelperList(){
		return Organization.getOrganization().getHelperList();
	}
	
	public ArrayList<Student> checkStudentList(){
		return Organization.getOrganization().getStudentList();
	}
	
	public ArrayList<PledgedDonor> checkDonorList(){
		return Organization.getOrganization().getDonorList();
	}
 
}