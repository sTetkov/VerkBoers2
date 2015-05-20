import java.sql.*; 
import java.util.Vector;

public class VerkaufsBoerse {

	private static DBConnector con;
	private static Vector<User> userVec;
	private static Vector<Article> articleVec;
	private static Vector<IBankData> bDataVec;
	private static Vector<Transaction> transacVec;
	private static Vector<Offer> offerVec;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			con = new DBConnector("mysql.minet.uni-jena.de",3307,"ka42juf","ka42juf","Poponi8583");
			cleanDB();
			fillDB();
			con.ExecuteAndCommit();
			System . out . println ( " Operationen ausgefuehrt . " ) ;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			con.Close();
			e.printStackTrace();
		}
	}
	
	private static void fillDB() throws SQLException
	{
	}
	
	private static void cleanDB() throws SQLException
	{
	    con.AddCommand("DELETE FROM SEPA WHERE idSEPA IS NOT NULL;");
	    con.AddCommand("DELETE FROM Bankverbindung WHERE idBankverbindung IS NOT NULL;");
	    con.AddCommand("DELETE FROM Transaktion WHERE idTransaktion IS NOT NULL;");
	    con.AddCommand("DELETE FROM Artikel WHERE idArtikel IS NOT NULL;");
	    con.AddCommand("DELETE FROM Nutzer WHERE idNutzer ;");
	    con.ExecuteAndCommit();
	}

}
