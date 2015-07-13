package messages;

import java.io.Serializable;

import DBClasses.User;

public class LoginMessageAnswerPayload implements Serializable {
	private boolean loginSuccess;
	private User userData;

	public User getUserData() {
		return userData;
	}

	public void setUserData(User userData) {
		this.userData = userData;
	}

	public boolean isLoginSucces() {
		return loginSuccess;
	}

	public void setLoginSucces(boolean loginSuccess) {
		this.loginSuccess = loginSuccess;
	}

	public LoginMessageAnswerPayload(boolean loginSuccess, String msg,
			User userData) {
		this.loginSuccess = loginSuccess;
		this.failureReason = msg;
		this.userData = userData;
	}

	public String getFailureReason() {
		return failureReason;
	}

	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}

	private String failureReason;
}
