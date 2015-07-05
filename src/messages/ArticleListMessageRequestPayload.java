package messages;

import java.io.Serializable;

public class ArticleListMessageRequestPayload implements Serializable {
    private int userId;

    public int getUserId() {
	return userId;
    }

    public void setUserId(int userId) {
	this.userId = userId;
    }

    public boolean isShowUserIdArticles() {
	return showUserIdArticles;
    }

    public void setShowUserIdArticles(boolean showUserIdArticles) {
	this.showUserIdArticles = showUserIdArticles;
    }

    private boolean showUserIdArticles;

}
