package messages;

import DBClasses.User;

public class ChangeUserDataMessageRequest implements IVBMessage {

	private DBClasses.User payload;

	public ChangeUserDataMessageRequest(User usr) {
		payload = usr;
	}

	@Override
	public MessageType MsgType() {
		// TODO Auto-generated method stub
		return IVBMessage.MessageType.ChangeUserDataMessageRequest;
	}

	@Override
	public Object getPayload() {
		// TODO Auto-generated method stub
		return payload;
	}

}
