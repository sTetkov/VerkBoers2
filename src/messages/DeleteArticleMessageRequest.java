package messages;

public class DeleteArticleMessageRequest implements IVBMessage {

	private int artID;

	public DeleteArticleMessageRequest(int articleID) {
		artID = articleID;
	}

	@Override
	public MessageType MsgType() {
		return IVBMessage.MessageType.DeleteArticleMessageRequest;
	}

	@Override
	public Object getPayload() {
		return new Integer(artID);
	}

}
