package Server;

import DBClasses.Article;
import DBClasses.BankConnection;
import DBClasses.IBankData;
import DBClasses.SEPA;
import DBClasses.SQLDataUtilities;
import DBClasses.Transaction;
import DBClasses.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import messages.AddArticleMessageAnswer;
import messages.ArticleListMessageAnswer;
import messages.ArticleListMessageRequestPayload;
import messages.BuyArticleMessageAnswer;
import messages.BuyArticleMessageRequestPayload;
import messages.ChangeArticleMessageAnswer;
import messages.ChangeUserDataMessageAnswer;
import messages.DeleteArticleMessageAnswer;
import messages.GetUpdatedUserDataAnswer;
import messages.IVBMessage;
import messages.LoginMessageAnswer;
import messages.LoginMessageRequestPayload;
import messages.LogoutMessageAnswer;
import messages.NewUserConfirmationCodeAnswer;
import messages.NewUserConfirmationCodePayload;
import messages.NewUserMessageAnswer;
import messages.NewUserMessageRequestPayload;
import messages.OperationFailedAnswer;
import messages.Pair;
import messages.ReducedUserData;

public class ServerCore implements Runnable {

	private static DBConnector con;

	private static Vector<LoggedInUser> loggedInUsers;

	private static ServerConnectionManager scm = null;

	static Vector<LoggedInUser> getLoggedInUserList() {
		return loggedInUsers;
	}

	private IServerAnswerToRequest customer;
	private IVBMessage request;

	public static void initServerCore() {
		loggedInUsers = new Vector<LoggedInUser>();
		initDBConnector();
		new Thread(new ServerConnectionManager(10000)).start();
	}

	public ServerCore(IVBMessage request, IServerAnswerToRequest customer) {
		this.request = request;
		this.customer = customer;
	}

	private static boolean initDBConnector() {
		try {
			con = new DBConnector("mysql.minet.uni-jena.de", 3307, "ka42juf",
					"ka42juf", "Poponi8583");
			// con = new DBConnector("", 3306, "ka42juf", "ka42juf",
			// "Poponi8583");
			return true;
		} catch (InstantiationException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			con.Close();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void run() {
		IVBMessage answer = null;
		try {
			switch (request.MsgType()) {
			case LoginMessageRequest:
				answer = loginUser(request);
				break;
			case NewUserMessageRequest:
				answer = createUser(request);
				break;
			case NewUserConfirmationCodeRequest:
				answer = confirmUser(request);
				break;
			case DeleteUserMessageRequest:
				answer = deleteUser(request);
				break;
			case ChangeUserDataMessageRequest:
				answer = changeUserData(request);
				break;
			case AddArticleMessageRequest:
				answer = addArticle(request);
				break;
			case DeleteArticleMessageRequest:
				answer = deleteArticle(request);
				break;
			case ChangeArticleMessageRequest:
				answer = changeArticle(request);
				break;
			case ArticleListMessageRequest:
				answer = getArticleList(request);
				break;
			case BuyArticleMessageRequest:
				answer = buyArticle(request);
				break;
			case LogoutMessageRequest:
				answer = logoutUser(request);
				break;
			case GetUpdatedUserDataRequest:
				answer = getUpdatedUserData(request);
				break;
			default:
				throw new AssertionError(request.MsgType().name());
			}
		} catch (SQLException e) {
			try {
				con.RollBack();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			answer = new OperationFailedAnswer(
					"DB Operation Failure, if this persists please contact the administrators: \n"
							+ e.getMessage());
			customer.requestFailed(answer);
			return;
		}
		if (answer == null) {
			customer.requestFailed(null);
			return;
		}
		customer.requestExecuted(answer);
	}

	private IVBMessage loginUser(IVBMessage request) throws SQLException {
		LoginMessageRequestPayload payload = (LoginMessageRequestPayload) request
				.getPayload();
		String userName = payload.username;
		String pwd = payload.password;
		User user = null;
		LoginMessageAnswer answer = null;
		if (checkCredentials(userName, pwd) != -1) {
			user = getUser(userName);
		}
		if (user == null) {
			answer = new LoginMessageAnswer(false,
					"Wrong username or password", null);
			customer.requestExecuted(answer);
		} else {
			IBankData bnk = getBankData(user.getId());
			if (bnk != null)
				user.setBankData(bnk);
			answer = new LoginMessageAnswer(true, "", user);
			LoggedInUser liu=new LoggedInUser(user.getId(),user.geteMail());
			if(!loggedInUsers.contains(liu))
				loggedInUsers.add(liu);
			customer.requestExecuted(answer);
		}
		return answer;
	}

	private int checkCredentials(String userName, String pwd)
			throws SQLException {
		String query = "SELECT * FROM User_cred WHERE EMAIL='" + userName
				+ "' AND " + "Pwd='" + pwd + "';";
		System.out.println(query);
		ResultSet rs = con.ExecuteQuery(query);
		if (!rs.next()) {
			return -1;
		}
		return rs.getInt("userID");
	}

	private User getUser(String userName) throws SQLException {
		String query = "SELECT * FROM Nutzer WHERE EMAIL="
				+ SQLDataUtilities.getSQLDataFormat(userName) + ";";
		ResultSet rs = con.ExecuteQuery(query);
		if (!rs.next()) {
			throw new SQLException("DATA INCONSISTENCY");
		}
		User res = new User();
		res.fillFromResultSet(rs);
		query = "SELECT * FROM SEPA WHERE idNutzer=" + res.getId() + ";";
		rs = con.ExecuteQuery(query);
		if (rs.next()) {
			SEPA bnk = new SEPA();
			bnk.fillFromResultSet(rs);
			res.setBankData(bnk);
		} else {
			query = "SELECT * FROM Bankverbindung WHERE idNutzer="
					+ res.getId() + ";";
			rs = con.ExecuteQuery(query);
			if (rs.next()) {
				BankConnection bnk = new BankConnection();
				bnk.fillFromResultSet(rs);
				res.setBankData(bnk);
			}
		}
		return res;
	}

	private IVBMessage createUser(IVBMessage request) throws SQLException {
		NewUserMessageRequestPayload payload = ((NewUserMessageRequestPayload) request
				.getPayload());
		String cmd = payload.getUser().getCreateStatement();
		con.AddCommand(cmd);
		con.ExecuteAndCommit();
		User usr = getUser(payload.getUser().geteMail());
		if (payload.getUser().getBankData() != null) {
			if (payload.getUser().getBankData().getType() == "SEPA") {
				SEPA bnk = ((SEPA) payload.getUser().getBankData());
				bnk.setUserId(usr.getId());
				con.AddCommand(bnk.getCreateStatement());
			} else {
				BankConnection bnk = ((BankConnection) payload.getUser()
						.getBankData());
				bnk.setUserId(usr.getId());
				con.AddCommand(bnk.getCreateStatement());
			}
		}
		Random rnd = new Random();
		String code = "" + rnd.nextInt(999999);
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		cmd = "INSERT INTO Approval_List (userID,EMAIL,Pwd,CODE,Timestmp)"
				+ "VALUES ("
				+ usr.getId()
				+ ", "
				+ SQLDataUtilities.getSQLDataFormat(usr.geteMail())
				+ ", "
				+ SQLDataUtilities.getSQLDataFormat(payload.getPassword())
				+ ", "
				+ SQLDataUtilities.getSQLDataFormat(code)
				+ ", "
				+ SQLDataUtilities.getSQLDataFormat(new java.sql.Timestamp(now
						.getTime())) + ");";
		System.out.println(cmd);
		con.AddCommand(cmd);
		con.Execute();
		MailManager mm = new MailManager(usr.geteMail(), code);
		new Thread(mm).start();
		return new NewUserMessageAnswer(true, "");
	}

	private IVBMessage confirmUser(IVBMessage request) throws SQLException {
		NewUserConfirmationCodePayload payload = ((NewUserConfirmationCodePayload) request
				.getPayload());
		String query = "SELECT * FROM Approval_List WHERE EMAIL="
				+ SQLDataUtilities.getSQLDataFormat(payload.geteMail())
				+ " AND CODE="
				+ SQLDataUtilities.getSQLDataFormat(payload.getCode()) + ";";
		ResultSet rs = con.ExecuteQuery(query);
		if (!rs.next()) {
			return new NewUserConfirmationCodeAnswer(false,
					"Wrong code or wrong E-Mail");
		}
		String cmd = "INSERT INTO User_cred (userID,EMAIL,Pwd)" + "VALUES("
				+ SQLDataUtilities.getSQLDataFormat(rs.getInt("userID")) + ", "
				+ SQLDataUtilities.getSQLDataFormat(rs.getString("EMAIL"))
				+ ", " + SQLDataUtilities.getSQLDataFormat(rs.getString("Pwd"))
				+ ");";
		con.AddCommand(cmd);
		System.out.println(cmd);
		cmd = "UPDATE Nutzer SET Zustand='OK' WHERE idNutzer="
				+ rs.getInt("userID") + ";";
		con.AddCommand(cmd);
		System.out.println(cmd);
		cmd = "DELETE FROM Approval_List WHERE EMAIL="
				+ SQLDataUtilities.getSQLDataFormat(payload.geteMail())
				+ " AND CODE="
				+ SQLDataUtilities.getSQLDataFormat(payload.getCode()) + ";";
		con.AddCommand(cmd);
		System.out.println(cmd);
		con.Execute();
		con.Commit();
		return new NewUserConfirmationCodeAnswer(true, "");
	}

	private IVBMessage deleteUser(IVBMessage request) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	private IVBMessage changeUserData(IVBMessage request) throws SQLException {
		User user = (User) request.getPayload();
		String query = user.getUpdateStatement();
		System.out.println(query);
		boolean existingBankData = false;
		IBankData ibd = getBankData(user.getId());
		if (ibd != null)
			existingBankData = ibd.getType().equals(
					user.getBankData().getType());
		con.AddCommand(query);
		if (!existingBankData) {
			String bnkQ = "";
			if (user.getBankData().getType().equals("SEPA"))
				bnkQ = ((SEPA) user.getBankData()).getCreateStatement();

			else
				bnkQ = ((BankConnection) user.getBankData())
						.getCreateStatement();
			System.out.println(bnkQ);
			con.AddCommand(bnkQ);
		} else {
			if (user.getBankData() != null) {
				String bnkQ = "";
				if (user.getBankData().getType().equals("SEPA"))
					bnkQ = ((SEPA) user.getBankData()).getUpdateStatement();
				else
					bnkQ = ((BankConnection) user.getBankData())
							.getUpdateStatement();
				System.out.println(bnkQ);
				con.AddCommand(bnkQ);
			}
		}
		con.ExecuteAndCommit();
		ChangeUserDataMessageAnswer answer = new ChangeUserDataMessageAnswer(
				true, "");
		return answer;
	}

	private IVBMessage addArticle(IVBMessage request) throws SQLException {
		Article art = (Article) request.getPayload();
		con.AddCommand(art.getCreateStatement());
		con.ExecuteAndCommit();
		AddArticleMessageAnswer answer = new AddArticleMessageAnswer(true,
				"Article added");
		return answer;
	}

	private IVBMessage deleteArticle(IVBMessage request) throws SQLException {
		Article art = getArticle(((Integer) request.getPayload()).intValue());
		if (art == null) {
			OperationFailedAnswer answer = new OperationFailedAnswer(
					"Could not find article");
			return answer;
		}
		art.setState("DELETED");
		con.AddCommand(art.getUpdateStatement());
		con.ExecuteAndCommit();
		DeleteArticleMessageAnswer answer = new DeleteArticleMessageAnswer(
				true, "Article deleted");
		return answer;
	}

	private IVBMessage changeArticle(IVBMessage request) throws SQLException {
		String cmd = ((Article) request.getPayload()).getUpdateStatement();
		System.out.println(cmd);
		con.AddCommand(cmd);
		con.ExecuteAndCommit();
		ChangeArticleMessageAnswer answer = new ChangeArticleMessageAnswer(
				true, "Article modified");
		return answer;
	}

	private IVBMessage getArticleList(IVBMessage request) throws SQLException {
		ArticleListMessageRequestPayload data = (ArticleListMessageRequestPayload) (request
				.getPayload());
		String query = "SELECT u.Vorname as Vorname, u.Nachname as Nachname, u.Ort as Ort, "
				+ "a.idArtikel as idArtikel, a.Bezeichnung as Bezeichnung, a.Beschreibung as Beschreibung, "
				+ "a.Gewicht as Gewicht, a.Anzahl as Anzahl, a.MwSt as MwSt, "
				+ "a.Preis_Brutto as Preis_Brutto, a.Preis_Netto as Preis_Netto, a.AblaufDatum as AblaufDatum, "
				+ "a.idNutzer as idNutzer, a.Zustand as Zustand FROM Artikel a JOIN Nutzer u ON a.idNutzer=u.idNutzer WHERE u.idNutzer";
		if (data.isShowUserIdArticles()) {
			query += "=" + data.getUserId() + " AND a.Zustand='OK';";
		} else {
			query += " != " + data.getUserId()
					+ " AND a.Anzahl>0 AND a.Zustand='OK';";
		}
		System.out.println(query);
		ResultSet rs = con.ExecuteQuery(query);
		Vector<Pair<Article, ReducedUserData>> list = new Vector<Pair<Article, ReducedUserData>>();
		while (rs.next()) {
			Article art = new Article(data.getUserId());
			ReducedUserData rud = null;
			if (!data.isShowUserIdArticles())
				rud = new ReducedUserData(rs.getString("Vorname") + " "
						+ rs.getString("Nachname"), rs.getString("Ort"));
			art.fillFromResultSet(rs);
			list.add(new Pair<Article, ReducedUserData>(art, rud));
		}
		ArticleListMessageAnswer answer = new ArticleListMessageAnswer(true,
				"", list);
		return answer;
	}

	private IVBMessage buyArticle(IVBMessage request) throws SQLException {
		BuyArticleMessageRequestPayload payload = ((BuyArticleMessageRequestPayload) request
				.getPayload());
		Article art = payload.getArticle();
		Transaction trns = new Transaction(art.getIdUser(),
				payload.getUserId(), art);
		trns.setAmount(payload.getAmount());
		String cmd = trns.getCreateStatement();
		art.setAmount(art.getAmount() - payload.getAmount());
		con.AddCommand(cmd);
		cmd = art.getUpdateStatement();
		con.AddCommand(cmd);
		con.Execute();
		con.Commit();
		sendTransactionMails(trns);
		BuyArticleMessageAnswer answer = new BuyArticleMessageAnswer();
		return answer;
	}

	private IVBMessage logoutUser(IVBMessage request) throws SQLException {
		User user=getUser(((Integer)request.getPayload()).intValue());
		loggedInUsers.remove(new LoggedInUser(user.getId(),user.geteMail()));
		LogoutMessageAnswer answer = new LogoutMessageAnswer(true,
				"User logged out");
		return answer;
	}

	private void sendTransactionMails(Transaction trns){
		try{
		Article art=getArticle(trns.getArticleId());
		User seller=getUser(trns.getSellerId());
		User buyer=getUser(trns.getBuyerId());
		MailManager mm=new MailManager(art,trns,seller,buyer);
		new Thread(mm).start();
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
			System.out.println("Could not send transaction mails");
		}

	}

	private Article getArticle(int articleID) {
		try {
			Article art = null;
			String query = "SELECT * FROM Artikel WHERE idArtikel=" + articleID
					+ ";";
			System.out.println(query);
			ResultSet rs = con.ExecuteQuery(query);
			while (rs.next()) {
				art = new Article(0);
				art.fillFromResultSet(rs);
			}
			return art;
		} catch (SQLException ex) {
			Logger.getLogger(ServerCore.class.getName()).log(Level.SEVERE,
					null, ex);
			return null;
		}
	}

	private IBankData getBankData(int id) {
		IBankData res = null;
		try {

			String query = "SELECT * FROM SEPA WHERE idNutzer=" + id + ";";
			ResultSet rs = con.ExecuteQuery(query);
			if (rs.next()) {
				res = new SEPA();
				((SEPA) res).fillFromResultSet(rs);
				return res;
			}
			query = "SELECT * FROM Bankverbindung WHERE idNutzer=" + id + ";";
			rs = con.ExecuteQuery(query);
			if (rs.next()) {
				res = new BankConnection();
				((BankConnection) res).fillFromResultSet(rs);
				return res;
			}
		} catch (SQLException ex) {
			Logger.getLogger(ServerCore.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return res;
	}

	private IVBMessage getUpdatedUserData(IVBMessage request)
			throws SQLException {
		int userId = ((Integer) request.getPayload()).intValue();
		User user = getUser(userId);
		if (user != null) {
			GetUpdatedUserDataAnswer answer = new GetUpdatedUserDataAnswer(user);
			return answer;
		} else {
			OperationFailedAnswer answer = new OperationFailedAnswer(
					"Could not find user");
			return answer;
		}
	}

	private User getUser(int userId) throws SQLException {
		String query = "SELECT * FROM Nutzer WHERE idNutzer=" + userId + ";";
		ResultSet rs = con.ExecuteQuery(query);
		if (!rs.next()) {
			throw new SQLException("DATA INCONSISTENCY");
		}
		User res = new User();
		res.fillFromResultSet(rs);
		query = "SELECT * FROM SEPA WHERE idNutzer=" + res.getId() + ";";
		rs = con.ExecuteQuery(query);
		if (rs.next()) {
			SEPA bnk = new SEPA();
			bnk.fillFromResultSet(rs);
			res.setBankData(bnk);
		} else {
			query = "SELECT * FROM Bankverbindung WHERE idNutzer="
					+ res.getId() + ";";
			rs = con.ExecuteQuery(query);
			if (rs.next()) {
				BankConnection bnk = new BankConnection();
				bnk.fillFromResultSet(rs);
				res.setBankData(bnk);
			}
		}
		return res;
	}

	static boolean loginAdmin(String username, String pwd) {
		try {
			String query = "SELECT * FROM Admin_login WHERE username='"
					+ username + "' AND pwd='" + pwd + "';";
			ResultSet rs = con.ExecuteQuery(query);
			if (!rs.next())
				return false;
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(ServerCore.class.getName()).log(Level.SEVERE,
					null, ex);
			return false;
		}

	}

	static Vector<User> getUserList() {
		Vector<User> res = new Vector<User>();
		try {
			ResultSet rs = con.ExecuteQuery("SELECT * FROM Nutzer;");
			while (rs.next()) {
				User user = new User();
				user.fillFromResultSet(rs);
				res.add(user);
			}
		} catch (SQLException ex) {
			Logger.getLogger(ServerCore.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return res;
	}

	static void setUserState(User user, String state) throws SQLException {
		user.setState(state);
		con.AddCommand(user.getUpdateStatement());
		con.ExecuteAndCommit();
	}

	static Vector<Pair<Article, ReducedUserData>> getArticleList() {
		String query = "SELECT u.Vorname as Vorname, u.Nachname as Nachname, u.Ort as Ort, "
				+ "a.idArtikel as idArtikel, a.Bezeichnung as Bezeichnung, a.Beschreibung as Beschreibung, "
				+ "a.Gewicht as Gewicht, a.Anzahl as Anzahl, a.MwSt as MwSt, "
				+ "a.Preis_Brutto as Preis_Brutto, a.Preis_Netto as Preis_Netto, a.AblaufDatum as AblaufDatum, "
				+ "a.idNutzer as idNutzer, a.Zustand as Zustand FROM Artikel a JOIN Nutzer u ON a.idNutzer=u.idNutzer;";
		System.out.println(query);
		Vector<Pair<Article, ReducedUserData>> list = new Vector<Pair<Article, ReducedUserData>>();
		try {
			ResultSet rs = con.ExecuteQuery(query);
			while (rs.next()) {
				Article art = new Article(0);
				ReducedUserData rud = null;
				rud = new ReducedUserData(rs.getString("Vorname") + " "
						+ rs.getString("Nachname"), rs.getString("Ort"));
				art.fillFromResultSet(rs);
				list.add(new Pair<Article, ReducedUserData>(art, rud));
			}
		} catch (SQLException ex) {
			Logger.getLogger(ServerCore.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return list;
	}

	static void setArticleState(Article article, String state)
			throws SQLException {
		article.setState(state);
		con.AddCommand(article.getUpdateStatement());
		con.ExecuteAndCommit();
	}
}
