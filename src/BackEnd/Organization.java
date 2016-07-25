package BackEnd;

import java.util.ArrayList;
import java.util.*;

public class Organization {
	private ArrayList<PledgedDonor> donors;
	private ArrayList<Helper> helpers;
	private ArrayList<Student> students;
	private ArrayList<Donation> donations;
	private Map<Donor,ArrayList<Donation> > donorMap;
	
	private Manager manager;
	private int funds;
	private ArrayList<Expenditure> expenditure;
	private Map<Student,ArrayList<Record> > performanceRecords;
	private Inventory inventory;
	
	static private Organization ngo;
	
	public Organization(){
		
	}
	
	public static Organization getOrganization(){
		return ngo;
	}
	
	public static void init(){
		
	}

	public void registerStudent(Student student) {
		// TODO Auto-generated method stub
		
	}

	public void receiveDonation(Donation donation) {
		// TODO Auto-generated method stub
		
	}

	public int getFunds() {
		// TODO Auto-generated method stub
		return funds;
	}

	public Inventory getInventory() {
		// TODO Auto-generated method stub
		return inventory;
	}

	public void registerHelper(Helper helper) {
		// TODO Auto-generated method stub
		
	}

	

	public ArrayList<Helper> getHelperList() {
		// TODO Auto-generated method stub
		return helpers;
	}

	public ArrayList<Student> getStudentList() {
		// TODO Auto-generated method stub
		return students;
	}

	public ArrayList<PledgedDonor> getDonorList() {
		// TODO Auto-generated method stub
		return donors;
	}
	
	void buyItems(Requirement requirement){
		funds-=requirement.getAmount();
		Map<Item,Integer> mp =requirement.getItemFreqMap();
		Iterator it = mp.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry pair = (Map.Entry)it.next();
			inventory.useItem(pair.getKey(),pair.getValue());
		}
		
	}
		
}
