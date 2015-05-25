package messages;

import DBClasses.Article;

public class ChangeAreticleMessageRequest implements IVBMessage {

    private Article payload;
    public ChangeAreticleMessageRequest(Article art) {
	payload=art;
    }

    @Override
    public MessageType MsgType() {
	return IVBMessage.MessageType.ChangeAreticleMessageRequest;
    }

    @Override
    public Object getPayload() {
	return payload;
    }

}
