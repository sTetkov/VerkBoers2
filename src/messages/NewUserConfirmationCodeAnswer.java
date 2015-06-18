package messages;

public class NewUserConfirmationCodeAnswer implements IVBMessage {

	private GenericAnswerPayload payload;

	public NewUserConfirmationCodeAnswer(boolean b, String string) {
		payload = new GenericAnswerPayload(b, string);
	}

	@Override
	public MessageType MsgType() {
		// TODO Auto-generated method stub
		return IVBMessage.MessageType.NewUserConfirmationCodeAnswer;
	}

	@Override
	public Object getPayload() {
		// TODO Auto-generated method stub
		return payload;
	}

}
