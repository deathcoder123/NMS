package BackEnd;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Donation {
	private Donor donor;
	private int money;
	private Date date;
	private Map<Item,Integer> itemFreq;
	
	public Donation(Donor donor, int money, Date date,Map<Item,Integer> itemFreq){
		this.donor = donor;
		this.money=money;
		this.date = date;
		this.itemFreq = itemFreq;
	}
	public Donor getDonor(){
		return donor;
	}
	public int getMoney(){
		return money;
	}
	public Date getDate(){
		return date;
	}
	public int getFreq(Item it){
		if(itemFreq.containsKey(it))
		return itemFreq.get(it);
		else return 0;
	}
	
}
