package BackEnd;

public class PledgedDonor {
	private String name, id;
	private Contact contact;
	private Password password;
	private int amount;
	private DonationPlan donationPlan;
	private int notified, donated;
	
	public PledgedDonor(String name, String id, Contact contact, Password password, int amount, DonationPlan donationPlan,int donated, int notified){
		this.name = name;
		this.id = id;
		this.contact = contact;
		this.password = password;
		this.amount = amount;
		this.donationPlan = donationPlan;
		this.notified = notified;
		this.donated = donated;
	}
	
	public boolean setPassword(String oldPassword, String newPassword){
		if(!oldPassword.equals(password))
		return false;
		password = new Password(newPassword);
		return true;
	}

	public int getPledgedAmount() {
		return amount;
	}
	
	public int isNotified() {
		return notified;
	}

	public void setNotified(int i) {
		notified=i;
	}

	public void setAmount(int x) {
		amount=x;
	}

	public String getDonationPlan() {
		return donationPlan.toString();
	}
	public void setDonationPlan(int index) {
		if(index==0)donationPlan=DonationPlan.ANNUALLY;
		else donationPlan=donationPlan.SEMI_ANNUALLY;
	}

	public void donate() {
		donated++;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public int getDonated() {
		// TODO Auto-generated method stub
		return donated;
	}
}

