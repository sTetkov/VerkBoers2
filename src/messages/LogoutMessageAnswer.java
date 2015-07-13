package messages;

public class LogoutMessageAnswer implements IVBMessage {

	GenericAnswerPayload payload;

	public LogoutMessageAnswer(boolean b, String string) {
		payload = new GenericAnswerPayload(b, string);
	}

	@Override
	public MessageType MsgType() {
		return IVBMessage.MessageType.LogoutMessageAnswer;
	}

	@Override
	public Object getPayload() {
		return payload;
	}

}
