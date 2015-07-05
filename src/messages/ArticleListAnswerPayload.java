package messages;

import java.io.Serializable;
import java.util.Vector;

import DBClasses.Article;

public class ArticleListAnswerPayload implements Serializable {

    public Vector<Article> getList() {
	return list;
    }

    public void setList(Vector<Article> list) {
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

    Vector<Article> list;
    boolean successful;
    String msg;

    public ArticleListAnswerPayload(boolean b, String string,
	    Vector<Article> articleList) {
	list = articleList;
	successful = b;
	msg = string;
    }

}
