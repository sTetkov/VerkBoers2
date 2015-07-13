package messages;

import java.io.Serializable;

import messages.IVBMessage;

public class MessagePackage implements Serializable {
	private String userName;

	public MessagePackage(String username2, String pwd, IVBMessage msg) {
		this.userName = username2;
		this.password = pwd;
		this.message = msg;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public messages.IVBMessage getMessage() {
		return message;
	}

	public void setMessage(messages.IVBMessage message) {
		this.message = message;
	}

	private String password;
	private messages.IVBMessage message;
}
