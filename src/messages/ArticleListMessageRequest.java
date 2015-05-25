package messages;

public class ArticleListMessageRequest implements IVBMessage {

    ArticleListMessageRequestPayload payload;
    public ArticleListMessageRequest(int userId, boolean getArticlesFormUser) {
	payload = new ArticleListMessageRequestPayload();
	payload.setShowUserIdArticles(getArticlesFormUser);
	payload.setUserId(userId);
    }

    @Override
    public MessageType MsgType() {
	return IVBMessage.MessageType.AddArticleMessageRequest;
    }

    @Override
    public Object getPayload() {
	return payload;
    }

}
