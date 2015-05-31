package messages;

import DBClasses.Article;

public class AddArticleMessageRequest implements IVBMessage {

    private Article payload;

    public AddArticleMessageRequest(Article art) {
	payload = art;
    }

    @Override
    public MessageType MsgType() {
	// TODO Auto-generated method stub
	return IVBMessage.MessageType.AddArticleMessageRequest;
    }

    @Override
    public Object getPayload() {
	return payload;
    }

}
