package messages;

import DBClasses.User;

public class LoginMessageAnswer implements IVBMessage {

	private LoginMessageAnswerPayload payload;

	public LoginMessageAnswer(boolean b, String string, User userData) {
		payload = new LoginMessageAnswerPayload(b, string, userData);
	}

	@Override
	public MessageType MsgType() {
		return IVBMessage.MessageType.LoginMessageAnswer;
	}

	@Override
	public Object getPayload() {
		// TODO Auto-generated method stub
		return payload;
	}

}
