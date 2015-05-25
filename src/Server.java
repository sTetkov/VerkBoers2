import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

public class Server {

    private static DBConnector con;

    public static void main(String[] args) {
	if (!initDBConnector()) {
	    System.out.println("Failed to initialize the DB Connector");
	    return;
	}
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

}
