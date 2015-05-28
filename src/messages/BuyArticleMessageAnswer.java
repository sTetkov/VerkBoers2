package messages;

public class BuyArticleMessageAnswer implements IVBMessage {

	@Override
	public MessageType MsgType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPayload() {
		return IVBMessage.MessageType.BuyArticleMessageAnswer;
	}

}
