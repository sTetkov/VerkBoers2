package messages;

public class OperationFailedAnswer implements IVBMessage {

    private String msg;

    public OperationFailedAnswer(String string) {
	msg = string;
    }

    @Override
    public MessageType MsgType() {
	// TODO Auto-generated method stub
	return IVBMessage.MessageType.OperationFailedAnswer;
    }

    @Override
    public Object getPayload() {
	// TODO Auto-generated method stub
	return msg;
    }

    public String getReasons() {
	return msg;
    }

}
