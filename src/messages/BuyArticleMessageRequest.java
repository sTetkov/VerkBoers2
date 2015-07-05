package messages;

import DBClasses.Article;

public class BuyArticleMessageRequest implements IVBMessage {

    BuyArticleMessageRequestPayload payload;

    public BuyArticleMessageRequest(int id, Article article, float amt) {
	payload = new BuyArticleMessageRequestPayload();
	payload.setAmount(amt);
	payload.setArticle(article);
	payload.setUserId(id);
    }

    @Override
    public MessageType MsgType() {
	return IVBMessage.MessageType.BuyArticleMessageRequest;
    }

    @Override
    public Object getPayload() {
	return payload;
    }

}
