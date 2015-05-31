package messages;

import DBClasses.Article;

public class ChangeArticleMessageRequest implements IVBMessage {

    private Article payload;

    public ChangeArticleMessageRequest(Article art) {
	payload = art;
    }

    @Override
    public MessageType MsgType() {
	return IVBMessage.MessageType.ChangeArticleMessageRequest;
    }

    @Override
    public Object getPayload() {
	return payload;
    }

}
