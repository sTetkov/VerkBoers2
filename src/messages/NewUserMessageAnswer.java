package messages;

public class NewUserMessageAnswer implements IVBMessage {

	private GenericAnswerPayload payload;

	public NewUserMessageAnswer(boolean b, String msg) {
		payload = new GenericAnswerPayload(b, msg);
	}

	@Override
	public MessageType MsgType() {
		// TODO Auto-generated method stub
		return IVBMessage.MessageType.NewUserMessageAnswer;
	}

	@Override
	public Object getPayload() {
		// TODO Auto-generated method stub
		return payload;
	}

}
