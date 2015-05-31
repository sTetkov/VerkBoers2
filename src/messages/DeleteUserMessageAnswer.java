package messages;

public class DeleteUserMessageAnswer implements IVBMessage {

    GenericAnswerPayload payload;

    public DeleteUserMessageAnswer(boolean b, String string) {
	payload = new GenericAnswerPayload(b, string);
    }

    @Override
    public MessageType MsgType() {
	// TODO Auto-generated method stub
	return IVBMessage.MessageType.DeleteUserMessageAnswer;
    }

    @Override
    public Object getPayload() {
	// TODO Auto-generated method stub
	return payload;
    }

}
