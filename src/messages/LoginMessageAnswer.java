package messages;

public class LoginMessageAnswer implements IVBMessage {

	private LoginMessageAnswerPayload payload;

	public LoginMessageAnswer(boolean b, String string) {
		payload = new LoginMessageAnswerPayload(b, string);
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
