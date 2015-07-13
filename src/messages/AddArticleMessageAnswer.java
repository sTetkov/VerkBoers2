package messages;

public class AddArticleMessageAnswer implements IVBMessage {

	GenericAnswerPayload payload;

	public AddArticleMessageAnswer(boolean b, String string) {
		payload = new GenericAnswerPayload(b, string);
	}

	@Override
	public MessageType MsgType() {
		return IVBMessage.MessageType.AddArticleMessageAnswer;
	}

	@Override
	public Object getPayload() {
		return payload;
	}

}
