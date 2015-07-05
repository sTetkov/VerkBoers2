package messages;

public class BuyArticleMessageAnswer implements IVBMessage {

    @Override
    public MessageType MsgType() {
	return IVBMessage.MessageType.BuyArticleMessageAnswer;
    }

    @Override
    public Object getPayload() {
	return null;
    }

}
