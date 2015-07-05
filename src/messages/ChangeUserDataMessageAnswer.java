package messages;

public class ChangeUserDataMessageAnswer implements IVBMessage {

    GenericAnswerPayload payload;

    public ChangeUserDataMessageAnswer(boolean b, String string) {
	payload = new GenericAnswerPayload(b, string);
    }

    @Override
    public MessageType MsgType() {
	// TODO Auto-generated method stub
	return IVBMessage.MessageType.ChangeUserDataMessageAnswer;
    }

    @Override
    public Object getPayload() {
	// TODO Auto-generated method stub
	return payload;
    }

}
