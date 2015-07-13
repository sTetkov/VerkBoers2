package Client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

import messages.*;

public class ClientConnectionManager implements Runnable {

	private boolean hasCredentials = false;

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
	private int timeout;

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public ClientConnectionManager() {
		queue = new LinkedList<ClientMessageThreadObject>();
		initConnectionParameters();
	}

	private void initConnectionParameters() {
		hostName = "localhost";
		port = 10000;
		timeout = 6000;
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
				new Thread(thr).start();
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException ex) {
				Logger.getLogger(ClientConnectionManager.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		}
	}

	public void addMessage(int opID, IVBMessage msg,
			ICSMessageEventReceiver customer) {
		ClientMessageThreadObject thr = new ClientMessageThreadObject(opID,
				msg, customer);
		queue.add(thr);
	}

	public void clearCredentials() {
		hasCredentials = false;
	}

}
