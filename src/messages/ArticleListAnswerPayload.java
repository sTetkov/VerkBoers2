package messages;

import java.io.Serializable;
import java.util.Vector;

import DBClasses.Article;

public class ArticleListAnswerPayload implements Serializable {

    public Vector<Pair<Article,ReducedUserData>> getList() {
	return list;
    }

    public void setList(Vector<Pair<Article,ReducedUserData>> list) {
	this.list = list;
    }

    public boolean isSuccessful() {
	return successful;
    }

    public void setSuccessful(boolean successful) {
	this.successful = successful;
    }

    public String getMsg() {
	return msg;
    }

    public void setMsg(String msg) {
	this.msg = msg;
    }

    private Vector<Pair<Article,ReducedUserData>> list;
    private boolean successful;
    String msg;

    public ArticleListAnswerPayload(boolean b, String string,
	    Vector<Pair<Article,ReducedUserData>> articleList) {
	list = articleList;
	successful = b;
	msg = string;
    }

}
