package Server;

import DBClasses.Article;
import DBClasses.BankConnection;
import DBClasses.SEPA;
import DBClasses.SQLDataUtilities;
import DBClasses.Transaction;
import DBClasses.User;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Vector;
import messages.AddArticleMessageAnswer;
import messages.ArticleListMessageAnswer;
import messages.ArticleListMessageRequestPayload;
import messages.BuyArticleMessageAnswer;
import messages.BuyArticleMessageRequestPayload;
import messages.ChangeArticleMessageAnswer;
import messages.DeleteArticleMessageAnswer;
import messages.IVBMessage;
import messages.LoginMessageAnswer;
import messages.LoginMessageRequestPayload;
import messages.LogoutMessageAnswer;
import messages.NewUserConfirmationCodeAnswer;
import messages.NewUserConfirmationCodePayload;
import messages.NewUserMessageAnswer;
import messages.NewUserMessageRequestPayload;

public class ServerCore implements Runnable {

    private static DBConnector con;

    private boolean waitingForConnection;

    private static Vector<LoggedInUser> loggedInUsers;

    private static ServerConnectionManager scm=null;

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
                    request = deleteUser(request);
                    break;
                case ChangeUserDataMessageRequest:
                    request = changeUserData(request);
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
                default:
                    throw new AssertionError(request.MsgType().name());
            }
        } catch (SQLException e) {
            //do something
        }
        if (answer == null) {
            customer.requestFailed(null);
        }
    }

    private IVBMessage loginUser(IVBMessage request) throws SQLException {
        LoginMessageRequestPayload payload = (LoginMessageRequestPayload) request;
        String userName = payload.username;
        String pwd = payload.password;
        User user = null;
        if (checkCredentials(userName, pwd) == -1) {
            user = getUser(userName);
        }
        if (user == null) {
            LoginMessageAnswer answer = new LoginMessageAnswer(false, "Wrong username or passwrod", null);
            customer.requestExecuted(answer);
        } else {
            LoginMessageAnswer answer = new LoginMessageAnswer(true, "", user);
            customer.requestExecuted(answer);
        }
        return null;
    }

    private int checkCredentials(String userName, String pwd) throws SQLException {
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
        con.Execute();
        MailManager mm = new MailManager();
        mm.sendConfirmationCode(usr.geteMail(), code);
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
        con.Execute();
        con.Commit();
        return new NewUserConfirmationCodeAnswer(true, "");
    }

    private IVBMessage deleteUser(IVBMessage request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private IVBMessage changeUserData(IVBMessage request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private IVBMessage addArticle(IVBMessage request) throws SQLException {
        Article art = (Article) request.getPayload();
        con.AddCommand(art.getCreateStatement());
        con.ExecuteAndCommit();
        AddArticleMessageAnswer answer = new AddArticleMessageAnswer(true, "Article added");
        return answer;
    }

    private IVBMessage deleteArticle(IVBMessage request) throws SQLException {
        Article art = (Article) request.getPayload();
        art.setState("DELETED");
        con.AddCommand(art.getUpdateStatement());
        con.ExecuteAndCommit();
        DeleteArticleMessageAnswer answer = new DeleteArticleMessageAnswer(true, "Article deleted");
        return answer;
    }

    private IVBMessage changeArticle(IVBMessage request) throws SQLException {
        String cmd = ((Article) request.getPayload()).getUpdateStatement();
        System.out.println(cmd);
        con.AddCommand(cmd);
        con.ExecuteAndCommit();
        ChangeArticleMessageAnswer answer = new ChangeArticleMessageAnswer(true, "Article modified");
        return answer;
    }

    private IVBMessage getArticleList(IVBMessage request) throws SQLException {
        ArticleListMessageRequestPayload data = (ArticleListMessageRequestPayload) (request
                .getPayload());
        String query = "SELECT * FROM Artikel WHERE idNutzer";
        if (data.isShowUserIdArticles()) {
            query += "=" + data.getUserId() + ";";
        } else {
            query += " != " + data.getUserId()
                    + " AND Anzahl>0 AND Zustand='OK';";
        }
        System.out.println(query);
        ResultSet rs = con.ExecuteQuery(query);
        Vector<Article> list = new Vector<Article>();
        while (rs.next()) {
            Article art = new Article(data.getUserId());
            list.add(art);
            art.fillFromResultSet(rs);
        }
        ArticleListMessageAnswer answer = new ArticleListMessageAnswer(true, "", list);
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
        con.ExecuteAndCommit();
        sendTransactionMails(trns);
        BuyArticleMessageAnswer answer = new BuyArticleMessageAnswer();
        return answer;
    }

    private IVBMessage logoutUser(IVBMessage request) {
        loggedInUsers.remove(request.getPayload());
        LogoutMessageAnswer answer = new LogoutMessageAnswer(true, "User logged out");
        return answer;
    }

    private void sendTransactionMails(Transaction trns) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
