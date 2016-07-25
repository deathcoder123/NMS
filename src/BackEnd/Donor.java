package BackEnd;

import java.util.Date;
import java.util.Map;

public class Donor {
	private String name;
	private Contact contact;
	
	public Donor(String name,Contact contact){
		this.name = name;
		this.contact = contact;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public boolean donate(Map<Item,Integer> itemFreqMap){
		Donation donation = new Donation(this,0,new Date(),itemFreqMap);
		Organization.getOrganization().receiveDonation(donation);
		return true;
	}
}
