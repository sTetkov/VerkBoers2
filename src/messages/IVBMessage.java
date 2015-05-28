package messages;

import java.io.Serializable;

public interface IVBMessage extends Serializable {
	public enum MessageType {
		LoginMessageRequest, LoginMessageAnswer, NewUserMessageRequest, NewUserMessageAnswer, NewUserConfirmationCodeRequest, NewUserConfirmationCodeAnswer, DeleteUserMessageRequest, DeleteUserMessageAnswer, ChangeUserDataMessageRequest, ChangeUserDataMessageAnswer, AddArticleMessageRequest, AddArticleMessageAnswer, DeleteArticleMessageRequest, DeleteArticleMessageAnswer, ChangeArticleMessageRequest, ChangeAreticleMessageAnswer, ArticleListMessageRequest, ArticleListMessageAnswer, BuyArticleMessageRequest, BuyArticleMessageAnswer, LogoutMessageRequest, LogoutMessageAnswer, OperationFailedAnswer
	}

	public MessageType MsgType();

	public Object getPayload();

}
