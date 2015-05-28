package messages;

import DBClasses.User;
import DBClasses.SEPA;
import DBClasses.BankConnection;
import DBClasses.IBankData;

public class NewUserMessageRequest implements IVBMessage {

	private User payload;

	public NewUserMessageRequest(User usr) {
		payload = usr;
	}

	@Override
	public MessageType MsgType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPayload() {
		// TODO Auto-generated method stub
		return payload;
	}

}
