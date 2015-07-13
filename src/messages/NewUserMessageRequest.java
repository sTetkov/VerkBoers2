package messages;

import DBClasses.User;
import DBClasses.SEPA;
import DBClasses.BankConnection;
import DBClasses.IBankData;

public class NewUserMessageRequest implements IVBMessage {

	private NewUserMessageRequestPayload payload;

	public NewUserMessageRequest(User usr, String pwd) {
		payload = new NewUserMessageRequestPayload();
		payload.setUser(usr);
		payload.setPassword(pwd);
	}

	@Override
	public MessageType MsgType() {
		// TODO Auto-generated method stub
		return IVBMessage.MessageType.NewUserMessageRequest;
	}

	@Override
	public Object getPayload() {
		// TODO Auto-generated method stub
		return payload;
	}

}
