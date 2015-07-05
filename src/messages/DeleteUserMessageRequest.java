package messages;

public class DeleteUserMessageRequest implements IVBMessage {

    private int userID;
    public DeleteUserMessageRequest(int userID) {
	this.userID=userID;
    }

    @Override
    public MessageType MsgType() {
	return IVBMessage.MessageType.DeleteUserMessageRequest;
    }

    @Override
    public Object getPayload() {
return new Integer(userID);
    }

}
