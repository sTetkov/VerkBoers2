package Client;

import messages.ArticleListMessageRequest;
import messages.IVBMessage;
import messages.LoginMessageRequest;
import messages.LoginMessageRequestPayload;
import messages.LogoutMessageRequest;
import DBClasses.Article;
import DBClasses.User;
import HelpCLasses.ClientConnectionManager;
import HelpCLasses.ICSMessageEventReceiver;
import HelpCLasses.IClientGUIListener;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class ClientCore implements ICSMessageEventReceiver {
	
	private ClientConnectionManager ccm;
	private User user;
	private int opID;
	private Map<Integer,IClientGUIListener> map;
	
	public ClientCore()
	{
		 map=new HashMap<Integer,IClientGUIListener>();
	}
	
	public void Login(String username,String pwd, IClientGUIListener customer)
	{
		opID++;
		LoginMessageRequest msg=new LoginMessageRequest();
	    msg.payload = new LoginMessageRequestPayload();
	    msg.payload.username = username;
	    msg.payload.password = pwd;
	    ccm.setUsername(username);
	    ccm.setPwd(pwd);
	    ccm.addMessage(opID,msg, this);
	    map.put(new Integer(opID), customer);
	}
	public void Logout(int clientID, IClientGUIListener customer)
	{
		opID++;
		LogoutMessageRequest msg=new LogoutMessageRequest(user.getId());
	    ccm.addMessage(opID,msg, this);
	    map.put(new Integer(opID), customer);
	}
	
	public void GetArticleList(int clientID,IClientGUIListener customer)
	{
		opID++;
    	ArticleListMessageRequest req=new ArticleListMessageRequest(user.getId(),true);
		ccm.addMessage(opID,req, this);
		map.put(new Integer(opID), customer);
    }
	
	public void GetOfferList(int clientID,IClientGUIListener customer)
	{
		opID++;
    	ArticleListMessageRequest req=new ArticleListMessageRequest(user.getId(),false);
		ccm.addMessage(opID,req, this);
		map.put(new Integer(opID), customer);
	}
	
	public void GetArticleDetails(int articleID,IClientGUIListener customer)
	{
		//Not required at the moment
	}
	
	public void ChangeArticleData(Article article,IClientGUIListener customer)
	{
		
	}
	public void DeleteArticle(int articleID,IClientGUIListener customer){}
	public void BuyArticle(int articleID,float amount,IClientGUIListener customer){}
	
	public void ChangeUserData(User user,IClientGUIListener customer){}
	public void DeleteUser(int userID,IClientGUIListener customer){}
	
	public void CreateUser(User user, String username, String pwd,IClientGUIListener customer){}
	public void ConfirmUser(String username, String pwd, String code,IClientGUIListener customer){}
	
	@Override
	public void MessageSent(int opID) {
		IClientGUIListener customer=(IClientGUIListener) map.get(new Integer(opID));
		// TODO Auto-generated method stub
		
	}
	@Override
	public void AnswerReceived(int opID, IVBMessage answer) {
		IClientGUIListener customer=(IClientGUIListener) map.get(new Integer(opID));
		switch (answer.MsgType())
		{
		case AddArticleMessageAnswer:
			break;
		case AddArticleMessageRequest:
			break;
		case ArticleListMessageAnswer:
			break;
		case ArticleListMessageRequest:
			break;
		case BuyArticleMessageAnswer:
			break;
		case BuyArticleMessageRequest:
			break;
		case ChangeAreticleMessageAnswer:
			break;
		case ChangeArticleMessageRequest:
			break;
		case ChangeUserDataMessageAnswer:
			break;
		case ChangeUserDataMessageRequest:
			break;
		case DeleteArticleMessageAnswer:
			break;
		case DeleteArticleMessageRequest:
			break;
		case DeleteUserMessageAnswer:
			break;
		case DeleteUserMessageRequest:
			break;
		case LoginMessageAnswer:
			break;
		case LoginMessageRequest:
			break;
		case LogoutMessageAnswer:
			break;
		case LogoutMessageRequest:
			break;
		case NewUserConfirmationCodeAnswer:
			break;
		case NewUserConfirmationCodeRequest:
			break;
		case NewUserMessageAnswer:
			break;
		case NewUserMessageRequest:
			break;
		case OperationFailedAnswer:
			break;
		default:
			break;
		}
		// TODO Auto-generated method stub
		
	}
	@Override
	public void CommunicationError(int opID, IVBMessage answer) {
		IClientGUIListener customer=(IClientGUIListener) map.get(new Integer(opID));
		// TODO Auto-generated method stub
		
	}

}
