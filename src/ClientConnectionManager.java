import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import DBClasses.User;

import messages.*;

public class ClientConnectionManager {

	private String username;
	private String pwd;

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	private String hostName;
	private int port;
	private Socket socket;
	private SocketAddress sep;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	public ClientConnectionManager()
	{
		socket=new Socket();
	}
	
	public User Login(String username, String pwd)
			throws ClientConnectionException {
		this.username = username;
		this.pwd = pwd;
		LoginMessageRequest msg=new LoginMessageRequest();
		msg.payload=new LoginMessageRequestPayload();
		msg.payload.username=username;
		msg.payload.password=pwd;
		LoginMessageAnswer ans=(LoginMessageAnswer)SendMessage(msg);
		LoginMessageAnswerPayload payload=((LoginMessageAnswerPayload)ans.getPayload());
		if(!payload.isLoginSucces())
		{
			throw new ClientConnectionException("Failure: "+payload.getFailureReason()); 
		}
		return payload.getUserData();
	}

	public IVBMessage SendMessage(IVBMessage msg)
			throws ClientConnectionException {

		// TODO manage timeouts and disconnections
		try {
			sep=new InetSocketAddress(hostName,port);
			MessagePackage msgPck = new MessagePackage(username, pwd, msg);
			socket.connect(sep);
			out.writeObject(msgPck);
			out.close();
			IVBMessage inMsg = (IVBMessage) in.readObject();
			switch (inMsg.MsgType()) {
			case LoginMessageAnswer: {
				LoginMessageAnswerPayload payload = (LoginMessageAnswerPayload) inMsg
						.getPayload();
				if (!payload.isLoginSucces())
					throw new ClientConnectionException("Login failed: "
							+ payload.getFailureReason());
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
			case OperationFailedAnswer:
				throw new ClientConnectionException("Operation Failed: "
						+ ((OperationFailedAnswer) inMsg).getReasons());
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			throw new ClientConnectionException("Something failed: "
					+ e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ClientConnectionException("Something failed: "
					+ e.getMessage());
		}
		return null;
	}

}
