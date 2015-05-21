package messages;

public class LoginMessageAnswer implements IVBMessage {

	public String errorMsg;
	@Override
	public MessageType MsgType() {
		return IVBMessage.MessageType.LoginMessageAnswer;
	}

	@Override
	public Object getPayload() {
		// TODO Auto-generated method stub
		return errorMsg;
	}

}
