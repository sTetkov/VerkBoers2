package messages;

public class LoginMessageAnswer implements IVBMessage {

	private LoginMessageAnswerPayload payload;
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
