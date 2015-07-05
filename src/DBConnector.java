import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.sql.Statement;

/**
 * 
 */

/**
 * @author ka42juf
 * 
 */
public class DBConnector {

    private String dbURL;
    private String dbUsr;
    private String dbPwd;
    private String dbName;
    private int dbPort;

    private Vector<String> cmdQueue;

    private Connection con;

    public DBConnector(String URL, int Port, String DBName, String UserName,
	    String Password) throws InstantiationException,
	    IllegalAccessException, ClassNotFoundException, SQLException {
	con = null;
	dbURL = URL;
	dbUsr = UserName;
	dbPwd = Password;
	dbName = DBName;
	dbPort = Port;
	cmdQueue = new Vector<String>();

	Class.forName("com.mysql.jdbc.Driver").newInstance();
	String url = "jdbc:mysql://" + dbURL + ":" + dbPort + "/" + dbName;
	con = DriverManager.getConnection(url, dbUsr, dbPwd);
	con.setAutoCommit(false);
    }

    public void OpenConnection() {

    }

    public void CloseConnection() {
	// TODO Auto-generated method stub
    }

    public void AddCommand(String statement) {
	cmdQueue.add(statement);
	// TODO Auto-generated method stub
    }

    public void ClearCommands() {
	cmdQueue.clear();
	// TODO Auto-generated method stub
    }

    public void ExecuteCommands() throws SQLException {
	Statement s;
	for (int i = 0; i < cmdQueue.size(); i++) {
	    s = con.createStatement();
	    s.execute(cmdQueue.elementAt(i));
	    s.close();
	}
	cmdQueue.clear();
    }

    public void Commit() throws SQLException {
	con.commit();
    }

    public void ExecuteAndCommit() throws SQLException {
	ExecuteCommands();
	Commit();
    }

    public void Close() {
	if (con != null)
	    try {
		con.close();
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
    }

    public ResultSet ExecuteQuery(String query) throws SQLException {
	Statement s = con.createStatement();
	return s.executeQuery(query);
    }
}
