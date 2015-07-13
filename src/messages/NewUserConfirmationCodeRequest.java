package messages;

public class NewUserConfirmationCodeRequest implements IVBMessage {

	private NewUserConfirmationCodePayload payload;

	public NewUserConfirmationCodeRequest(String eMail, String code) {
		payload = new NewUserConfirmationCodePayload();
		payload.setCode(code);
		payload.seteMail(eMail);
	}

	@Override
	public MessageType MsgType() {
		// TODO Auto-generated method stub
		return IVBMessage.MessageType.NewUserConfirmationCodeRequest;
	}

	@Override
	public Object getPayload() {
		// TODO Auto-generated method stub
		return payload;
	}

}
