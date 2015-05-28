package messages;

public class ChangeArticleMessageAnswer implements IVBMessage {

	GenericAnswerPayload payload;

	public ChangeArticleMessageAnswer(boolean b, String string) {
		payload = new GenericAnswerPayload(b, string);
	}

	@Override
	public MessageType MsgType() {
		return IVBMessage.MessageType.ChangeAreticleMessageAnswer;
	}

	@Override
	public Object getPayload() {
		return payload;
	}

}
