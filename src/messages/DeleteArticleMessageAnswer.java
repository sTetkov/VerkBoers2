package messages;

public class DeleteArticleMessageAnswer implements IVBMessage {

    GenericAnswerPayload payload;

    public DeleteArticleMessageAnswer(boolean b, String string) {
	payload = new GenericAnswerPayload(b, string);
    }

    @Override
    public MessageType MsgType() {
	return IVBMessage.MessageType.DeleteArticleMessageAnswer;
    }

    @Override
    public Object getPayload() {
	return payload;
    }

}
