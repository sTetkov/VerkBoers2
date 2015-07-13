package HelpCLasses;

import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.LinkedList;
import java.util.Queue;

import DBClasses.User;

import messages.*;

public class ClientConnectionManager implements Runnable {

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	private String username;
	private String pwd;

	private Queue<ClientMessageThreadObject> queue;

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

	public ClientConnectionManager() {
		queue = new LinkedList<ClientMessageThreadObject>();
	}

	public User Login(String username, String pwd)
			throws ClientConnectionException {
		this.username = username;
		this.pwd = pwd;
		LoginMessageRequest msg = new LoginMessageRequest();
		msg.payload = new LoginMessageRequestPayload();
		msg.payload.username = username;
		msg.payload.password = pwd;
		LoginMessageAnswer ans = (LoginMessageAnswer) SendMessage(msg);
		LoginMessageAnswerPayload payload = ((LoginMessageAnswerPayload) ans
				.getPayload());
		if (!payload.isLoginSucces()) {
			throw new ClientConnectionException("Failure: "
					+ payload.getFailureReason());
		}
		return payload.getUserData();
	}

	public IVBMessage SendMessage(IVBMessage msg)
			throws ClientConnectionException {

		IVBMessage inMsg = null;
		// TODO manage timeouts and disconnections
		try {
			socket = new Socket();
			sep = new InetSocketAddress(hostName, port);
			MessagePackage msgPck = new MessagePackage(username, pwd, msg);
			socket.connect(sep);
			out = new ObjectOutputStream(socket.getOutputStream());

			out.writeObject(msgPck);

			in = new ObjectInputStream(socket.getInputStream());
			inMsg = (IVBMessage) in.readObject();
			socket.close();
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
			default:
				throw new ClientConnectionException(
						"Operation Failed: Unknonw answer message");
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
		return inMsg;
	}

	@Override
	public void run() {
		while (true) {
			while (!queue.isEmpty()) {
				ClientMessageThreadObject thr = queue.poll();
				thr.setHostName(hostName);
				thr.setPort(port);
				thr.setUsername(username);
				thr.setPassword(pwd);
				Thread thread = new Thread(thr);
				thread.start();
			}
		}
	}

	public void addMessage(int opID, IVBMessage msg,
			ICSMessageEventReceiver customer) {
		ClientMessageThreadObject thr = new ClientMessageThreadObject(opID,
				msg, customer);
		queue.add(thr);
	}

}
