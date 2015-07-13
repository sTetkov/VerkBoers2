package Client;

import HelpCLasses.MessagePackage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

import messages.IVBMessage;
import messages.LoginMessageAnswerPayload;
import messages.OperationFailedAnswer;

/**
 * @author ka42juf
 * 
 */
public class ClientMessageThreadObject implements Runnable {

	private int opID;

	public ClientMessageThreadObject(int opID, IVBMessage message,
			ICSMessageEventReceiver customer) {
		this.opID = opID;
		this.customer = customer;
		this.message = message;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public IVBMessage getMessage() {
		return message;
	}

	public void setMessage(IVBMessage message) {
		this.message = message;
	}

	public ICSMessageEventReceiver getCustomer() {
		return customer;
	}

	public void setCustomer(ICSMessageEventReceiver customer) {
		this.customer = customer;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	private int port;
	private String hostName;
	private String username;
	private String password;
	private int timeout;
	private IVBMessage message;
	private ICSMessageEventReceiver customer;

	@Override
	public void run() {
		IVBMessage inMsg = null;
		Socket socket;
		InetSocketAddress sep;
		ObjectOutputStream out;
		ObjectInputStream in;
		// TODO manage timeouts and disconnections
		try {
			socket = new Socket();
			sep = new InetSocketAddress(hostName, port);
			MessagePackage msgPck = new MessagePackage(username, password,
					message);
			socket.connect(sep, timeout);
			out = new ObjectOutputStream(socket.getOutputStream());

			out.writeObject(msgPck);
			customer.MessageSent(opID);
			in = new ObjectInputStream(socket.getInputStream());
			inMsg = (IVBMessage) in.readObject();
			socket.close();
			switch (inMsg.MsgType()) {
			case LoginMessageAnswer: {
				LoginMessageAnswerPayload payload = (LoginMessageAnswerPayload) inMsg
						.getPayload();
				if (!payload.isLoginSucces())
					customer.CommunicationError(opID,
							new OperationFailedAnswer("Login Failed: "
									+ payload.getFailureReason()));
				else
					customer.AnswerReceived(opID, inMsg);
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
				customer.CommunicationError(opID, inMsg);
				break;
			default:
				customer.CommunicationError(opID, new OperationFailedAnswer(
						"Operation Failed: Unknown answer message"));
			}
		} catch (SocketTimeoutException e) {
			System.out.println(e.getMessage());
			customer.CommunicationError(
					opID,
					new OperationFailedAnswer(
							"Connection timout, check your internet connection, if the problem persists please contact the adiministrators."));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			customer.CommunicationError(opID, new OperationFailedAnswer(
					"Something failed: " + e.getMessage()));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			customer.CommunicationError(opID, new OperationFailedAnswer(
					"Something failed: " + e.getMessage()));
		}
		customer.AnswerReceived(opID, inMsg);

	}

}
