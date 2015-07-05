package messages;

import java.util.Vector;
import DBClasses.Article;

public class ArticleListMessageAnswer implements IVBMessage {

    ArticleListAnswerPayload payload;

    public ArticleListMessageAnswer(boolean b, String string,
	    Vector<Article> articleList) {
	payload = new ArticleListAnswerPayload(b, string, articleList);
    }

    @Override
    public MessageType MsgType() {
	// TODO Auto-generated method stub
	return IVBMessage.MessageType.ArticleListMessageAnswer;
    }

    @Override
    public Object getPayload() {
	return payload;
    }

}
