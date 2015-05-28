import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Vector;

import DBClasses.*;

import messages.*;

public class Server implements Runnable {

	private static DBConnector con;

	private int port;
	private Socket socket;
	private boolean waitingForConnection;

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
		if (!initDBConnector()) {
			System.out.println("Failed to initialize the DB Connector");
			return;
		}
		new Thread(new Server(Integer.parseInt(args[0]))).start();

		while (true) {
			System.out.println("Insert command (help for a list):");
			String str = System.console().readLine();
			str = str.toLowerCase();
			String[] cmd = str.split(" ");
			switch (cmd[0]) {
			case "quit":
				return;
			case "help":
				showCommandList();
				break;
			case "sqlquery":
				str = new String();
				for (int i = 1; i < cmd.length; i++)
					str += cmd[i];
				try {
					PrintOutQueryResults(con.ExecuteQuery(str));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			default:
				System.out.println("Unrecognized command");
			}
		}

	}

	private static void PrintOutQueryResults(ResultSet rs) throws SQLException {
		ResultSetMetaData meta = rs.getMetaData();
		for (int i = 0; i < meta.getColumnCount(); i++) {
			System.out.print(meta.getColumnLabel(i));
			if (i != meta.getColumnCount() - 1)
				System.out.print(" | ");
		}
		System.out.println();
		while (!rs.isAfterLast()) {
			for (int i = 0; i < meta.getColumnCount(); i++) {
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

	}

	private static boolean initDBConnector() {
		try {
			con = new DBConnector("mysql.minet.uni-jena.de", 3307, "ka42juf",
					"ka42juf", "Poponi8583");
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
			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());
			MessagePackage msgPck = (MessagePackage) ois.readObject();
			IVBMessage answer = handleMessage(msgPck);
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
		if (!checkCredentials(msgPck.getUserName(), msgPck.getPassword()))
			return new OperationFailedAnswer("Credentials did not validate");
		IVBMessage msg = msgPck.getMessage();
		switch (msg.MsgType()) {
		case LoginMessageRequest:
			return new LoginMessageAnswer(true, "");
		case NewUserMessageRequest:
			throw new Exception("Message ti implement");
		case NewUserConfirmationCodeRequest:
			throw new Exception("Message ti implement");
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

	private void buyArticleQuery(BuyArticleMessageRequest msg) {
		try {
			BuyArticleMessageRequestPayload payload = ((BuyArticleMessageRequestPayload) msg
					.getPayload());
			Article art = payload.getArticle();
			Transaction trns = new Transaction(art.getIdUser(),
					payload.getUserId(), art);
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

	private void sendTransactionMails(Transaction trns) {
		// TODO Auto-generated method stub

	}

	private Vector<Article> getArticleList(ArticleListMessageRequest msg) {
		try {
			ArticleListMessageRequestPayload data = (ArticleListMessageRequestPayload) (msg
					.getPayload());
			String query = "SELECT * FROM ARTIKEL WHERE idNutzer";
			if (data.isShowUserIdArticles())
				query += "=" + data.getUserId() + ";";
			else
				query += "IS NOT " + data.getUserId() + " AND Anzahl>0 AND Zustand='OK';";
			ResultSet rs = con.ExecuteQuery(query);
			Vector<Article> list = new Vector<Article>();
			while (!rs.isAfterLast()) {
				Article art = new Article(data.getUserId());
				list.add(art);
				art.fillFromResultSet(rs);
			}
			successful = true;
			message = "";
		} catch (SQLException e) {
			successful = false;
			message = "Failure: " + e.getMessage();
		}
		return null;
	}

	private boolean changeArticleQuery(ChangeArticleMessageRequest msg) {
		try {
			String cmd = ((Article) msg.getPayload()).getUpdateStatement();
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
		// TODO Auto-generated method stub
		return false;
	}

	private boolean deleteUserQuery(DeleteUserMessageRequest msg)
	{
		// TODO Auto-generated method stub
		return false;
	}

	private boolean checkCredentials(String userName, String password) {
		try {
			ResultSet rs=con.ExecuteQuery("SELECT * FROM User_cred WHERE EMAIL='"+userName+"' AND "
																		+"Pwd='"+password+"';");
			if(rs.getFetchSize()==0)
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
