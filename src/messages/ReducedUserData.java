package messages;

public class ReducedUserData {

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	private String name;
	private String address;
	
	public ReducedUserData (String name, String address){
	this.name=name;
	this.address=address;
	}

}
