//contact class
 
package BackEnd;
 
public class Contact {
	private String address;
	
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	private String phone;
 
	public Contact(String address, String phone){
		this.address = address;
		this.phone = phone;
	}
	public String getAddress(){
		return address;
	}
	public String getPhone(){
		return phone;
	}
 
}