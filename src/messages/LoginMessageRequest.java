package messages;

public class LoginMessageRequest implements IVBMessage {

    public LoginMessageRequestPayload payload;

    @Override
    public MessageType MsgType() {
	return IVBMessage.MessageType.LoginMessageRequest;
    }

    @Override
    public Object getPayload() {
	// TODO Auto-generated method stub
	return payload;
    }

}
