package messages;

public class ArticleListMessageRequestPayload {
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
