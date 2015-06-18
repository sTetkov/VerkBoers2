package messages;

import java.io.Serializable;

import DBClasses.User;

public class NewUserMessageRequestPayload implements Serializable {
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String password;
}
