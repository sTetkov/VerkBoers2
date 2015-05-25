import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketAddress;

import messages.*;


public class ClientConnectionManager {
	
	private String username;
	private String pwd;
	
	private String hostName;
	private int port;
	private Socket socket;
	private SocketAddress sep;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	public void Login(String username, String pwd) throws ClientConnectionException
	{
		this.username=username;
		this.pwd=pwd;
	}
	
	public IVBMessage SendMessage(IVBMessage msg) throws ClientConnectionException
	{
	    try{
	    MessagePackage msgPck=new MessagePackage(username,pwd,msg);
	    socket.connect(sep);
	    out.writeObject(msg);
	    out.close();
	    IVBMessage inMsg=(IVBMessage)in.readObject();
	    switch(inMsg.MsgType())
	    {
	    case LoginMessageAnswer:
	    {
		LoginMessageAnswerPayload payload=(LoginMessageAnswerPayload)inMsg.getPayload();
		if(!payload.isLoginSucces())
		    throw new ClientConnectionException("Login failed: "+payload.getFailureReason());
	    }
		break;
	    case NewUserMessageAnswer:
		break;
	    case NewUserConfirmationCodeAnswer:
		break;
	    case DeleteUserMessageAnswer:
		break;
	    case ChangeUserDataMessageAnswer:
		break;
	    case AddArticleMessageAnswer:
		break;
	    case DeleteArticleMessageAnswer:
		break;
	    case ChangeAreticleMessageAnswer:
		break;
	    case ArticleListMessageAnswer:
		break;
	    case BuyArticleMessageAnswer:
		break;
	    case LogoutMessageAnswer:
		break;
	    }
	    }catch(IOException e)
	    {
		System.out.println(e.getMessage());
		throw new ClientConnectionException("Something failed: "+e.getMessage());
	    } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new ClientConnectionException("Something failed: "+e.getMessage());
	    }
	    return null;
	}

	
}
