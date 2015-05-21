import messages.IVBMessage;


public class ClientConnectionManager {
	
	private String username;
	private String pwd;
	
	public void Login(String username, String pwd)
	{
		this.username=username;
		this.pwd=pwd;
	}
	
	public IVBMessage SendMessage(IVBMessage msg)
	{
		return null;
	}

	
}
