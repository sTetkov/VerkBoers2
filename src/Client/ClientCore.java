package Client;

import messages.ArticleListMessageRequest;
import messages.ChangeArticleMessageRequest;
import messages.ChangeUserDataMessageRequest;
import messages.DeleteArticleMessageRequest;
import messages.DeleteUserMessageRequest;
import messages.IVBMessage;
import messages.LoginMessageRequest;
import messages.LoginMessageRequestPayload;
import messages.LogoutMessageRequest;
import messages.NewUserConfirmationCodeRequest;
import messages.NewUserMessageRequest;
import messages.Pair;
import messages.ReducedUserData;
import DBClasses.Article;
import DBClasses.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JLabel;
import messages.LoginMessageAnswerPayload;

public class ClientCore implements ICSMessageEventReceiver {

    private int opID;
    private Map<Integer, IClientGUIListener> map;
    private ClientConnectionManager ccm;
    
    private User user;
    private Vector<Pair<Article,ReducedUserData>> offers;
    private Vector<Pair<Article,ReducedUserData>> userArticles;

    public User getUser() {
        return user;
    }

    public Vector<Pair<Article,ReducedUserData>> getOffers() {
        return offers;
    }

    public Vector<Pair<Article,ReducedUserData>> getUserArticles() {
        return userArticles;
    }


    public ClientCore() {
    	map = new HashMap<Integer, IClientGUIListener>();
    }

    public void Login(String username, String pwd, IClientGUIListener customer) {
	opID++;
	LoginMessageRequest msg = new LoginMessageRequest();
	msg.payload = new LoginMessageRequestPayload();
	msg.payload.username = username;
	msg.payload.password = pwd;
	ccm.setUsername(username);
	ccm.setPwd(pwd);
	ccm.addMessage(opID, msg, this);
	map.put(new Integer(opID), customer);
    }

    public void Logout(int clientID, IClientGUIListener customer) {
	opID++;
	LogoutMessageRequest msg = new LogoutMessageRequest(user.getId());
	ccm.addMessage(opID, msg, this);
	map.put(new Integer(opID), customer);
	ccm.clearCredentials();
	user=null;
	offers=null;
	userArticles=null;
    }

    public void GetArticleList(int clientID, IClientGUIListener customer) {
	opID++;
	ArticleListMessageRequest req = new ArticleListMessageRequest(
		user.getId(), true);
	ccm.addMessage(opID, req, this);
	map.put(new Integer(opID), customer);
    }

    public void GetOfferList(int clientID, IClientGUIListener customer) {
	opID++;
	ArticleListMessageRequest req = new ArticleListMessageRequest(
		user.getId(), false);
	ccm.addMessage(opID, req, this);
	map.put(new Integer(opID), customer);
    }

    public void GetArticleDetails(int articleID, IClientGUIListener customer) {
	// Not required at the moment
    }

    public void ChangeArticleData(Article article, IClientGUIListener customer) {
	opID++;
	ChangeArticleMessageRequest caMsg = new ChangeArticleMessageRequest(article);
	ccm.addMessage(opID,caMsg,this);
	map.put(new Integer(opID), customer);
    }

    public void DeleteArticle(int articleID, IClientGUIListener customer) {
	opID++;
	DeleteArticleMessageRequest delMsgReq=new DeleteArticleMessageRequest(articleID);
	ccm.addMessage(opID,delMsgReq,this);
	map.put(new Integer(opID), customer);
    }

    public void BuyArticle(Article article, float amount,IClientGUIListener customer) {
	opID++;
	ccm.addMessage(opID,new messages.BuyArticleMessageRequest(
			user.getId(), article, amount),this);
	map.put(new Integer(opID), customer);
    }

    public void ChangeUserData(User user, IClientGUIListener customer) {
	opID++;
	ChangeUserDataMessageRequest numrMsg = new ChangeUserDataMessageRequest(user);
	ccm.addMessage(opID, numrMsg, this);
	map.put(new Integer(opID), customer);
    }

    public void DeleteUser(int userID, IClientGUIListener customer) {
	opID++;
	DeleteUserMessageRequest msg=new DeleteUserMessageRequest(userID);
	ccm.addMessage(opID, msg, this);
	map.put(new Integer(opID), customer);
    }

    public void CreateUser(User user, String username, String pwd,
	    IClientGUIListener customer) {
	opID++;
	user.setState("UNCONFIRMED");
	NewUserMessageRequest msg = new NewUserMessageRequest(user,pwd);
	ccm.addMessage(opID, msg, this);
	map.put(new Integer(opID), customer);
    }

    public void ConfirmUser(String username, String pwd, String code,
	    IClientGUIListener customer) {
	opID++;
	NewUserConfirmationCodeRequest msg=new NewUserConfirmationCodeRequest(username,code);
	ccm.addMessage(opID, msg, this);
	map.put(new Integer(opID), customer);
    }

    @Override
    public void MessageSent(int opID) {
	IClientGUIListener customer = (IClientGUIListener) map.get(new Integer(
		opID));
	customer.confirmMessageSent();

    }

    @Override
    public void AnswerReceived(int opID, IVBMessage answer) {
	IClientGUIListener customer = (IClientGUIListener) map.get(new Integer(
		opID));
	switch (answer.MsgType()) {
	case AddArticleMessageAnswer:
	    customer.positiveAnswerReceived(null);
	    break;
	case ArticleListMessageAnswer:
	    customer.positiveAnswerReceived(answer.getPayload());
	    break;
	case BuyArticleMessageAnswer:
	    customer.positiveAnswerReceived(null);
	    break;
	case ChangeAreticleMessageAnswer:
	    customer.positiveAnswerReceived(null);
	    break;
	case ChangeUserDataMessageAnswer:
	    customer.positiveAnswerReceived(null);
	    break;
	case DeleteArticleMessageAnswer:
	    customer.positiveAnswerReceived(null);
	    break;
	case DeleteUserMessageAnswer:
	    customer.positiveAnswerReceived(null);
	    break;
	case LoginMessageAnswer:
            if(((LoginMessageAnswerPayload)answer.getPayload()).isLoginSucces())
            {
                customer.positiveAnswerReceived(answer.getPayload());
                user=((LoginMessageAnswerPayload)answer.getPayload()).getUserData();
            }
            else
            {
                customer.failureAnswerReceived("Login failed: "+((LoginMessageAnswerPayload)answer.getPayload()).getFailureReason());
            }
	    break;
	case LogoutMessageAnswer:
	    customer.positiveAnswerReceived(null);
	    break;
	case NewUserConfirmationCodeAnswer:
	    customer.positiveAnswerReceived(null);
	    break;
	case NewUserMessageAnswer:
	    customer.positiveAnswerReceived(null);
	    break;
	case OperationFailedAnswer:
	    customer.failureAnswerReceived(answer.getPayload());
	    break;
	default:
	    break;
	}
    }

    @Override
    public void CommunicationError(int opID, IVBMessage answer) {
	IClientGUIListener customer = (IClientGUIListener) map.get(new Integer(
		opID));
	customer.communicationErrorReceived(answer.getPayload());
    }

}
