import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Random;
import java.util.Vector;

import DBClasses.*;
import messages.*;

public class Server implements Runnable {

    private static DBConnector con;

    private int port;
    private Socket socket;
    private boolean waitingForConnection;

    private static Vector<LoggedInUser> loggedInUsers;

    private boolean successful;

    private String message;

    public Server(int serverPort) {
	this.port = serverPort;
	waitingForConnection = true;
    }

    public Server(Socket socket) {
	this.socket = socket;
	waitingForConnection = false;
    }

    public static void main(String[] args) {
	loggedInUsers = new Vector<LoggedInUser>();
	System.out.println("Server starting...");
	System.out.println("Initializing DB Connector...");
	if (!initDBConnector()) {
	    System.out.println("Failed to initialize the DB Connector");
	    return;
	}
	System.out.println("Starting listener thread...");
	new Thread(new Server(Integer.parseInt(args[0]))).start();

	System.out.println("Starting main loop...");
	while (true) {
	    boolean loggedIn = false;
	    System.out.println("Insert username:");
	    String uName = System.console().readLine();
	    System.out.println("Insert password:");
	    String pwd = System.console().readLine();
	    loggedIn = loginAdmin(uName, pwd);
	    while (loggedIn) {
		System.out.println("Insert command (help for a list):");
		String str = System.console().readLine();
		String[] cmd = str.split(" ");
		System.out.println("Echo :" + cmd[0]);
		switch (cmd[0].toLowerCase()) {
		case "quit":
		    System.exit(0);
		case "help":
		    showCommandList();
		    break;
		case "logout":
		    loggedIn = false;
		    break;
		case "sqlquery":
		    str = new String();
		    for (int i = 1; i < cmd.length; i++)
			str += " " + cmd[i];
		    str += ";";
		    System.out.println("ECHO: " + str);
		    try {
			PrintOutQueryResults(con.ExecuteQuery(str));
		    } catch (SQLException e) {
			e.printStackTrace();
		    }
		case "showusers":
		    java.util.Calendar calendar = java.util.Calendar
			    .getInstance();
		    java.util.Date now = calendar.getTime();
		    Timestamp tStamp = new java.sql.Timestamp(now.getTime());
		    for (int i = 0; i < loggedInUsers.size(); i++) {

			if ((tStamp.getTime() - loggedInUsers.get(i)
				.gettStamp().getTime()) > (1000 * 60 * 15)) {
			    loggedInUsers.remove(i);
			    i--;
			} else
			    System.out.println(loggedInUsers.get(i));
		    }
		    break;
		case "sendmessage":
		    MailManager mm = new MailManager();
		    String title = "Message from " + uName;
		    String text = "";
		    for (int i = 2; i < cmd.length; i++)
			text += " " + cmd[i];
		    mm.sendMessageTo(cmd[1], title, text);
		    break;
		case "block":
		    blockArticle(true, cmd[1]);
		    break;
		case "unblock":
		    blockArticle(false, cmd[1]);
		    break;
		case "lockout":
		    lockUser(true, cmd[1]);
		    break;
		case "unlockout":
		    lockUser(false, cmd[1]);
		    break;
		default:
		    System.out.println("Unrecognized command");
		}
	    }
	}

    }

    private static void lockUser(boolean b, String string) {
	String cmd = "UPDATE Nutzer SET Zustand=";
	if (b)
	    cmd += "'BLOCKED'";
	else
	    cmd += "'OK'";
	cmd += "WHERE idNutzer=" + Integer.parseInt(string) + ";";
	con.AddCommand(cmd);
	try {
	    con.ExecuteAndCommit();
	} catch (SQLException e) {
	    System.out.println("Failure: " + e.getMessage());
	}
    }

    private static void blockArticle(boolean b, String string) {

	String cmd = "UPDATE Artikel SET Zustand=";
	if (b)
	    cmd += "'BLOCKED'";
	else
	    cmd += "'OK'";
	cmd += "WHERE idArtikel=" + Integer.parseInt(string) + ";";
	con.AddCommand(cmd);
	try {
	    con.ExecuteAndCommit();
	} catch (SQLException e) {
	    System.out.println("Failure: " + e.getMessage());
	}
    }

    private static boolean loginAdmin(String uName, String pwd) {
	String query = "SELECT * FROM Admin_login WHERE username='" + uName
		+ "' AND pwd='" + pwd + "';";
	System.out.println(query);
	ResultSet rs;
	try {
	    rs = con.ExecuteQuery(query);
	  if (!rs.next()) {
		    System.out.println("Login failed.");
		    return false;
		}
	} catch (SQLException e) {
	    e.printStackTrace();
	    System.out.println("Login failed.");
	    return false;
	}
	return true;
    }

    private static void PrintOutQueryResults(ResultSet rs) throws SQLException {
	ResultSetMetaData meta = rs.getMetaData();
	System.out.println("Columns: " + meta.getColumnCount());
	for (int i = 1; i <= meta.getColumnCount(); i++) {
	    System.out.print(meta.getColumnLabel(i));
	    ;
	}
	System.out.println();
	while (rs.next()) {
	    for (int i = 1; i <= meta.getColumnCount(); i++) {
		switch (meta.getColumnType(i)) {
		case java.sql.Types.ARRAY:
		    System.out.print(rs.getArray(i));
		    break;
		case java.sql.Types.BIGINT:
		    System.out.print(rs.getBigDecimal(i));
		    break;
		case java.sql.Types.BINARY:
		    System.out.print(rs.getBinaryStream(i));
		    break;
		case java.sql.Types.BIT:
		    System.out.print("Unprintable");
		    break;
		case java.sql.Types.BLOB:
		    System.out.print(rs.getBlob(i));
		    break;
		case java.sql.Types.BOOLEAN:
		    System.out.print(rs.getBoolean(i));
		    break;
		case java.sql.Types.CHAR:
		    System.out.print(rs.getString(i));
		    break;
		case java.sql.Types.CLOB:
		    System.out.print("Unprintable");
		    break;
		case java.sql.Types.DATALINK:
		    System.out.print("Unprintable");
		    break;
		case java.sql.Types.DATE:
		    System.out.print(rs.getDate(i));
		    break;
		case java.sql.Types.DECIMAL:
		    System.out.print(rs.getFloat(i));
		    break;
		case java.sql.Types.DISTINCT:
		    System.out.print("Unprintable");
		    break;
		case java.sql.Types.DOUBLE:
		    System.out.print(rs.getDouble(i));
		    break;
		case java.sql.Types.FLOAT:
		    System.out.print(rs.getFloat(i));
		    break;
		case java.sql.Types.INTEGER:
		    System.out.print(rs.getInt(i));
		    break;
		case java.sql.Types.JAVA_OBJECT:
		    System.out.print("Unprintable");
		    break;
		case java.sql.Types.LONGNVARCHAR:
		    System.out.print("LONGNVARCHARFIELD");
		    break;
		case java.sql.Types.LONGVARBINARY:
		    System.out.print("Unprintable");
		    break;
		case java.sql.Types.LONGVARCHAR:
		    System.out.print(rs.getString(i));
		    break;
		case java.sql.Types.NCHAR:
		    System.out.print("Unprintable");
		    break;
		case java.sql.Types.NCLOB:
		    System.out.print("Unprintable");
		    break;
		case java.sql.Types.NULL:
		    System.out.print("NULL FIELD");
		    break;
		case java.sql.Types.NUMERIC:
		    System.out.print(rs.getString(i));
		    break;
		case java.sql.Types.NVARCHAR:
		    System.out.print(rs.getString(i));
		    break;
		case java.sql.Types.OTHER:
		    System.out.print(rs.getString(i));
		    break;
		case java.sql.Types.REAL:
		    System.out.print(rs.getDouble(i));
		    break;
		case java.sql.Types.REF:
		    System.out.print("Unprintable");
		    break;
		case java.sql.Types.ROWID:
		    System.out.print(rs.getString(i));
		    break;
		case java.sql.Types.SMALLINT:
		    System.out.print(rs.getInt(i));
		    break;
		case java.sql.Types.SQLXML:
		    System.out.print(rs.getSQLXML(i));
		    break;
		case java.sql.Types.STRUCT:
		    System.out.print("Unprintable");
		    break;
		case java.sql.Types.TIME:
		    System.out.print(rs.getTime(i));
		    break;
		case java.sql.Types.TIMESTAMP:
		    System.out.print(rs.getTimestamp(i));
		    break;
		case java.sql.Types.TINYINT:
		    System.out.print(rs.getInt(i));
		    break;
		case java.sql.Types.VARBINARY:
		    System.out.print("UNPRINTABLE");
		    break;
		case java.sql.Types.VARCHAR:
		    System.out.print(rs.getString(i));
		    break;
		}
		if (i != meta.getColumnCount() - 1)
		    System.out.print(" | ");
	    }
	    System.out.println();
	}
    }

    private static void showCommandList() {
	System.out.println("quit\t\tquits the application");
	System.out
		.println("sqlquery sql-query\t\texecutes the query and prints out the results");
	System.out.println("logout\t\tlog out current administrator");
	System.out
		.println("ShowUsers\t\tShows every user with activity in the last 15 minutes");
	System.out
		.println("SendMessage [username] [message] sends a mail to the user");
	System.out
		.println("Block [articlenr] blocks further sales of the article");
	System.out
		.println("UnBlock [articlenr] unblocks further sales of the article");
	System.out.println("LockOut [userId] blocks a user");
	System.out.println("UnLockOut [userId] Unblocks a user");

    }

    private static boolean initDBConnector() {
	try {
	    // con = new DBConnector("mysql.minet.uni-jena.de", 3307, "ka42juf",
	    // "ka42juf", "Poponi8583");
	    con = new DBConnector("", 3306, "ka42juf", "ka42juf", "Poponi8583");
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
	if (waitingForConnection)
	    waitForIncomingRequest();
	else
	    handleRequest();
    }

    private void waitForIncomingRequest() {
	try {
	    ServerSocket sSocket = new ServerSocket(port);
	    while (true) {
		Socket incoming = sSocket.accept();
		new Thread(new Server(incoming)).start();
	    }
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    private void handleRequest() {
	try {
	    ObjectInputStream ois = new ObjectInputStream(
		    socket.getInputStream());

	    MessagePackage msgPck = (MessagePackage) ois.readObject();
	    IVBMessage answer = handleMessage(msgPck);
	    ObjectOutputStream oos = new ObjectOutputStream(
		    socket.getOutputStream());
	    oos.writeObject(answer);
	    socket.close();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (ClassNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    private IVBMessage handleMessage(MessagePackage msgPck) throws Exception {
	int userId = checkCredentials(msgPck.getUserName(),
		msgPck.getPassword());
	IVBMessage msg = msgPck.getMessage();
	if (userId == -1
		&& msg.MsgType() != IVBMessage.MessageType.NewUserMessageRequest
		&& msg.MsgType() != IVBMessage.MessageType.NewUserConfirmationCodeRequest)
	    return new OperationFailedAnswer("Credentials did not validate");
	if (msg.MsgType() != IVBMessage.MessageType.NewUserMessageRequest
		&& msg.MsgType() != IVBMessage.MessageType.NewUserConfirmationCodeRequest) {
	    User usr = getUser(userId);
	    switch (usr.getState()) {
	    case "BLOCKED":
		return new OperationFailedAnswer("User blocked");
	    default:
		break;
	    }

	}
	LoggedInUser liu = new LoggedInUser(userId, msgPck.getUserName());
	if (!loggedInUsers.contains(liu)) {
	    loggedInUsers.addElement(liu);
	    liu.StampRecord();
	} else {
	    loggedInUsers.get(loggedInUsers.indexOf(liu)).StampRecord();
	}
	switch (msg.MsgType()) {
	case LoginMessageRequest:
	    return loginUser((LoginMessageRequest) msg, userId);
	case NewUserMessageRequest:
	    return createNewUser((NewUserMessageRequest) msg);
	case NewUserConfirmationCodeRequest:
	    return checkNewUserConfirmationCode((NewUserConfirmationCodeRequest) msg);
	case DeleteUserMessageRequest:
	    deleteUserQuery((DeleteUserMessageRequest) msg);
	    return new DeleteUserMessageAnswer(successful, message);
	case ChangeUserDataMessageRequest:
	    changeUserDataQuery((ChangeUserDataMessageRequest) msg);
	    return new ChangeUserDataMessageAnswer(successful, message);
	case AddArticleMessageRequest:
	    addArticleQuery((AddArticleMessageRequest) msg);
	    return new AddArticleMessageAnswer(successful, message);
	case DeleteArticleMessageRequest:
	    deleteArticleQuery((DeleteArticleMessageRequest) msg);
	    return new DeleteArticleMessageAnswer(successful, message);
	case ChangeArticleMessageRequest:
	    changeArticleQuery((ChangeArticleMessageRequest) msg);
	    return new ChangeArticleMessageAnswer(successful, message);
	case ArticleListMessageRequest:
	    Vector<Article> list = getArticleList((ArticleListMessageRequest) msg);
	    return new ArticleListMessageAnswer(successful, message, list);
	case BuyArticleMessageRequest:
	    buyArticleQuery((BuyArticleMessageRequest) msg);
	    return new DeleteArticleMessageAnswer(successful, message);
	case LogoutMessageRequest:
	    logoutQuery((LogoutMessageRequest) msg);
	    return new LogoutMessageAnswer(successful, message);
	default:
	    throw new Exception("Unknown message");
	}
    }

    private IVBMessage checkNewUserConfirmationCode(
	    NewUserConfirmationCodeRequest msg) {
	NewUserConfirmationCodePayload payload = ((NewUserConfirmationCodePayload) msg
		.getPayload());
	String query = "SELECT * FROM Approval_List WHERE EMAIL="
		+ SQLDataUtilities.getSQLDataFormat(payload.geteMail())
		+ " AND CODE="
		+ SQLDataUtilities.getSQLDataFormat(payload.getCode()) + ";";
	try {
	    ResultSet rs = con.ExecuteQuery(query);
	    if (!rs.next()) {
		return new NewUserConfirmationCodeAnswer(false,
			"Wrong code or wrong E-Mail");
	    }
	    String cmd = "INSERT INTO User_cred (userID,EMAIL,Pwd)" + "VALUES("
		    + SQLDataUtilities.getSQLDataFormat(rs.getInt("userID"))
		    + ", "
		    + SQLDataUtilities.getSQLDataFormat(rs.getString("EMAIL"))
		    + ", "
		    + SQLDataUtilities.getSQLDataFormat(rs.getString("Pwd"))
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
		    + SQLDataUtilities.getSQLDataFormat(payload.getCode())
		    + ";";
	    con.AddCommand(cmd);
	    System.out.println(cmd);
	    con.ExecuteAndCommit();
	    return new NewUserConfirmationCodeAnswer(true, "");
	} catch (SQLException e) {
	    System.out.println(e.getMessage());
	    return new NewUserConfirmationCodeAnswer(false, "Failure: "
		    + e.getMessage());
	}
    }

    private IVBMessage createNewUser(NewUserMessageRequest msg) {
	NewUserMessageRequestPayload payload = ((NewUserMessageRequestPayload) msg
		.getPayload());
	try {
	    String cmd = payload.getUser().getCreateStatement();
	    con.AddCommand(cmd);
	    con.ExecuteAndCommit();
	    User usr = getUserByMail(payload.getUser().geteMail());
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
		    + SQLDataUtilities.getSQLDataFormat(new java.sql.Timestamp(
			    now.getTime())) + ");";
	    System.out.println(cmd);
	    con.AddCommand(cmd);
	    con.ExecuteAndCommit();
	    MailManager mm = new MailManager();
	    mm.sendConfirmationCode(usr.geteMail(), code);
	    return new NewUserMessageAnswer(true, "");
	} catch (SQLException e) {
	    System.out.println(e.getMessage());
	    return new NewUserMessageAnswer(false, e.getMessage());
	}
    }

    private User getUserByMail(String eMail) throws SQLException {
	String query = "SELECT * FROM Nutzer WHERE EMAIL="
		+ SQLDataUtilities.getSQLDataFormat(eMail) + ";";
	ResultSet rs = con.ExecuteQuery(query);
	if (!rs.next())
	    throw new SQLException("DATA INCONSISTENCY");
	User res = new User();
	res.fillFromResultSet(rs);
	return res;
    }

    private LoginMessageAnswer loginUser(LoginMessageRequest msg, int userId) {
	try {
	    ResultSet rs = con
		    .ExecuteQuery("SELECT * FROM Nutzer WHERE idNutzer="
			    + userId + ";");
	    User user = new User();
	    rs.next();
	    user.fillFromResultSet(rs);
	    return new LoginMessageAnswer(true, "", user);
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return new LoginMessageAnswer(false, "User Not found", null);
    }

    private void buyArticleQuery(BuyArticleMessageRequest msg) {
	try {
	    BuyArticleMessageRequestPayload payload = ((BuyArticleMessageRequestPayload) msg
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
	    con.ExecuteAndCommit();
	    sendTransactionMails(trns);
	    successful = true;
	    message = "";
	} catch (SQLException e) {
	    successful = false;
	    message = "Failure: " + e.getMessage();
	}
    }

    private void sendTransactionMails(Transaction trns) throws SQLException {

	MailManager mm = new MailManager();
	User seller = getUser(trns.getSellerId());
	User buyer = getUser(trns.getBuyerId());
	Article art = getArticle(trns.getArticleId());
	mm.SendTransactionMail(art, trns, seller, buyer);
    }

    private Article getArticle(int articleId) throws SQLException {
	String query = "SELECT * FROM Artikel WHERE idArtikel=" + articleId
		+ ";";
	ResultSet rs = con.ExecuteQuery(query);
	if (!rs.next())
	    throw new SQLException("DATA INCONSISTENCY");
	Article res = new Article(0);
	res.fillFromResultSet(rs);
	return res;
    }

    private User getUser(int userId) throws SQLException {
	String query = "SELECT * FROM Nutzer WHERE idNutzer=" + userId + ";";
	ResultSet rs = con.ExecuteQuery(query);
	if (!rs.next())
	    throw new SQLException("DATA INCONSISTENCY");
	User res = new User();
	res.fillFromResultSet(rs);
	return res;
    }

    private Vector<Article> getArticleList(ArticleListMessageRequest msg) {
	try {
	    ArticleListMessageRequestPayload data = (ArticleListMessageRequestPayload) (msg
		    .getPayload());
	    String query = "SELECT * FROM Artikel WHERE idNutzer";
	    if (data.isShowUserIdArticles())
		query += "=" + data.getUserId() + ";";
	    else
		query += " != " + data.getUserId()
			+ " AND Anzahl>0 AND Zustand='OK';";
	    System.out.println(query);
	    ResultSet rs = con.ExecuteQuery(query);
	    Vector<Article> list = new Vector<Article>();
	    while (rs.next()) {
		Article art = new Article(data.getUserId());
		list.add(art);
		art.fillFromResultSet(rs);
	    }
	    successful = true;
	    message = "";
	    return list;
	} catch (SQLException e) {
	    successful = false;
	    message = "Failure: " + e.getMessage();
	}
	return null;
    }

    private boolean changeArticleQuery(ChangeArticleMessageRequest msg) {
	try {
	    String cmd = ((Article) msg.getPayload()).getUpdateStatement();
	    System.out.println(cmd);
	    con.AddCommand(cmd);
	    con.ExecuteAndCommit();
	    successful = true;
	    message = "";
	} catch (SQLException e) {
	    successful = false;
	    message = "Failure: " + e.getMessage();
	}
	return successful;
    }

    private boolean logoutQuery(LogoutMessageRequest msg) {
	// TODO Auto-generated method stub
	return false;
    }

    private boolean deleteArticleQuery(DeleteArticleMessageRequest msg) {
	// TODO Auto-generated method stub
	return false;
    }

    private boolean addArticleQuery(AddArticleMessageRequest msg) {
	Article art = (Article) msg.getPayload();
	try {
	    con.AddCommand(art.getCreateStatement());
	    con.ExecuteAndCommit();
	    successful = true;
	    message = "Article " + art.getShortDescription() + " added";
	} catch (SQLException e) {
	    successful = false;
	    message = "Failure: " + e.getMessage();
	    return false;
	}
	return true;
    }

    private boolean changeUserDataQuery(ChangeUserDataMessageRequest msg) {
	try {
	    String cmd = ((User) msg.getPayload()).getUpdateStatement();
	    System.out.println(cmd);
	    con.AddCommand(cmd);
	    con.ExecuteAndCommit();
	    successful = true;
	    message = "";
	} catch (SQLException e) {
	    successful = false;
	    message = "Failure: " + e.getMessage();
	}
	return successful;
    }

    private boolean deleteUserQuery(DeleteUserMessageRequest msg) {
	// TODO Auto-generated method stub
	return false;
    }

    private int checkCredentials(String userName, String password) {
	try {
	    String query = "SELECT * FROM User_cred WHERE EMAIL='" + userName
		    + "' AND " + "Pwd='" + password + "';";
	    System.out.println(query);
	    ResultSet rs = con.ExecuteQuery(query);
	    if (!rs.next())
		return -1;
	    return rs.getInt("userID");
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return -1;
    }
}
