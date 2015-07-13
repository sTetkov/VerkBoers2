package messages;

public class LogoutMessageRequest implements IVBMessage {

	private int userID;

	public LogoutMessageRequest(int userID) {
		this.userID = userID;
	}

	@Override
	public MessageType MsgType() {
		return IVBMessage.MessageType.LogoutMessageRequest;
	}

	@Override
	public Object getPayload() {
		return new Integer(userID);
	}

}
